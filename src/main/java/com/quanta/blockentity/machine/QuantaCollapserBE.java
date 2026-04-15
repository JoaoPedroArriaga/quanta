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
 * ULTRA OPTIMIZED - Converts Quanta to FE with tick pacing.
 */
public class QuantaCollapserBE extends QuantaBlockEntity implements IEnergyStorage {
    
    private final QuantumEnergyStorage storage;
    private int feBuffer;
    private static final int MAX_FE_BUFFER = 10000;
    private static final int MAX_QUANTA_TRANSFER = 100;
    private static final int CAPACITY = 10000;
    private final int fePerQuanta;
    
    // Tick pacing - reduces operations
    private int tickCounter = 0;
    private static final int CONVERSION_INTERVAL = 5;
    private static final int QUANTA_PER_CONVERSION = 10;
    
    public QuantaCollapserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTA_COLLAPSER.get(), pos, state);
        this.storage = new QuantumEnergyStorage(CAPACITY, MAX_QUANTA_TRANSFER);
        this.feBuffer = 0;
        this.fePerQuanta = QuantaConfig.fePerQuanta();
    }
    
    public void tickServer() {
        if (level == null || level.isClientSide) return;
        
        tickCounter++;
        if (tickCounter < CONVERSION_INTERVAL) return;
        tickCounter = 0;
        
        // Early exit if no space or no quanta
        if (feBuffer >= MAX_FE_BUFFER) return;
        
        int quantaToConvert = Math.min(QUANTA_PER_CONVERSION, storage.getQuantumStored());
        if (quantaToConvert > 0) {
            int extracted = storage.extractQuantum(quantaToConvert, false);
            feBuffer += extracted * fePerQuanta;
            if (feBuffer > MAX_FE_BUFFER) feBuffer = MAX_FE_BUFFER;
            setChangedAndSync();
        }
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = Math.min(feBuffer, maxExtract);
        if (!simulate && extracted > 0) {
            feBuffer -= extracted;
            setChangedAndSync();
        }
        return extracted;
    }
    
    @Override
    public int getEnergyStored() { return feBuffer; }
    
    @Override
    public int getMaxEnergyStored() { return MAX_FE_BUFFER; }
    
    @Override
    public boolean canExtract() { return feBuffer > 0; }
    
    @Override
    public boolean canReceive() { return false; }
    
    public IQuantumEnergyStorage getQuantaStorage() { return storage; }
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("quanta", storage.getQuantumStored());
        tag.putInt("fe_buffer", feBuffer);
    }
    
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        storage.setEnergy(tag.getInt("quanta"));
        feBuffer = tag.getInt("fe_buffer");
    }
}