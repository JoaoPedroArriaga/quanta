package com.quanta.api.cable;

import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.List;

/**
 * Quanta storage interface.
 * Extends IEnergyStorage for FE compatibility with other mods.
 * 1 Quanta = configurable amount of FE (default 100).
 */
public interface IQuantaStorage extends IEnergyStorage {

    int getQuantaStored();
    int getQuantaCapacity();

    int addQuanta(int amount);
    int extractQuanta(int amount, boolean allowTunnel, boolean simulate);
    int extractQuanta(int amount, boolean allowTunnel);

    boolean isInSuperposition();
    float getSuperpositionChanceBonus();
    int getTier();

    void addConnection(IQuantaStorage other);
    void removeConnection(IQuantaStorage other);
    List<IQuantaStorage> getConnections();

    int getTotalExtracted();
    int getTotalReceived();
    void resetStats();

    default boolean hasEnough(int amount) {
        return getQuantaStored() >= amount;
    }

    default float getFillPercentage() {
        int capacity = getQuantaCapacity();
        return capacity > 0 ? (float) getQuantaStored() / capacity : 0f;
    }
}
