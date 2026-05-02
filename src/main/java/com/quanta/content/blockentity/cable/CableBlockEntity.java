package com.quanta.content.blockentity.cable;

import com.quanta.api.cable.IGasHandler;
import com.quanta.api.cable.IQuantaStorage;
import com.quanta.client.animation.ClientAnimationHandler;
import com.quanta.client.renderer.state.CableRenderState;
import com.quanta.content.block.cable.CableBlock;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.core.network.NetworkManager;
import com.quanta.core.tier.QuantumTier;
import com.quanta.core.transfer.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.*;

public class CableBlockEntity extends BlockEntity {

    private final ObjectArrayList<TransferType> types = new ObjectArrayList<>();
    private int typeMask = 0;
    private final int[] connectionMasks = new int[6];
    private final Map<Direction, ConnectionConfig> configs = new EnumMap<>(Direction.class);
    private QuantumTier tier = QuantumTier.DECOHERENT;
    private boolean connectionsDirty = true;
    private boolean networkDirty = true;
    private int tickCounter = 0;

    private int quantaBuffer = 0;
    private final List<ItemStack> itemBuffer = new ArrayList<>();
    private FluidStack fluidBuffer = FluidStack.EMPTY;
    private GasStack gasBuffer = GasStack.EMPTY;

    // Máscara de faces com transferência ativa neste tick
    private int activeTransferMask = 0;

