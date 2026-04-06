package com.quanta.energy;

import com.quanta.api.IQuantumEnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class QuantumEnergyStorage implements IQuantumEnergyStorage, IEnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private boolean inSuperposition;
    private int totalExtracted = 0;
    private int totalReceived = 0;
    
    private static final int FE_PER_QUANTA = 100;
    
    public QuantumEnergyStorage(int capacity, int maxTransfer) {
        this.capacity = capacity;
        this.maxReceive = maxTransfer;
        this.maxExtract = maxTransfer;
        this.energy = 0;
        this.inSuperposition = false;
    }
    
    // ========== MÉTODOS DO IEnergyStorage (FE) ==========
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int quantaToReceive = maxReceive / FE_PER_QUANTA;
        int received = Math.min(capacity - energy, Math.min(this.maxReceive, quantaToReceive));
        
        if (!simulate) {
            energy += received;
            totalReceived += received;
        }
        return received * FE_PER_QUANTA;
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int quantaToExtract = maxExtract / FE_PER_QUANTA;
        int extracted = Math.min(energy, Math.min(this.maxExtract, quantaToExtract));
        
        if (!simulate) {
            energy -= extracted;
            totalExtracted += extracted;
        }
        return extracted * FE_PER_QUANTA;
    }
    
    @Override
    public int getEnergyStored() {
        return energy * FE_PER_QUANTA;
    }
    
    @Override
    public int getMaxEnergyStored() {
        return capacity * FE_PER_QUANTA;
    }
    
    @Override
    public boolean canExtract() {
        return true;
    }
    
    @Override
    public boolean canReceive() {
        return true;
    }
    
    // ========== MÉTODOS DO IQuantumEnergyStorage ==========
    
    @Override
    public int getQuantumStored() {
        return energy;
    }
    
    @Override
    public int getQuantumCapacity() {
        return capacity;
    }
    
    @Override
    public int addQuantum(int amount) {
        int received = Math.min(capacity - energy, amount);
        energy += received;
        totalReceived += received;
        return received;
    }
    
    @Override
    public int extractQuantum(int amount, boolean allowTunnel) {
        int extracted = Math.min(energy, amount);
        
        if (allowTunnel && inSuperposition) {
            if (Math.random() < 0.1) {
                extracted = Math.min(energy, amount * 2);
            }
        }
        
        energy -= extracted;
        totalExtracted += extracted;
        return extracted;
    }
    
    @Override
    public boolean isInSuperposition() {
        return inSuperposition;
    }
    
    @Override
    public void setSuperposition(boolean enabled) {
        this.inSuperposition = enabled;
    }
    
    @Override
    public int getTotalExtracted() {
        return totalExtracted;
    }
    
    @Override
    public int getTotalReceived() {
        return totalReceived;
    }
    
    // ========== MÉTODOS UTILITÁRIOS ==========
    
    public void setEnergy(int energy) {
        this.energy = Math.min(capacity, Math.max(0, energy));
    }
    
    public float getEnergyPercentage() {
        return (float) energy / capacity;
    }
}