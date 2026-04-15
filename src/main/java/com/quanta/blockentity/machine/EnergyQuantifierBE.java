package com.quanta.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.api.IQuantumEnergyStorage;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.blockentity.base.QuantaBlockEntity;
import com.quanta.energy.QuantumEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * ULTRA OPTIMIZED - Converts FE to Quanta with caching.
 */
public class EnergyQuantifierBE extends QuantaBlockEntity implements IEnergyStorage {
    
    private final QuantumEnergyStorage storage;
    private final int fePerQuanta;
    private static final int CAPACITY = 10000;
    private static final int MAX_TRANSFER = 1000;
    
    // Cache for repeated calls (prevents recomputation)
    private int lastReceiveAmount = -1;
    private int lastReceiveResult = 0;
    
    public EnergyQuantifierBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_QUANTIFIER.get(), pos, state);
        this.storage = new QuantumEnergyStorage(CAPACITY, MAX_TRANSFER);
        this.fePerQuanta = QuantaConfig.fePerQuanta();
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        // Fast path: same amount as last time
        if (maxReceive == lastReceiveAmount && !simulate) {
            return lastReceiveResult;
        }
        
        int quantaToAdd = maxReceive / fePerQuanta;
        if (quantaToAdd <= 0) return 0;
        
        int added = storage.addQuantum(quantaToAdd);
        int result = added * fePerQuanta;
        
        if (!simulate) {
            lastReceiveAmount = maxReceive;
            lastReceiveResult = result;
            setChangedAndSync();
        }
        
        return result;
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) { return 0; }
    
    @Override
    public int getEnergyStored() { return storage.getQuantumStored() * fePerQuanta; }
    
    @Override
    public int getMaxEnergyStored() { return CAPACITY * fePerQuanta; }
    
    @Override
    public boolean canExtract() { return false; }
    
    @Override
    public boolean canReceive() { return true; }
    
    public IQuantumEnergyStorage getQuantaStorage() { return storage; }
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("quanta", storage.getQuantumStored());
    }
    
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        storage.setEnergy(tag.getInt("quanta"));
    }
}