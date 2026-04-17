package com.quanta.blockentity.base;

import com.quanta.api.IQuantumEnergyStorage;
import com.quanta.energy.QuantumEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * OPTIMIZED: Energy storage with direct field access and packed flags.
 */
public abstract class QuantaEnergyBE extends QuantaBlockEntity {
    
    // ========== BIT PACKED FLAGS ==========
    private byte flags = 0;
    private static final byte FLAG_ENERGY_DIRTY = 0b00000001;
    
    protected final QuantumEnergyStorage energy;
    protected final int energyCapacity;
    protected final int energyMaxTransfer;
    protected final int inventorySlots;
    
    public QuantaEnergyBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                           int inventorySlots, int energyCapacity, int energyMaxTransfer) {
        super(type, pos, state);
        this.inventorySlots = inventorySlots;
        this.energyCapacity = energyCapacity;
        this.energyMaxTransfer = energyMaxTransfer;
        this.energy = new QuantumEnergyStorage(energyCapacity, energyMaxTransfer);
    }
    
    // ========== FAST ACCESSORS (inline-friendly) ==========
    public IQuantumEnergyStorage getEnergyStorage() { return energy; }
    public int getEnergyStoredQuanta() { return energy.getQuantumStored(); }
    public int getEnergyCapacityQuanta() { return energyCapacity; }
    
    public float getEnergyPercent() { 
        return (float) energy.getQuantumStored() / energyCapacity;
    }
    
    // ========== ENERGY OPERATIONS ==========
    protected boolean hasEnoughEnergy(int cost) {
        return energy.getQuantumStored() >= cost;
    }
    
    protected void consumeEnergy(int cost) {
        energy.extractQuantum(cost, false);
        setChangedAndSync();
    }
    
    protected abstract int getEnergyCost();
    
    // ========== NBT ==========
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