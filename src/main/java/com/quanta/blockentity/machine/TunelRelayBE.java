package com.quanta.blockentity.machine;

import com.quanta.blockentity.ModBlockEntities;
import com.quanta.blockentity.base.QuantaBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class TunelRelayBE extends QuantaBlockEntity {

    public TunelRelayBE(BlockPos pos, BlockState state){
        super(ModBlockEntities.TUNEL_RELAY.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return null;
    }
    
}
