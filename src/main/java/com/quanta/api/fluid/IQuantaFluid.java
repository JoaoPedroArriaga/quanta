package com.quanta.api.fluid;

/**
 * Interface for Quanta fluids.
 * Implemented by Liquid Quanta, Liquid Chaos, Liquid Order, etc.
 */
public interface IQuantaFluid {
    
    String getFluidId();
    String getDisplayName();
    int getColor();
    boolean isStable();
    int getTemperature();
    boolean canBeUsedAsStabilizer();
    float getStabilizationEfficiency();
    boolean canBeUsedAsFuel();
    int getFuelEnergyDensity();
}
