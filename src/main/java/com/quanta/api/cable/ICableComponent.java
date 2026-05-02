package com.quanta.api.cable;

import com.quanta.core.transfer.TransferType;
import net.minecraft.core.Direction;

/**
 * Component interface for cable transfer handlers.
 * Each transfer type (QUANTA, ITEM, FLUID, GAS) has its own component.
 * Other mods can implement this to add custom transfer types.
 */
public interface ICableComponent {
    
    TransferType getType();

    int getBufferAmount();
    int getMaxBuffer();
    int getLastTransfer();

    boolean canExtractFrom(Direction dir);
    boolean canInsertTo(Direction dir);
    void tick(ICableContext context);

    default void onAdded(ICableContext context) {}

    default void onRemoved(ICableContext context) {}
}