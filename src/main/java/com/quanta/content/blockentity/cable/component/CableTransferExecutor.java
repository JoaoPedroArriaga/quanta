package com.quanta.content.blockentity.cable.component;

import com.quanta.capability.QuantaCapabilities;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.network.CableNetwork;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.GasStack;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.*;

public class CableTransferExecutor {

    private final CableBlockEntity be;
    private final CableTypeManager typeManager;
    private final CableConnectionManager connectionManager;
    private final CableNetworkProxy networkProxy;
    private int activeTransferMask;

    public CableTransferExecutor(CableBlockEntity be, CableTypeManager typeManager,
                                  CableConnectionManager connectionManager,
                                  CableNetworkProxy networkProxy) {
        this.be = be;
        this.typeManager = typeManager;
        this.connectionManager = connectionManager;
        this.networkProxy = networkProxy;
    }

    private Level getLevel() { return be.getLevel(); }
    private BlockPos getPos() { return be.getBlockPos(); }

    public int getActiveTransferMask() { return activeTransferMask; }
    public void setActiveTransferMask(int mask) { this.activeTransferMask = mask; }

    public void executeTick() {
        activeTransferMask = 0;

        CableNetwork net = networkProxy.getNetwork();
        if (net == null) return;

        boolean itemPath = hasPath(net, TransferType.ITEM);
        boolean fluidPath = hasPath(net, TransferType.FLUID);
        boolean gasPath = hasPath(net, TransferType.GAS);

        if (typeManager.hasType(TransferType.QUANTA)) {
            extractQuanta(net);
            insertQuanta(net);
        }
        if (typeManager.hasType(TransferType.ITEM) && itemPath) {
            extractItems(net);
            insertItems(net);
        }
        if (typeManager.hasType(TransferType.FLUID) && fluidPath) {
            extractFluid(net);
            insertFluid(net);
        }
        if (typeManager.hasType(TransferType.GAS) && gasPath) {
            extractGas(net);
            insertGas(net);
        }

        markActive(net, itemPath, fluidPath, gasPath);
    }

    // ==================== BFS PATH FINDING ====================

    private boolean hasPath(CableNetwork net, TransferType type) {
        Level level = getLevel();
        if (level == null) return false;

        Set<BlockPos> sources = new HashSet<>();
        Set<BlockPos> destinations = new HashSet<>();

        for (BlockPos cablePos : net.getCables()) {
            if (!(level.getBlockEntity(cablePos) instanceof CableBlockEntity cableBe)) continue;
            if (!cableBe.getTypeManager().hasType(type)) continue;
            for (Direction d : Direction.values()) {
                ConnectionConfig cfg = cableBe.getConnectionManager().getConfig(d);
                if (!cfg.getMode().isEnabled()) continue;
                if (cfg.getMode() == ConnectionConfig.Mode.EXTRACT
                    && cableBe.getConnectionManager().hasExternalConnection(d, type)) {
                    sources.add(cablePos);
                }
                if (cfg.getMode() == ConnectionConfig.Mode.INSERT
                    && cableBe.getConnectionManager().hasExternalConnection(d, type)) {
                    destinations.add(cablePos);
                }
            }
        }

        if (sources.isEmpty() || destinations.isEmpty()) return false;

        for (BlockPos source : sources) {
            if (destinations.contains(source)) return true;
        }

        for (BlockPos source : sources) {
            Set<BlockPos> visited = new HashSet<>();
            Queue<BlockPos> queue = new ArrayDeque<>();
            queue.add(source);
            visited.add(source);

            while (!queue.isEmpty()) {
                BlockPos cur = queue.poll();
                if (destinations.contains(cur)) return true;
                if (!(level.getBlockEntity(cur) instanceof CableBlockEntity curBe)) continue;
                if (!curBe.getTypeManager().hasType(type)) continue;
                for (Direction d : Direction.values()) {
                    if (!curBe.getConnectionManager().getConfig(d).getMode().isEnabled()) continue;
                    BlockPos nb = cur.relative(d);
                    if (visited.contains(nb) || !net.getCables().contains(nb)) continue;
                    if (level.getBlockEntity(nb) instanceof CableBlockEntity nbe
                        && nbe.getTypeManager().hasType(type)) {
                        visited.add(nb);
                        queue.add(nb);
                    }
                }
            }
        }
        return false;
    }

