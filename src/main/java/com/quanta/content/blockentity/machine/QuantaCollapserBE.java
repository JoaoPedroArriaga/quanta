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

public class QuantaCollapserBE extends QuantaEnergyBE {
    
    private int feBuffer = 0;
    private final int fePerQuanta;
    private int tickCounter = 0;
    private static final int CONVERSION_INTERVAL = 5;
    private static final int QUANTA_PER_CONVERSION = 10;
    
    public QuantaCollapserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTA_COLLAPSER.get(), pos, state, QuantumTier.DECOHERENT);
        this.fePerQuanta = QuantaConfig.fePerQuanta();
    }
    
    public void tickServer() {
        if (level == null || level.isClientSide) return;
        
        tickCounter++;
        if (tickCounter < CONVERSION_INTERVAL) return;
        tickCounter = 0;
        
        int quantaToConvert = Math.min(QUANTA_PER_CONVERSION, energy.getQuantaStored());
        if (quantaToConvert > 0) {
            int extracted = energy.extractQuanta(quantaToConvert, false);
            feBuffer += extracted * fePerQuanta;
            if (feBuffer > getMaxEnergyStored()) feBuffer = getMaxEnergyStored();
            setChangedAndSync();
        }
    }
    
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = Math.min(feBuffer, maxExtract);
        if (!simulate && extracted > 0) {
            feBuffer -= extracted;
            setChangedAndSync();
        }
        return extracted;
    }
    
    public int getEnergyStored() { return feBuffer; }
    public int getMaxEnergyStored() { return 10000; }
    public boolean canExtract() { return feBuffer > 0; }
    public boolean canReceive() { return false; }
    
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