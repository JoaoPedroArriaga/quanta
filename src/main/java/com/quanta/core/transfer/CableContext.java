package com.quanta.core.transfer;

import com.quanta.api.cable.ICableContext;
import com.quanta.api.cable.IGasHandler;
import com.quanta.api.cable.IQuantaStorage;
import com.quanta.capability.QuantaCapabilities;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class CableContext implements ICableContext {

    private final Level level;
    private final BlockPos pos;
    private final QuantumTier tier;
    private final int[] connectionMasks;
    private final Map<Direction, ConnectionConfig> configs;
    private final int typeMask;
    private final Consumer<Void> dirtyMarker;

    public CableContext(Level level, BlockPos pos, QuantumTier tier,
                        int[] connectionMasks,
                        Map<Direction, ConnectionConfig> configs,
                        int typeMask,
                        Consumer<Void> dirtyMarker) {
        this.level = level;
        this.pos = pos;
        this.tier = tier;
        this.connectionMasks = connectionMasks.clone();
        this.configs = new EnumMap<>(configs);
        this.typeMask = typeMask;
        this.dirtyMarker = dirtyMarker;
    }

    @Override
    public Level getLevel() { return level; }

    @Override
    public BlockPos getPos() { return pos; }

    @Override
    public QuantumTier getTier() { return tier; }

    @Override
    public int getConnectionMask(Direction dir) {
        return connectionMasks[dir.ordinal()];
    }

    @Override
    public ConnectionConfig getConfig(Direction dir) {
        return configs.get(dir);
    }

    @Override
    public boolean canTransfer(Direction dir, TransferType type) {
        return (connectionMasks[dir.ordinal()] & type.bitMask) != 0
            && configs.get(dir).canTransfer(type);
    }

    @Override
    public int getTypeMask() { return typeMask; }

    @Override
    public IQuantaStorage getNeighborQuanta(Direction dir) {
        return level.getCapability(QuantaCapabilities.QUANTA_ENERGY,
            pos.relative(dir), dir.getOpposite());
    }

    @Override
    public IItemHandler getNeighborItems(Direction dir) {
        return level.getCapability(Capabilities.ItemHandler.BLOCK,
            pos.relative(dir), dir.getOpposite());
    }

    @Override
    public IFluidHandler getNeighborFluid(Direction dir) {
        return level.getCapability(Capabilities.FluidHandler.BLOCK,
            pos.relative(dir), dir.getOpposite());
    }

    @Override
    public IGasHandler getNeighborGas(Direction dir) {
        return level.getCapability(QuantaCapabilities.QUANTA_GAS,
            pos.relative(dir), dir.getOpposite());
    }

    @Override
    public void markDirty() {
        dirtyMarker.accept(null);
    }
}