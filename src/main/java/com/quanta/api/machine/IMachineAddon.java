package com.quanta.api.machine;

import net.minecraft.resources.ResourceLocation;

/**
 * Interface for machine addons (Speed Coil, Energy Coil, Stabilizer, etc.).
 * Addons modify machine behavior when installed.
 */
public interface IMachineAddon {
    
    ResourceLocation getId();
    String getDisplayName();
    
    float getSpeedMultiplier();
    float getEnergyMultiplier();
    float getFailureModifier();
    float getYieldBonus();
    int getMaxStackSize();
    int getMinimumTier();
}
