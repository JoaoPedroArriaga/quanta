package com.quanta.content.blockentity.machine;

import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaEnergyBE;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class GasBurnerBE extends QuantaEnergyBE {

    public GasBurnerBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAS_BURNER.get(), pos, state, QuantumTier.ENTANGLED);
    }

    public void tickServer() {
        if (level == null || level.isClientSide) return;
        energy.addQuanta(50);
        setChangedAndSync();
    }

    @Override
    protected int getBaseEnergyCost() { return 0; }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}