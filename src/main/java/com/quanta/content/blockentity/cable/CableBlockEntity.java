package com.quanta.content.blockentity.cable;

import com.quanta.api.cable.IGasHandler;
import com.quanta.api.cable.IQuantaStorage;
import com.quanta.client.animation.ClientAnimationHandler;
import com.quanta.client.renderer.state.CableRenderState;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.cable.component.*;
import com.quanta.core.tier.QuantumTier;
import com.quanta.core.transfer.*;
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
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.*;

public class CableBlockEntity extends BlockEntity {

    private final CableTypeManager typeManager;
    private final CableConnectionManager connectionManager;
    private final CableNetworkProxy networkProxy;
    private final CableTransferExecutor transferExecutor;

    private QuantumTier tier = QuantumTier.DECOHERENT;
    private int tickCounter;
    private CableRenderState renderState;

    private IQuantaStorage quantaHandler;
    private IItemHandler itemHandler;
    private IFluidHandler fluidHandler;
    private IGasHandler gasHandler;

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABLE.get(), pos, state);
        this.typeManager = new CableTypeManager(this::onTypesChanged);
        this.connectionManager = new CableConnectionManager(this, typeManager);
        this.networkProxy = new CableNetworkProxy(this);
        this.transferExecutor = new CableTransferExecutor(this, typeManager, connectionManager, networkProxy);
    }

    // ==================== PUBLIC ACCESSORS ====================

    public CableTypeManager getTypeManager() { return typeManager; }
    public CableConnectionManager getConnectionManager() { return connectionManager; }
    public CableNetworkProxy getNetworkProxy() { return networkProxy; }
    public CableTransferExecutor getTransferExecutor() { return transferExecutor; }

    public void invalidateCache() { networkProxy.invalidateCache(); }

    // ==================== TYPE DELEGATION ====================

    public boolean addType(TransferType t) { return typeManager.addType(t); }
    public boolean removeType(TransferType t) { return typeManager.removeType(t); }
    public boolean hasType(TransferType t) { return typeManager.hasType(t); }
    public List<TransferType> getTypes() { return typeManager.getTypes(); }
    public int getTypeMask() { return typeManager.getTypeMask(); }
    public TransferType getBaseType() { return typeManager.getBaseType(); }

    // ==================== CONNECTION DELEGATION ====================

    public void updateConnections() { connectionManager.updateConnections(); }
    public int getConnectionMask(Direction d) { return connectionManager.getConnectionMask(d); }
    public boolean hasConnection(Direction d, TransferType t) { return connectionManager.hasConnection(d, t); }
    public ConnectionConfig getConfig(Direction d) { return connectionManager.getConfig(d); }

    public void setConfig(Direction d, ConnectionConfig c) {
        ConnectionConfig old = connectionManager.getConfig(d);
        connectionManager.setConfig(d, c);
        setChanged();
        updateConnections();
        syncToClient();

        if (old != null && old.getMode().isEnabled() != c.getMode().isEnabled()) {
            networkProxy.invalidateCache();
            connectionManager.markDirty();
            if (level != null && !level.isClientSide) {
                networkProxy.rebuildNetwork();
                networkProxy.clearAllBuffers();
            }
        }
    }

    public boolean hasExternalConnection(Direction dir, TransferType type) {
        return connectionManager.hasExternalConnection(dir, type);
    }

    public int getActiveTransferMask() { return transferExecutor.getActiveTransferMask(); }

    // ==================== CAPABILITIES ====================

    public IQuantaStorage getQuantaStorage() {
        if (quantaHandler == null) quantaHandler = new NetQuanta();
        return quantaHandler;
    }

    public IItemHandler getItemHandler() {
        if (itemHandler == null) itemHandler = new NetItems();
        return itemHandler;
    }

    public IFluidHandler getFluidHandler() {
        if (fluidHandler == null) fluidHandler = new NetFluid();
        return fluidHandler;
    }

    public IGasHandler getGasHandler() {
        if (gasHandler == null) gasHandler = new NetGas();
        return gasHandler;
    }

    // ==================== TICK ====================

    public void tick() {
        if (level == null || level.isClientSide) return;
        tickCounter++;
        networkProxy.setTickCounter(tickCounter);

        if (connectionManager.isDirty()) {
            connectionManager.updateConnections();
            networkProxy.rebuildNetwork();
            connectionManager.markClean();
            networkProxy.invalidateCache();
        }

        if (tickCounter % 10 == 0) {
            networkProxy.rebuildNetwork();
            networkProxy.invalidateCache();
        }

        var net = networkProxy.getNetwork();
        if (net == null) return;

        net.updateCapacities(tier.energyTransfer * 20, tier.itemTransfer, tier.liquidTransfer, tier.gasTransfer);

        int oldMask = transferExecutor.getActiveTransferMask();
        transferExecutor.executeTick();

        if (transferExecutor.getActiveTransferMask() != oldMask) {
            syncToClient();
        }
    }

    // ==================== LIFECYCLE ====================

    private void onTypesChanged() {
        connectionManager.markDirty();
        renderState = null;
        setChanged();
        syncToClient();
        if (level != null && !level.isClientSide) {
            networkProxy.rebuildNetwork();
            networkProxy.notifyNetwork();
        }
    }

    public void scheduleConnectionUpdate() {
        connectionManager.markDirty();
        renderState = null;
    }

    public void onRemoved() {
        if (level != null && !level.isClientSide) {
            networkProxy.clearAllBuffers();
            networkProxy.removeCable();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null) {
            if (level.isClientSide) {
                ClientAnimationHandler.registerAnimatedBlock(worldPosition);
                requestModelDataUpdate();
            } else {
                scheduleConnectionUpdate();
            }
        }
    }

    @Override
    public void setRemoved() {
        if (level != null && level.isClientSide) {
            ClientAnimationHandler.unregisterAnimatedBlock(worldPosition);
        }
        super.setRemoved();
        onRemoved();
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        networkProxy.invalidateCache();
    }

    public QuantumTier getTier() { return tier; }

    public void upgradeTier(QuantumTier nt) {
        if (nt.ordinal() > tier.ordinal()) {
            tier = nt;
            setChanged();
            syncToClient();
        }
    }

    public void syncToClient() {
        if (level != null && !level.isClientSide) {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // ==================== SYNC ====================

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider r) {
        CompoundTag tag = super.getUpdateTag(r);
        tag.putIntArray("types", typeManager.getTypeIds());
        tag.putIntArray("masks", connectionManager.getConnectionMasks());
        tag.putByte("tier", (byte) tier.ordinal());
        CompoundTag configTag = new CompoundTag();
        for (Direction d : Direction.values()) {
            configTag.put(d.getName(), connectionManager.getConfig(d).save());
        }
        tag.put("configs", configTag);
        tag.putInt("activeMask", transferExecutor.getActiveTransferMask());
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider r) {
        super.handleUpdateTag(tag, r);
        if (level != null && level.isClientSide) {
            typeManager.loadFromIds(tag.getIntArray("types"));

            int[] masks = tag.getIntArray("masks");
            if (masks.length == 6) {
                connectionManager.setConnectionMasks(masks);
            }

            if (tag.contains("configs")) {
                CompoundTag configTag = tag.getCompound("configs");
                for (Direction d : Direction.values()) {
                    if (configTag.contains(d.getName())) {
                        ConnectionConfig c = new ConnectionConfig();
                        c.load(configTag.getCompound(d.getName()));
                        connectionManager.setConfig(d, c);
                    }
                }
            }

            if (tag.contains("activeMask")) {
                transferExecutor.setActiveTransferMask(tag.getInt("activeMask"));
            }

            connectionManager.markClean();
            renderState = null;
            requestModelDataUpdate();
        }
    }

    @Override
    public void onDataPacket(Connection n, ClientboundBlockEntityDataPacket p, HolderLookup.Provider r) {
        CompoundTag tag = p.getTag();
        if (tag != null) handleUpdateTag(tag, r);
    }

    @Override
    public ModelData getModelData() {
        if (renderState == null) renderState = new CableRenderState();
        renderState.update(
            new ArrayList<>(typeManager.getTypes()),
            connectionManager.getConnectionMasks(),
            new EnumMap<>(connectionManager.getConfigs()),
            transferExecutor.getActiveTransferMask()
        );
        return ModelData.builder().with(CableRenderState.PROPERTY, renderState).build();
    }

    @Override
    public void requestModelDataUpdate() {
        super.requestModelDataUpdate();
        renderState = null;
    }

    // ==================== SAVE / LOAD ====================

    @Override
    protected void saveAdditional(CompoundTag t, HolderLookup.Provider r) {
        super.saveAdditional(t, r);
        t.putIntArray("types", typeManager.getTypeIds());
        t.putIntArray("masks", connectionManager.getConnectionMasks());
        t.putByte("tier", (byte) tier.ordinal());
        CompoundTag ct = new CompoundTag();
        for (Direction d : Direction.values()) {
            ct.put(d.getName(), connectionManager.getConfig(d).save());
        }
        t.put("configs", ct);
        t.putInt("activeMask", transferExecutor.getActiveTransferMask());
    }

    @Override
    protected void loadAdditional(CompoundTag t, HolderLookup.Provider r) {
        super.loadAdditional(t, r);
        typeManager.loadFromIds(t.getIntArray("types"));
        int[] s = t.getIntArray("masks");
        if (s.length == 6) connectionManager.setConnectionMasks(s);
        tier = QuantumTier.fromId(t.getByte("tier"));
        if (t.contains("configs")) {
            CompoundTag ct = t.getCompound("configs");
            for (Direction d : Direction.values()) {
                if (ct.contains(d.getName())) {
                    ConnectionConfig c = new ConnectionConfig();
                    c.load(ct.getCompound(d.getName()));
                    connectionManager.setConfig(d, c);
                }
            }
        }
        if (t.contains("activeMask")) {
            transferExecutor.setActiveTransferMask(t.getInt("activeMask"));
        }
        connectionManager.markDirty();
        renderState = null;
        networkProxy.invalidateCache();
    }

    // ==================== INNER CLASSES ====================

    class NetQuanta implements IQuantaStorage {
        @Override
        public int getQuantaStored() {
            var net = networkProxy.getNetwork();
            return net != null ? net.getQuantaStored() : 0;
        }
        @Override
        public int getQuantaCapacity() {
            var net = networkProxy.getNetwork();
            return net != null ? net.getMaxQuanta() : 0;
        }
        @Override
        public int addQuanta(int a) {
            var net = networkProxy.getNetwork();
            return net != null ? net.addQuanta(a) : 0;
        }
        @Override
        public int extractQuanta(int a, boolean at, boolean sim) {
            var net = networkProxy.getNetwork();
            if (net == null) return 0;
            if (sim) return Math.min(a, net.getQuantaStored());
            return net.extractQuanta(a);
        }
        @Override
        public int extractQuanta(int a, boolean at) { return extractQuanta(a, at, false); }
        @Override public boolean isInSuperposition() { return false; }
        @Override public float getSuperpositionChanceBonus() { return 0; }
        @Override public int getTier() { return tier.id; }
        @Override public void addConnection(IQuantaStorage o) {}
        @Override public void removeConnection(IQuantaStorage o) {}
        @Override public List<IQuantaStorage> getConnections() { return List.of(); }
        @Override public int getTotalExtracted() { return 0; }
        @Override public int getTotalReceived() { return 0; }
        @Override public void resetStats() {}
        @Override public int receiveEnergy(int m, boolean s) { return 0; }
        @Override public int extractEnergy(int m, boolean s) { return 0; }
        @Override public int getEnergyStored() { return 0; }
        @Override public int getMaxEnergyStored() { return 0; }
        @Override public boolean canExtract() {
            var net = networkProxy.getNetwork();
            return net != null && net.getQuantaStored() > 0;
        }
        @Override public boolean canReceive() {
            var net = networkProxy.getNetwork();
            return net != null && net.getQuantaStored() < net.getMaxQuanta();
        }
    }

    class NetItems implements IItemHandler {
        @Override
        public int getSlots() {
            var net = networkProxy.getNetwork();
            return net != null ? Math.max(1, net.getItemSlots()) : 0;
        }
        @Override
        public ItemStack getStackInSlot(int s) {
            var net = networkProxy.getNetwork();
            return net != null ? net.getItem(s) : ItemStack.EMPTY;
        }
        @Override
        public ItemStack insertItem(int s, ItemStack st, boolean sim) {
            var net = networkProxy.getNetwork();
            if (net == null) return st;
            if (!sim) net.addItem(st.copy());
            return ItemStack.EMPTY;
        }
        @Override
        public ItemStack extractItem(int s, int a, boolean sim) {
            var net = networkProxy.getNetwork();
            if (net == null) return ItemStack.EMPTY;
            if (sim) {
                if (net.getItemSlots() == 0) return ItemStack.EMPTY;
                return net.getItem(0).copyWithCount(Math.min(a, net.getItemCount()));
            }
            return net.extractItem(a);
        }
        @Override public int getSlotLimit(int s) { return 64; }
        @Override public boolean isItemValid(int s, ItemStack st) { return true; }
    }

    class NetFluid implements IFluidHandler {
        @Override public int getTanks() { return 1; }
        @Override
        public FluidStack getFluidInTank(int t) {
            var net = networkProxy.getNetwork();
            return net != null ? net.getFluid() : FluidStack.EMPTY;
        }
        @Override
        public int getTankCapacity(int t) {
            var net = networkProxy.getNetwork();
            return net != null ? net.getMaxFluid() : 0;
        }
        @Override public boolean isFluidValid(int t, FluidStack s) { return true; }
        @Override
        public int fill(FluidStack r, FluidAction a) {
            var net = networkProxy.getNetwork();
            if (net == null) return 0;
            if (a.simulate()) return Math.min(r.getAmount(), net.getMaxFluid() - net.getFluid().getAmount());
            return net.fillFluid(r);
        }
        @Override
        public FluidStack drain(int m, FluidAction a) {
            var net = networkProxy.getNetwork();
            if (net == null) return FluidStack.EMPTY;
            if (a.simulate()) {
                var f = net.getFluid();
                return f.isEmpty() ? FluidStack.EMPTY : f.copyWithAmount(Math.min(m, f.getAmount()));
            }
            return net.drainFluid(m);
        }
        @Override
        public FluidStack drain(FluidStack r, FluidAction a) {
            var net = networkProxy.getNetwork();
            if (net == null || !net.getFluid().is(r.getFluid())) return FluidStack.EMPTY;
            return drain(r.getAmount(), a);
        }
    }

    class NetGas implements IGasHandler {
        @Override
        public int getGasAmount() {
            var net = networkProxy.getNetwork();
            if (net == null) return 0;
            GasStack tmp = net.drainGas(1);
            if (tmp.isEmpty()) return 0;
            net.fillGas(tmp);
            return tmp.getAmount();
        }
        @Override
        public int getMaxGasAmount() {
            var net = networkProxy.getNetwork();
            return net != null ? net.getMaxGas() : 0;
        }
        @Override
        public int fill(GasStack r, boolean sim) {
            var net = networkProxy.getNetwork();
            if (net == null) return 0;
            if (sim) {
                GasStack tmp = net.drainGas(1);
                int current = tmp.isEmpty() ? 0 : tmp.getAmount();
                if (!tmp.isEmpty()) net.fillGas(tmp);
                return Math.min(r.getAmount(), net.getMaxGas() - current);
            }
            return net.fillGas(r);
        }
        @Override
        public GasStack drain(int m, boolean sim) {
            var net = networkProxy.getNetwork();
            if (net == null) return GasStack.EMPTY;
            if (sim) {
                GasStack g = net.drainGas(1);
                if (g.isEmpty()) return GasStack.EMPTY;
                net.fillGas(g);
                return g.copy();
            }
            return net.drainGas(m);
        }
        @Override
        public boolean isEmpty() {
            var net = networkProxy.getNetwork();
            if (net == null) return true;
            GasStack g = net.drainGas(1);
            if (g.isEmpty()) return true;
            net.fillGas(g);
            return false;
        }
        @Override
        public boolean isFull() {
            var net = networkProxy.getNetwork();
            if (net == null) return false;
            GasStack g = net.drainGas(1);
            if (g.isEmpty()) return false;
            net.fillGas(g);
            return net.getMaxGas() <= g.getAmount();
        }
    }
}