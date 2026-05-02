package com.quanta.content.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaEnergyBE;
import com.quanta.core.tier.QuantumTier;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyQuantifierBE extends QuantaEnergyBE {
    
    private final int fePerQuanta;
    
    public EnergyQuantifierBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENERGY_QUANTIFIER.get(), pos, state, QuantumTier.DECOHERENT);
        this.fePerQuanta = QuantaConfig.fePerQuanta();
    }
    
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int quantaToAdd = maxReceive / fePerQuanta;
        if (quantaToAdd <= 0) return 0;
        
        int added = energy.addQuanta(quantaToAdd);
        return added * fePerQuanta;
    }
    
    public int extractEnergy(int maxExtract, boolean simulate) {
        int quantaToExtract = maxExtract / fePerQuanta;
        if (quantaToExtract <= 0) return 0;
        
        int extracted = energy.extractQuanta(quantaToExtract, false, simulate);
        return extracted * fePerQuanta;
    }
    
    public int getEnergyStored() { return energy.getQuantaStored() * fePerQuanta; }
    public int getMaxEnergyStored() { return energy.getQuantaCapacity() * fePerQuanta; }
    public boolean canExtract() { return false; }
    public boolean canReceive() { return true; }
    
    @Override
    protected int getBaseEnergyCost() { return 0; }
    
    @Override
    protected int getEnergyCost() { return 0; }
    
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return null;
    }
    
    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }
}