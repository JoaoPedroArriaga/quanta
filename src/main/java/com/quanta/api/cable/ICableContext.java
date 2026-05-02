package com.quanta.api.cable;

import com.quanta.core.tier.QuantumTier;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

/**
 * Read-only context passed to cable components during tick.
 * Provides access to neighbor capabilities without direct coupling.
 */
public interface ICableContext {
    
    Level getLevel();
    BlockPos getPos();
    QuantumTier getTier();
    int getConnectionMask(Direction dir);
    ConnectionConfig getConfig(Direction dir);
    boolean canTransfer(Direction dir, TransferType type);
    int getTypeMask();

    IQuantaStorage getNeighborQuanta(Direction dir);
    IItemHandler getNeighborItems(Direction dir);
    IFluidHandler getNeighborFluid(Direction dir);
    IGasHandler getNeighborGas(Direction dir);

    void markDirty();
}
