package com.quanta.content.blockentity.base;

import com.quanta.api.cable.IQuantaStorage;
import com.quanta.core.energy.QuantumEnergyStorage;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class QuantaEnergyBE extends QuantaBlockEntity {

    protected final QuantumEnergyStorage energy;
    protected int cachedEnergyCost;
    protected QuantumTier tier;

    public QuantaEnergyBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                          QuantumTier tier, int customCapacity) {
        super(type, pos, state);
        this.tier = tier;
        this.energy = new QuantumEnergyStorage(tier, customCapacity);
        this.cachedEnergyCost = (int) (getBaseEnergyCost() * tier.energyEfficiency);
    }

    public QuantaEnergyBE(BlockEntityType<?> type, BlockPos pos, BlockState state, QuantumTier tier) {
        this(type, pos, state, tier, tier.energyTransfer * 20);
    }

    public IQuantaStorage getEnergyStorage() { return energy; }
    public int getEnergyStoredQuanta() { return energy.getQuantaStored(); }
    public int getEnergyCapacityQuanta() { return energy.getQuantaCapacity(); }

    protected boolean hasEnoughEnergy(int cost) { return energy.getQuantaStored() >= cost; }

    protected void consumeEnergy(int cost) {
        energy.extractQuanta(cost, false);
        setChangedAndSync();
    }

    public void upgradeTier(QuantumTier newTier) {
        this.tier = newTier;
        energy.upgradeTier(newTier);
        this.cachedEnergyCost = (int) (getBaseEnergyCost() * newTier.energyEfficiency);
        setChangedAndSync();
    }

    protected abstract int getBaseEnergyCost();

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("energy", energy.getQuantaStored());
        tag.putInt("tier", tier.id);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        energy.setEnergy(tag.getInt("energy"));
        tier = QuantumTier.fromId(tag.getInt("tier"));
        cachedEnergyCost = (int) (getBaseEnergyCost() * tier.energyEfficiency);
    }
}