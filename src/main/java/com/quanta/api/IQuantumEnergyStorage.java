package com.quanta.api;

public interface IQuantumEnergyStorage {
    // Métodos básicos
    int getQuantumStored();
    int getQuantumCapacity();
    int addQuantum(int amount);
    int extractQuantum(int amount, boolean allowTunnel);
    
    // Estado quântico
    boolean isInSuperposition();
    void setSuperposition(boolean enabled);

    // Para estatísticas
    int getTotalExtracted();
    int getTotalReceived();
}
