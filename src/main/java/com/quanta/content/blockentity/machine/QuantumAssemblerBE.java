package com.quanta.content.blockentity.machine;

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

public class QuantumAssemblerBE extends QuantaProcessingBE {

    private static final int INVENTORY_SLOTS = 9;
    private static final int BASE_MAX_PROGRESS = 100;

    public QuantumAssemblerBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTUM_ASSEMBLER.get(), pos, state,
              INVENTORY_SLOTS, QuantumTier.DECOHERENT, BASE_MAX_PROGRESS);
    }

    @Override
    protected int getBaseEnergyCost() { return 15; }

    @Override
    protected boolean canProcess(ItemStack input) { return !input.isEmpty(); }

    @Override
    protected void doProcess() {
        // Placeholder for recipe-based crafting
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}