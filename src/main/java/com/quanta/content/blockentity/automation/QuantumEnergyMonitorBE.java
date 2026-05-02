package com.quanta.content.blockentity.automation;

import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class QuantumEnergyMonitorBE extends QuantaBlockEntity {

    public QuantumEnergyMonitorBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUANTUM_ENERGY_MONITOR.get(), pos, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}