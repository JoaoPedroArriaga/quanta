package com.quanta.api.cable;

import com.quanta.core.transfer.GasStack;

/**
 * Gas handler interface for pressurized gas transfer.
 * Separate from fluids to allow different physics (pressure, temperature).
 */
public interface IGasHandler {
    
    int fill(GasStack resource, boolean simulate);
    GasStack drain(int maxDrain, boolean simulate);
    int getGasAmount();
    int getMaxGasAmount();

    default boolean isEmpty() {
        return getGasAmount() <= 0; 
    }

    default boolean isFull() { 
        return getGasAmount() >= getMaxGasAmount(); 
    }
}