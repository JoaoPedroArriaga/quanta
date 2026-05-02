package com.quanta.api.machine;

/**
 * Interface for machines that have upgradeable tiers.
 */
public interface IMachineTier {
    
    int getTier();
    boolean upgradeTier(int newTier);
    int getEnergyTransferRate();
    float getProcessingSpeed();
    float getEnergyEfficiency();
    int getMaxAddonSlots();
}