    private IQuantaStorage quantaStorage;
    private IItemHandler itemHandler;
    private IFluidHandler fluidHandler;
    private IGasHandler gasHandler;
    private CableRenderState renderState;

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABLE.get(), pos, state);
        for (Direction dir : Direction.values()) configs.put(dir, new ConnectionConfig());
    }

    public boolean addType(TransferType type) {
        if (types.contains(type)) return false;
        types.add(type); typeMask |= type.bitMask;
        connectionsDirty = networkDirty = true; renderState = null;
        setChanged(); syncToClient(); updateConnections();
        return true;
    }

    public boolean removeType(TransferType type) {
        if (!types.remove(type)) return false;
        typeMask &= ~type.bitMask; Arrays.fill(connectionMasks, 0);
        connectionsDirty = networkDirty = true; renderState = null;
        setChanged(); syncToClient(); updateConnections();
        return true;
    }

    public boolean hasType(TransferType type) { return (typeMask & type.bitMask) != 0; }
    public List<TransferType> getTypes() { return Collections.unmodifiableList(types); }
    public int getTypeMask() { return typeMask; }
    public TransferType getBaseType() { return types.isEmpty() ? TransferType.QUANTA : types.get(0); }

    public void scheduleConnectionUpdate() { connectionsDirty = true; renderState = null; }

    public void updateConnections() {
        if (level == null) return;
        int[] old = connectionMasks.clone();
        Arrays.fill(connectionMasks, 0);
        BlockPos p = worldPosition;
        for (TransferType t : types) {
            int bit = t.bitMask;
            if (canConnect(p, Direction.DOWN, t)) connectionMasks[0] |= bit;
            if (canConnect(p, Direction.UP, t)) connectionMasks[1] |= bit;
            if (canConnect(p, Direction.NORTH, t)) connectionMasks[2] |= bit;
            if (canConnect(p, Direction.SOUTH, t)) connectionMasks[3] |= bit;
            if (canConnect(p, Direction.WEST, t)) connectionMasks[4] |= bit;
            if (canConnect(p, Direction.EAST, t)) connectionMasks[5] |= bit;
        }
        if (!Arrays.equals(old, connectionMasks)) { updateBlockState(); networkDirty = true; renderState = null; }
        connectionsDirty = false;
    }

    private boolean canConnect(BlockPos pos, Direction dir, TransferType t) {
        BlockPos neighbor = pos.relative(dir);
        if (level.getBlockEntity(neighbor) instanceof CableBlockEntity nb && nb.hasType(t)) return true;
        Direction opposite = dir.getOpposite();
        return switch (t) {
            case QUANTA -> level.getCapability(Capabilities.EnergyStorage.BLOCK, neighbor, opposite) != null
                        || level.getCapability(com.quanta.capability.QuantaCapabilities.QUANTA_ENERGY, neighbor, opposite) != null;
            case ITEM -> level.getCapability(Capabilities.ItemHandler.BLOCK, neighbor, opposite) != null;
            case FLUID -> level.getCapability(Capabilities.FluidHandler.BLOCK, neighbor, opposite) != null;
            case GAS -> level.getCapability(com.quanta.capability.QuantaCapabilities.QUANTA_GAS, neighbor, opposite) != null;
        };
    }

    private void updateBlockState() {
        if (level == null) return;
        BlockState st = getBlockState();
        for (int i = 0; i < 6; i++) st = st.setValue(CableBlock.DIR_PROPS[i], connectionMasks[i] != 0);
        if (st != getBlockState()) level.setBlock(worldPosition, st, 3);
        requestModelDataUpdate(); syncToClient();
    }

    public int getConnectionMask(Direction dir) { return connectionMasks[dir.ordinal()]; }
    public boolean hasConnection(Direction dir, TransferType t) { return (connectionMasks[dir.ordinal()] & t.bitMask) != 0; }
    public int getActiveTransferMask() { return activeTransferMask; }

    public ConnectionConfig getConfig(Direction dir) { return configs.get(dir); }
    public void setConfig(Direction dir, ConnectionConfig c) { configs.put(dir, c); renderState = null; setChanged(); syncToClient(); }

    public IQuantaStorage getQuantaStorage() { if (quantaStorage == null) quantaStorage = new CableQuantaStorage(); return quantaStorage; }
    public IItemHandler getItemHandler() { if (itemHandler == null) itemHandler = new CableItemHandler(); return itemHandler; }
    public IFluidHandler getFluidHandler() { if (fluidHandler == null) fluidHandler = new CableFluidHandler(); return fluidHandler; }
    public IGasHandler getGasHandler() { if (gasHandler == null) gasHandler = new CableGasHandler(); return gasHandler; }

    public void tick() {
        if (level == null || level.isClientSide) return;
        tickCounter++;
        activeTransferMask = 0; // Reseta a cada tick
        if (connectionsDirty) updateConnections();
        if (networkDirty && tickCounter % 100 == 0) { NetworkManager.rebuildNetwork(level, worldPosition); networkDirty = false; }
        if (hasType(TransferType.QUANTA)) processQuantaTransfer();
        if (hasType(TransferType.ITEM)) processItemTransfer();
        if (hasType(TransferType.FLUID)) processFluidTransfer();
        if (hasType(TransferType.GAS)) processGasTransfer();
    }

    private void processQuantaTransfer() {
        for (Direction dir : Direction.values()) {
            ConnectionConfig cfg = configs.get(dir);
            if (!cfg.getMode().isEnabled()) continue;
            if (!hasConnection(dir, TransferType.QUANTA)) continue;
            BlockPos neighbor = worldPosition.relative(dir);
            Direction opposite = dir.getOpposite();

            if (cfg.getMode() == ConnectionConfig.Mode.EXTRACT) {
                IEnergyStorage ext = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighbor, opposite);
                if (ext != null && ext.canExtract()) {
                    int fe = ext.extractEnergy(cfg.getTransferLimit() * 100, true);
                    int q = fe / 100;
                    int space = tier.energyTransfer * 20 - quantaBuffer;
                    int add = Math.min(q, space);
                    if (add > 0) {
                        ext.extractEnergy(add * 100, false);
                        quantaBuffer += add;
                        activeTransferMask |= (1 << dir.ordinal());
                        syncToClient();
                    }
                }
            } else if (cfg.getMode() == ConnectionConfig.Mode.INSERT) {
                if (quantaBuffer <= 0) continue;
                IEnergyStorage ext = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighbor, opposite);
                if (ext != null && ext.canReceive()) {
                    int q = Math.min(quantaBuffer, cfg.getTransferLimit());
                    int fe = ext.receiveEnergy(q * 100, true);
                    int sent = fe / 100;
                    if (sent > 0) {
                        ext.receiveEnergy(sent * 100, false);
                        quantaBuffer -= sent;
                        activeTransferMask |= (1 << dir.ordinal());
                        syncToClient();
                    }
                }
            }
        }
    }

    private void processItemTransfer() {
        for (Direction dir : Direction.values()) {
            ConnectionConfig cfg = configs.get(dir);
            if (!cfg.getMode().isEnabled()) continue;
            if (!hasConnection(dir, TransferType.ITEM)) continue;
            BlockPos neighbor = worldPosition.relative(dir);
            Direction opposite = dir.getOpposite();
            IItemHandler ext = level.getCapability(Capabilities.ItemHandler.BLOCK, neighbor, opposite);
            if (ext == null) continue;

            if (cfg.getMode() == ConnectionConfig.Mode.EXTRACT) {
                for (int slot = 0; slot < ext.getSlots() && itemBuffer.size() < tier.itemTransfer; slot++) {
                    ItemStack extracted = ext.extractItem(slot, cfg.getTransferLimit(), true);
                    if (!extracted.isEmpty()) {
                        ItemStack remainder = ItemHandlerHelper.insertItem(getItemHandler(), extracted, true);
                        int toTake = extracted.getCount() - remainder.getCount();
                        if (toTake > 0) {
                            ext.extractItem(slot, toTake, false);
                            ItemHandlerHelper.insertItem(getItemHandler(), new ItemStack(extracted.getItem(), toTake), false);
                            activeTransferMask |= (1 << dir.ordinal());
                            syncToClient();
                        }
                        break;
                    }
                }
            } else if (cfg.getMode() == ConnectionConfig.Mode.INSERT) {
                if (itemBuffer.isEmpty()) continue;
                for (int i = 0; i < itemBuffer.size(); i++) {
                    ItemStack stack = itemBuffer.get(i).copy();
                    ItemStack remainder = ItemHandlerHelper.insertItem(ext, stack, true);
                    int toSend = stack.getCount() - remainder.getCount();
                    if (toSend > 0) {
                        ItemStack toInsert = itemBuffer.get(i).copy();
                        toInsert.setCount(toSend);
                        ItemHandlerHelper.insertItem(ext, toInsert, false);
                        itemBuffer.get(i).shrink(toSend);
                        if (itemBuffer.get(i).isEmpty()) itemBuffer.remove(i);
                        activeTransferMask |= (1 << dir.ordinal());
                        syncToClient();
                        break;
                    }
                }
            }
        }
    }

    private void processFluidTransfer() {
        for (Direction dir : Direction.values()) {
            ConnectionConfig cfg = configs.get(dir);
            if (!cfg.getMode().isEnabled()) continue;
            if (!hasConnection(dir, TransferType.FLUID)) continue;
            BlockPos neighbor = worldPosition.relative(dir);
            Direction opposite = dir.getOpposite();
            IFluidHandler ext = level.getCapability(Capabilities.FluidHandler.BLOCK, neighbor, opposite);
            if (ext == null) continue;

            IFluidHandler cable = getFluidHandler();
            if (cfg.getMode() == ConnectionConfig.Mode.EXTRACT) {
                FluidStack drained = ext.drain(cfg.getTransferLimit(), IFluidHandler.FluidAction.SIMULATE);
                if (!drained.isEmpty()) {
                    int filled = cable.fill(drained, IFluidHandler.FluidAction.SIMULATE);
                    if (filled > 0) {
                        ext.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                        cable.fill(new FluidStack(drained.getFluid(), filled), IFluidHandler.FluidAction.EXECUTE);
                        activeTransferMask |= (1 << dir.ordinal());
                    }
                }
            } else if (cfg.getMode() == ConnectionConfig.Mode.INSERT) {
                FluidStack drained = cable.drain(cfg.getTransferLimit(), IFluidHandler.FluidAction.SIMULATE);
                if (!drained.isEmpty()) {
                    int filled = ext.fill(drained, IFluidHandler.FluidAction.SIMULATE);
                    if (filled > 0) {
                        cable.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                        ext.fill(new FluidStack(drained.getFluid(), filled), IFluidHandler.FluidAction.EXECUTE);
                        activeTransferMask |= (1 << dir.ordinal());
                    }
                }
            }
        }
    }

    private void processGasTransfer() {
        for (Direction dir : Direction.values()) {
            ConnectionConfig cfg = configs.get(dir);
            if (!cfg.getMode().isEnabled()) continue;
            if (!hasConnection(dir, TransferType.GAS)) continue;
            BlockPos neighbor = worldPosition.relative(dir);
            Direction opposite = dir.getOpposite();
            IGasHandler ext = level.getCapability(com.quanta.capability.QuantaCapabilities.QUANTA_GAS, neighbor, opposite);
            if (ext == null) continue;

            IGasHandler cable = getGasHandler();
            if (cfg.getMode() == ConnectionConfig.Mode.EXTRACT) {
                if (!ext.isEmpty() && !cable.isFull()) {
                    GasStack drained = ext.drain(cfg.getTransferLimit(), true);
                    if (!drained.isEmpty()) {
                        int filled = cable.fill(drained, false);
                        if (filled > 0) {
                            ext.drain(filled, false);
                            activeTransferMask |= (1 << dir.ordinal());
                        }
                    }
                }
            } else if (cfg.getMode() == ConnectionConfig.Mode.INSERT) {
                if (!cable.isEmpty() && !ext.isFull()) {
                    GasStack drained = cable.drain(cfg.getTransferLimit(), true);
                    if (!drained.isEmpty()) {
                        int filled = ext.fill(drained, true);
                        if (filled > 0) {
                            cable.drain(filled, false);
                            ext.fill(new GasStack(drained.getGas(), filled), false);
                            activeTransferMask |= (1 << dir.ordinal());
                        }
                    }
                }
            }
        }
    }

    public void onRemoved() { if (level != null && !level.isClientSide) NetworkManager.removeCable(level, worldPosition); }

    @Override public void onLoad() {
        super.onLoad();
        if (level != null) {
            if (level.isClientSide) { ClientAnimationHandler.registerAnimatedBlock(worldPosition); requestModelDataUpdate(); }
            else scheduleConnectionUpdate();
        }
    }

    @Override public void setRemoved() {
        if (level != null && level.isClientSide) ClientAnimationHandler.unregisterAnimatedBlock(worldPosition);
        super.setRemoved(); onRemoved();
    }

    public QuantumTier getTier() { return tier; }
    public void upgradeTier(QuantumTier nt) { if (nt.ordinal() > tier.ordinal()) { tier = nt; setChanged(); syncToClient(); } }

    public void syncToClient() { if (level != null && !level.isClientSide) { setChanged(); level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); } }

    @Override public CompoundTag getUpdateTag(HolderLookup.Provider r) { CompoundTag t = super.getUpdateTag(r); saveAdditional(t, r); return t; }
    @Override public Packet<ClientGamePacketListener> getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }
    @Override public void handleUpdateTag(CompoundTag t, HolderLookup.Provider r) { super.handleUpdateTag(t, r); if (level != null && level.isClientSide) requestModelDataUpdate(); }
    @Override public void onDataPacket(Connection n, ClientboundBlockEntityDataPacket p, HolderLookup.Provider r) { super.onDataPacket(n, p, r); if (level != null && level.isClientSide) requestModelDataUpdate(); }

    @Override public ModelData getModelData() {
        if (renderState == null) renderState = new CableRenderState();
        renderState.update(new ArrayList<>(types), connectionMasks, new EnumMap<>(configs), activeTransferMask);
        return ModelData.builder().with(CableRenderState.PROPERTY, renderState).build();
    }

    @Override public void requestModelDataUpdate() { super.requestModelDataUpdate(); renderState = null; }

    @Override protected void saveAdditional(CompoundTag t, HolderLookup.Provider r) {
        super.saveAdditional(t, r);
        t.putIntArray("types", types.stream().mapToInt(TransferType::ordinal).toArray());
        t.putIntArray("masks", connectionMasks);
        t.putByte("tier", (byte) tier.ordinal());
        CompoundTag ct = new CompoundTag();
        for (Direction d : Direction.values()) ct.put(d.getName(), configs.get(d).save());
        t.put("configs", ct);
    }

    @Override protected void loadAdditional(CompoundTag t, HolderLookup.Provider r) {
        super.loadAdditional(t, r);
        types.clear(); typeMask = 0;
        for (int id : t.getIntArray("types")) { TransferType tt = TransferType.fromId(id); types.add(tt); typeMask |= tt.bitMask; }
        int[] s = t.getIntArray("masks"); if (s.length == 6) System.arraycopy(s, 0, connectionMasks, 0, 6);
        tier = QuantumTier.fromId(t.getByte("tier"));
        if (t.contains("configs")) { CompoundTag ct = t.getCompound("configs");
            for (Direction d : Direction.values()) if (ct.contains(d.getName())) { ConnectionConfig c = new ConnectionConfig(); c.load(ct.getCompound(d.getName())); configs.put(d, c); } }
        connectionsDirty = networkDirty = true; renderState = null;
    }

    // Inner handlers iguais...
    private class CableQuantaStorage implements IQuantaStorage {
        @Override public int getQuantaStored() { return quantaBuffer; }
        @Override public int getQuantaCapacity() { return tier.energyTransfer * 20; }
        @Override public int addQuanta(int a) { int s=getQuantaCapacity()-quantaBuffer,ad=Math.min(a,s); quantaBuffer+=ad; if(ad>0)syncToClient(); return ad; }
        @Override public int extractQuanta(int a, boolean at, boolean sim) { int e=Math.min(a,quantaBuffer); if(!sim){quantaBuffer-=e; if(e>0)syncToClient();} return e; }
        @Override public int extractQuanta(int a, boolean at) { return extractQuanta(a,at,false); }
        @Override public boolean isInSuperposition() { return false; } @Override public float getSuperpositionChanceBonus() { return 0; } @Override public int getTier() { return tier.id; }
        @Override public void addConnection(IQuantaStorage o) {} @Override public void removeConnection(IQuantaStorage o) {} @Override public List<IQuantaStorage> getConnections() { return List.of(); }
        @Override public int getTotalExtracted() { return 0; } @Override public int getTotalReceived() { return 0; } @Override public void resetStats() {}
        @Override public int receiveEnergy(int m, boolean s) { return 0; } @Override public int extractEnergy(int m, boolean s) { return 0; }
        @Override public int getEnergyStored() { return 0; } @Override public int getMaxEnergyStored() { return 0; }
        @Override public boolean canExtract() { return true; } @Override public boolean canReceive() { return true; }
    }
    private class CableItemHandler implements IItemHandler {
        @Override public int getSlots() { return itemBuffer.size(); }
        @Override public ItemStack getStackInSlot(int s) { return s<itemBuffer.size()?itemBuffer.get(s):ItemStack.EMPTY; }
        @Override public ItemStack insertItem(int s, ItemStack st, boolean sim) { if(st.isEmpty())return st; int l=Math.min(st.getCount(),tier.itemTransfer); if(!sim){ItemStack c=st.copy();c.setCount(l);itemBuffer.add(c);syncToClient();} ItemStack r=st.copy();r.shrink(l);return r; }
        @Override public ItemStack extractItem(int s, int a, boolean sim) { if(s>=itemBuffer.size())return ItemStack.EMPTY; ItemStack st=itemBuffer.get(s); int e=Math.min(a,st.getCount()); if(!sim){st.shrink(e);if(st.isEmpty())itemBuffer.remove(s);syncToClient();} ItemStack r=st.copy();r.setCount(e);return r; }
        @Override public int getSlotLimit(int s) { return 64; } @Override public boolean isItemValid(int s, ItemStack st) { return true; }
    }
    private class CableFluidHandler implements IFluidHandler {
        @Override public int getTanks() { return 1; } @Override public FluidStack getFluidInTank(int t) { return fluidBuffer; }
        @Override public int getTankCapacity(int t) { return tier.liquidTransfer; } @Override public boolean isFluidValid(int t, FluidStack s) { return true; }
        @Override public int fill(FluidStack r, FluidAction a) { if(r.isEmpty())return 0; int sp=getTankCapacity(0)-fluidBuffer.getAmount(); if(fluidBuffer.isEmpty()||fluidBuffer.is(r.getFluid())){int f=Math.min(r.getAmount(),sp); if(a.execute()){if(fluidBuffer.isEmpty())fluidBuffer=r.copy();else fluidBuffer.grow(f);syncToClient();} return f;} return 0; }
        @Override public FluidStack drain(int m, FluidAction a) { if(fluidBuffer.isEmpty())return FluidStack.EMPTY; int d=Math.min(m,fluidBuffer.getAmount()); FluidStack r=fluidBuffer.copy();r.setAmount(d); if(a.execute()){fluidBuffer.shrink(d);syncToClient();} return r; }
        @Override public FluidStack drain(FluidStack r, FluidAction a) { if(!fluidBuffer.is(r.getFluid()))return FluidStack.EMPTY; return drain(r.getAmount(),a); }
    }
    private class CableGasHandler implements IGasHandler {
        @Override public int fill(GasStack r, boolean sim) { if(r.isEmpty())return 0; int sp=tier.gasTransfer-gasBuffer.getAmount(); if(gasBuffer.isEmpty()||gasBuffer.isGasEqual(r)){int f=Math.min(r.getAmount(),sp); if(!sim){if(gasBuffer.isEmpty())gasBuffer=r.copy();else gasBuffer.setAmount(gasBuffer.getAmount()+f);syncToClient();} return f;} return 0; }
        @Override public GasStack drain(int m, boolean sim) { if(gasBuffer.isEmpty())return GasStack.EMPTY; int d=Math.min(m,gasBuffer.getAmount()); GasStack r=gasBuffer.copy();r.setAmount(d); if(!sim){gasBuffer.setAmount(gasBuffer.getAmount()-d);if(gasBuffer.isEmpty())gasBuffer=GasStack.EMPTY;syncToClient();} return r; }
        @Override public int getGasAmount() { return gasBuffer.getAmount(); } @Override public int getMaxGasAmount() { return tier.gasTransfer; }
    }
}