    private void markActive(CableNetwork net, boolean itemPath, boolean fluidPath, boolean gasPath) {
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (!cfg.getMode().isEnabled() || connectionManager.getConnectionMask(d) == 0) continue;
            for (TransferType type : typeManager.getTypes()) {
                if (!connectionManager.hasConnection(d, type)
                    || !connectionManager.hasExternalConnection(d, type)) continue;
                boolean path = switch (type) {
                    case QUANTA -> true;
                    case ITEM -> itemPath;
                    case FLUID -> fluidPath;
                    case GAS -> gasPath;
                };
                if (path && (cfg.getMode() == ConnectionConfig.Mode.EXTRACT
                    || cfg.getMode() == ConnectionConfig.Mode.INSERT)) {
                    activeTransferMask |= (1 << d.ordinal());
                    break;
                }
            }
        }
    }

    // ==================== FILTER CHECK ====================

    private boolean passesItemFilter(ConnectionConfig cfg, ItemStack stack) {
        if (!cfg.isFilterEnabled() || cfg.getItemFilters().isEmpty()) return true;
        for (ConnectionConfig.ItemFilter filter : cfg.getItemFilters()) {
            if (matchesItemFilter(filter, stack)) {
                return cfg.isWhitelist();
            }
        }
        return !cfg.isWhitelist();
    }

    private boolean matchesItemFilter(ConnectionConfig.ItemFilter filter, ItemStack stack) {
        return switch (filter.type) {
            case ITEM -> {
                if (filter.ignoreDamage && filter.ignoreEnchants && filter.ignoreCount) {
                    yield stack.getItem() == filter.stack.getItem();
                }
                yield ItemStack.isSameItemSameComponents(stack, filter.stack);
            }
            case TAG -> stack.is(tag -> tag.toString().equals(filter.tagOrRegex));
            case NBT -> stack.getItem() == filter.stack.getItem();
            case REGEX -> stack.getDescriptionId().matches(filter.tagOrRegex);
            case FLUID -> false;
            case GAS -> false;
        };
    }

    // ==================== FLUID/GAS FILTERS ====================

    private boolean passesFluidFilter(ConnectionConfig cfg, net.neoforged.neoforge.fluids.FluidStack stack) {
        if (!cfg.isFilterEnabled() || cfg.getItemFilters().isEmpty()) return true;
        for (ConnectionConfig.ItemFilter filter : cfg.getItemFilters()) {
            if (matchesFluidFilter(filter, stack)) {
                return cfg.isWhitelist();
            }
        }
        return !cfg.isWhitelist();
    }

    private boolean matchesFluidFilter(ConnectionConfig.ItemFilter filter, net.neoforged.neoforge.fluids.FluidStack stack) {
        if (stack.isEmpty()) return false;
        return switch (filter.type) {
            case FLUID -> {
                if (!filter.tagOrRegex.isEmpty()) {
                    var id = net.minecraft.core.registries.BuiltInRegistries.FLUID.getKey(stack.getFluid());
                    if (id != null && id.toString().equals(filter.tagOrRegex)) yield true;
                }
                if (!filter.stack.isEmpty()) {
                    var fhOpt = FluidUtil.getFluidHandler(filter.stack);
                    if (fhOpt.isPresent()) {
                        var contained = fhOpt.get().getFluidInTank(0);
                        yield !contained.isEmpty() && contained.getFluid() == stack.getFluid();
                    }
                }
                yield false;
            }
            case TAG -> {
                if (!filter.tagOrRegex.isEmpty()) {
                    var id = net.minecraft.core.registries.BuiltInRegistries.FLUID.getKey(stack.getFluid());
                    if (id != null && id.toString().equals(filter.tagOrRegex)) yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    private boolean passesGasFilter(ConnectionConfig cfg, com.quanta.core.transfer.GasStack stack) {
        if (!cfg.isFilterEnabled() || cfg.getItemFilters().isEmpty()) return true;
        for (ConnectionConfig.ItemFilter filter : cfg.getItemFilters()) {
            if (matchesGasFilter(filter, stack)) {
                return cfg.isWhitelist();
            }
        }
        return !cfg.isWhitelist();
    }

    private boolean matchesGasFilter(ConnectionConfig.ItemFilter filter, com.quanta.core.transfer.GasStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        return switch (filter.type) {
            case GAS -> {
                if (!filter.tagOrRegex.isEmpty()) {
                    var id = net.minecraft.core.registries.BuiltInRegistries.FLUID.getKey(stack.getGas());
                    if (id != null && id.toString().equals(filter.tagOrRegex)) yield true;
                }
                if (!filter.stack.isEmpty()) {
                    var fhOpt = FluidUtil.getFluidHandler(filter.stack);
                    if (fhOpt.isPresent()) {
                        var contained = fhOpt.get().getFluidInTank(0);
                        yield !contained.isEmpty() && contained.getFluid() == stack.getGas();
                    }
                }
                yield false;
            }
            case TAG -> {
                if (!filter.tagOrRegex.isEmpty()) {
                    var id = net.minecraft.core.registries.BuiltInRegistries.FLUID.getKey(stack.getGas());
                    if (id != null && id.toString().equals(filter.tagOrRegex)) yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    // ==================== EXTRACT ====================

    private void extractQuanta(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.EXTRACT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.QUANTA)) continue;
            var ext = level.getCapability(QuantaCapabilities.QUANTA_ENERGY, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            int space = net.getMaxQuanta() - net.getQuantaStored();
            if (space <= 0) continue;
            int extracted = ext.extractQuanta(Math.min(cfg.getTransferLimit(), space), false, true);
            if (extracted > 0) {
                ext.extractQuanta(extracted, false, false);
                net.addQuanta(extracted);
            }
        }
    }

    private void extractItems(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.EXTRACT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.ITEM)) continue;
            var ext = level.getCapability(Capabilities.ItemHandler.BLOCK, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            int space = net.getMaxItems() - net.getItemCount();
            if (space <= 0) continue;
            for (int s = 0; s < ext.getSlots() && space > 0; s++) {
                ItemStack ex = ext.extractItem(s, Math.min(cfg.getTransferLimit(), space), true);
                if (!ex.isEmpty() && passesItemFilter(cfg, ex)) {
                    int take = Math.min(ex.getCount(), space);
                    ext.extractItem(s, take, false);
                    net.addItem(ex.copyWithCount(take));
                    space -= take;
                }
            }
        }
    }

    private void extractFluid(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.EXTRACT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.FLUID)) continue;
            var ext = level.getCapability(Capabilities.FluidHandler.BLOCK, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            int space = net.getMaxFluid() - net.getFluid().getAmount();
            if (space <= 0) continue;
            FluidStack drained = ext.drain(Math.min(cfg.getTransferLimit(), space), IFluidHandler.FluidAction.SIMULATE);
            if (!drained.isEmpty() && passesFluidFilter(cfg, drained)) {
                int filled = net.fillFluid(drained);
                if (filled > 0) ext.drain(filled, IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    private void extractGas(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.EXTRACT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.GAS)) continue;
            var ext = level.getCapability(QuantaCapabilities.QUANTA_GAS, pos.relative(d), d.getOpposite());
            if (ext == null || ext.isEmpty()) continue;
            int space = net.getMaxGas();
            GasStack tmp = net.drainGas(1);
            if (!tmp.isEmpty()) { space -= tmp.getAmount(); net.fillGas(tmp); }
            if (space <= 0) continue;
            GasStack drained = ext.drain(Math.min(cfg.getTransferLimit(), space), true);
            if (!drained.isEmpty() && passesGasFilter(cfg, drained)) {
                int filled = net.fillGas(drained);
                if (filled > 0) ext.drain(filled, false);
            }
        }
    }

    // ==================== INSERT ====================

    private void insertQuanta(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.INSERT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.QUANTA)) continue;
            var ext = level.getCapability(QuantaCapabilities.QUANTA_ENERGY, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            int stored = Math.min(net.getQuantaStored(), cfg.getTransferLimit());
            if (stored <= 0) continue;
            int added = ext.addQuanta(stored);
            if (added > 0) net.extractQuanta(added);
        }
    }

    private void insertItems(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.INSERT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.ITEM)) continue;
            var ext = level.getCapability(Capabilities.ItemHandler.BLOCK, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            int stored = Math.min(net.getItemCount(), cfg.getTransferLimit());
            if (stored <= 0) continue;
            ItemStack extracted = net.extractItem(stored);
            if (!extracted.isEmpty() && passesItemFilter(cfg, extracted)) {
                ItemStack remainder = ItemHandlerHelper.insertItem(ext, extracted, false);
                if (!remainder.isEmpty()) net.addItem(remainder);
            } else if (!extracted.isEmpty()) {
                net.addItem(extracted); // Devolve se não passar no filtro
            }
        }
    }

    private void insertFluid(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.INSERT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.FLUID)) continue;
            var ext = level.getCapability(Capabilities.FluidHandler.BLOCK, pos.relative(d), d.getOpposite());
            if (ext == null) continue;
            FluidStack drained = net.drainFluid(cfg.getTransferLimit());
            if (!drained.isEmpty() && passesFluidFilter(cfg, drained)) {
                int filled = ext.fill(drained, IFluidHandler.FluidAction.SIMULATE);
                if (filled > 0) ext.fill(new FluidStack(drained.getFluid(), filled), IFluidHandler.FluidAction.EXECUTE);
                if (filled < drained.getAmount()) {
                    net.fillFluid(new FluidStack(drained.getFluid(), drained.getAmount() - filled));
                }
            } else if (!drained.isEmpty()) {
                // put back if not passing filter
                net.fillFluid(new FluidStack(drained.getFluid(), drained.getAmount()));
            }
        }
    }

    private void insertGas(CableNetwork net) {
        Level level = getLevel();
        BlockPos pos = getPos();
        for (Direction d : Direction.values()) {
            ConnectionConfig cfg = connectionManager.getConfig(d);
            if (cfg.getMode() != ConnectionConfig.Mode.INSERT
                || !cfg.getMode().isEnabled()
                || !connectionManager.hasConnection(d, TransferType.GAS)) continue;
            var ext = level.getCapability(QuantaCapabilities.QUANTA_GAS, pos.relative(d), d.getOpposite());
            if (ext == null || ext.isFull()) continue;
            GasStack drained = net.drainGas(cfg.getTransferLimit());
            if (!drained.isEmpty() && passesGasFilter(cfg, drained)) {
                int filled = ext.fill(drained, true);
                if (filled > 0) ext.fill(new GasStack(drained.getGas(), filled), false);
                if (filled < drained.getAmount()) {
                    net.fillGas(new GasStack(drained.getGas(), drained.getAmount() - filled));
                }
            } else if (!drained.isEmpty()) {
                // put back if not passing filter
                net.fillGas(new GasStack(drained.getGas(), drained.getAmount()));
            }
        }
    }
}