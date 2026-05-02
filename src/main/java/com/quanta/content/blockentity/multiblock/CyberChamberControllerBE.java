package com.quanta.content.blockentity.multiblock;

import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaEnergyBE;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class CyberChamberControllerBE extends QuantaEnergyBE {

    public CyberChamberControllerBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CYBER_CHAMBER_CONTROLLER.get(), pos, state, QuantumTier.SUPERPOSED, 500000);
    }

    @Override
    protected int getBaseEnergyCost() { return 100; }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}