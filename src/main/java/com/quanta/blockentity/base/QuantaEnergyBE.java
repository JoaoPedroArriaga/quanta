package com.quanta.blockentity.base;

import com.quanta.api.IQuantumEnergyStorage;
import com.quanta.energy.QuantumEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class QuantaEnergyBE extends QuantaBlockEntity {
    
    protected final QuantumEnergyStorage energy;
    protected final int energyCapacity;
    protected final int energyMaxTransfer;
    protected final int inventorySlots;
    
    // Dirty flag packed into byte
    private byte flags = 0;
    private static final byte FLAG_ENERGY_DIRTY = 0b00000001;
    
    public QuantaEnergyBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                           int inventorySlots, int energyCapacity, int energyMaxTransfer) {
        super(type, pos, state);
        this.inventorySlots = inventorySlots;
        this.energyCapacity = energyCapacity;
        this.energyMaxTransfer = energyMaxTransfer;
        this.energy = new QuantumEnergyStorage(energyCapacity, energyMaxTransfer);
    }
    
    public IQuantumEnergyStorage getEnergyStorage() { return energy; }
    public int getEnergyStoredQuanta() { return energy.getQuantumStored(); }
    public int getEnergyCapacityQuanta() { return energyCapacity; }
    
    public float getEnergyPercent() { 
        return (float) energy.getQuantumStored() / energyCapacity;
    }
    
    protected boolean hasEnoughEnergy(int cost) {
        return energy.getQuantumStored() >= cost;
    }
    
    protected void consumeEnergy(int cost) {
        energy.extractQuantum(cost, false);
        setChangedAndSync();
    }
    
    protected abstract int getEnergyCost();
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("energy", energy.getQuantumStored());
    }
    
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        energy.setEnergy(tag.getInt("energy"));
    }
}