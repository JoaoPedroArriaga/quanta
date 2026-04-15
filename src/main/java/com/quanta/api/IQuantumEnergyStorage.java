package com.quanta.api;

import net.neoforged.neoforge.energy.IEnergyStorage;
import java.util.List;

/**
 * OPTIMIZED API: Added default methods for common operations.
 */
public interface IQuantumEnergyStorage extends IEnergyStorage {
    
    int getQuantumStored();
    int getQuantumCapacity();
    int addQuantum(int amount);
    int extractQuantum(int amount, boolean allowTunnel);
    int extractQuantum(int amount, boolean allowTunnel, boolean simulate);
    
    boolean isInSuperposition();
    void setSuperposition(boolean enabled);
    float getSuperpositionChanceBonus();
    
    int getTotalExtracted();
    int getTotalReceived();
    void resetStats();
    
    void addConnection(IQuantumEnergyStorage other);
    void removeConnection(IQuantumEnergyStorage other);
    List<IQuantumEnergyStorage> getConnections();
    
    int getTier();
    void setTier(int tier);
    
    // ========== DEFAULT METHODS (optimized implementations) ==========
    
    /**
     * Fast check if has enough energy without method call overhead.
     * Default implementation - override for even faster check.
     */
    default boolean hasEnough(int amount) {
        return getQuantumStored() >= amount;
    }
    
    /**
     * Consume energy without return value (faster when you don't need extracted amount).
     */
    default void consume(int amount) {
        extractQuantum(amount, false);
    }
    
    /**
     * Get energy as float percentage (pre-calculated for GUIs).
     */
    default float getFillPercentage() {
        return (float) getQuantumStored() / getQuantumCapacity();
    }
}