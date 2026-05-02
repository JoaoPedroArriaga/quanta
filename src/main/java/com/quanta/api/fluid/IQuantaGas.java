package com.quanta.api.fluid;

/**
 * Interface for Quanta gases.
 * Separate from fluids due to different physics (pressure, compressibility).
 */
public interface IQuantaGas {
    
    String getGasId();
    String getDisplayName();
    int getColor();
    float getPressure();
    boolean isFlammable();
    boolean isRadioactive();
    boolean canBeBurned();
    int getBurnEnergyDensity();
}
