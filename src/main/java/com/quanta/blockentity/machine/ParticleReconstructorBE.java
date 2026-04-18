package com.quanta.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.block.machine.base.MachineTier;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.blockentity.base.QuantaProcessingBE;
import com.quanta.menu.ParticleReconstructorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ParticleReconstructorBE extends QuantaProcessingBE {
    
    private static final int INVENTORY_SLOTS = 2;
    private static final int ENERGY_CAPACITY = 10000;
    private static final int ENERGY_TRANSFER = 100;
    private static final int BASE_MAX_PROGRESS = 100;
    
    private static final int HASH_ORE = "ore".hashCode();
    private static final int HASH_ENDER_PEARL = "ender_pearl".hashCode();
    
    public ParticleReconstructorBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARTICLE_RECONSTRUCTOR.get(), pos, state,
              INVENTORY_SLOTS, ENERGY_CAPACITY, ENERGY_TRANSFER, 
              BASE_MAX_PROGRESS, MachineTier.BASIC);
    }
    
    @Override
    protected int getBaseEnergyCost() {
        return QuantaConfig.particleCost();
    }
    
    @Override
    protected boolean canProcess(ItemStack input) {
        String path = input.getItem().getDescriptionId();
        int hash = path.hashCode();
        return hash == HASH_ORE || hash == HASH_ENDER_PEARL || 
               path.contains("ore") || path.contains("ender_pearl");
    }
    
    @Override
    protected void doProcess() {
        ItemStack input = inventory.getStackInSlot(SLOT_INPUT);
        ItemStack output = inventory.getStackInSlot(SLOT_OUTPUT);
        
        int multiplier = QuantaConfig.particleOutputMin();
        ItemStack result = input.copy();
        result.setCount(result.getCount() * multiplier);
        
        input.shrink(1);
        
        if (output.isEmpty()) {
            inventory.setStackInSlot(SLOT_OUTPUT, result);
        } else {
            output.grow(result.getCount());
        }
    }
    
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ParticleReconstructorMenu(id, inv, this, this.getContainerData());
    }
    
    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }
}