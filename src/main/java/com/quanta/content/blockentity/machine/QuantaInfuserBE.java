package com.quanta.content.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaProcessingBE;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class QuantaInfuserBE extends QuantaProcessingBE {

    private static final int INVENTORY_SLOTS = 3;
    private static final int BASE_MAX_PROGRESS = 200;
    public static final int SLOT_CATALYST = 1;
    public static final int SLOT_OUTPUT_INFUSER = 2;

    public QuantaInfuserBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTA_INFUSER.get(), pos, state,
              INVENTORY_SLOTS, QuantumTier.DECOHERENT, BASE_MAX_PROGRESS);
    }

    @Override
    protected int getBaseEnergyCost() { return QuantaConfig.particleCost() * 2; }

    @Override
    protected int getOutputSlot() { return SLOT_OUTPUT_INFUSER; }

    @Override
    protected boolean canProcess(ItemStack input) { return !input.isEmpty(); }

    @Override
    protected void doProcess() {
        ItemStack input = inventory.getItem(SLOT_INPUT);
        ItemStack catalyst = inventory.getItem(SLOT_CATALYST);
        ItemStack output = inventory.getItem(SLOT_OUTPUT_INFUSER);
        ItemStack result = input.copy();
        result.setCount(result.getCount() * 2);
        input.shrink(1);
        if (!catalyst.isEmpty()) catalyst.shrink(1);
        if (output.isEmpty()) inventory.setItem(SLOT_OUTPUT_INFUSER, result);
        else output.grow(result.getCount());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }
}