package com.quanta.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.block.machine.base.MachineTier;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.blockentity.base.QuantaProcessingBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class QuantaInfuserBE extends QuantaProcessingBE {
    
    private static final int INVENTORY_SLOTS = 3;
    private static final int ENERGY_CAPACITY = 20000;
    private static final int ENERGY_TRANSFER = 200;
    private static final int BASE_MAX_PROGRESS = 200;
    
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_CATALYST = 1;
    private static final int SLOT_OUTPUT = 2;
    
    public QuantaInfuserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTA_INFUSER.get(), pos, state,
              INVENTORY_SLOTS, ENERGY_CAPACITY, ENERGY_TRANSFER, 
              BASE_MAX_PROGRESS, MachineTier.BASIC);
    }
    
    @Override
    protected int getBaseEnergyCost() {
        return QuantaConfig.particleCost() * 2;
    }
    
    @Override
    protected boolean canProcess(ItemStack input) {
        return !input.isEmpty();
    }
    
    @Override
    protected void doProcess() {
        ItemStack input = inventory.getStackInSlot(SLOT_INPUT);
        ItemStack catalyst = inventory.getStackInSlot(SLOT_CATALYST);
        ItemStack output = inventory.getStackInSlot(SLOT_OUTPUT);
        
        ItemStack result = input.copy();
        result.setCount(result.getCount() * 2);
        
        input.shrink(1);
        if (!catalyst.isEmpty()) {
            catalyst.shrink(1);
        }
        
        if (output.isEmpty()) {
            inventory.setStackInSlot(SLOT_OUTPUT, result);
        } else {
            output.grow(result.getCount());
        }
    }
}