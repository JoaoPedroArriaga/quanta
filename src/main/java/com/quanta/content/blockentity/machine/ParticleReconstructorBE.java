package com.quanta.content.blockentity.machine;

import com.quanta.QuantaConfig;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaProcessingBE;
import com.quanta.content.menu.machine.ParticleReconstructorMenu;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ParticleReconstructorBE extends QuantaProcessingBE {

    private static final int BASE_MAX_PROGRESS = 100;

    public ParticleReconstructorBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARTICLE_RECONSTRUCTOR.get(), pos, state,
                QuantumTier.DECOHERENT.totalSlots + QuantumTier.DECOHERENT.getAddonSlots(),
                QuantumTier.DECOHERENT, BASE_MAX_PROGRESS);
    }

    @Override
    protected int getBaseEnergyCost() {
        return QuantaConfig.particleCost();
    }

    @Override
    protected boolean canProcess(ItemStack input) {
        return input.getItem().toString().toLowerCase().contains("dust");
    }

    @Override
    protected void doProcess(int inputSlot, int outputSlot) {
        ItemStack input = inventory.getItem(inputSlot);
        ItemStack output = inventory.getItem(outputSlot);
        int multiplier = QuantaConfig.particleOutputMin();
        ItemStack result = input.copy();
        result.setCount(result.getCount() * multiplier);
        input.shrink(1);
        if (output.isEmpty()) {
            inventory.setItem(outputSlot, result);
        } else {
            output.grow(result.getCount());
        }
    }

    public boolean applyUpgradeKit(ItemStack stack) {
        String name = stack.getItem().toString().toLowerCase();
        QuantumTier newTier = null;
        if (name.contains("entangled_upgrade_kit")) newTier = QuantumTier.ENTANGLED;
        else if (name.contains("superposed_upgrade_kit")) newTier = QuantumTier.SUPERPOSED;
        else if (name.contains("singular_upgrade_kit")) newTier = QuantumTier.SINGULAR;
        if (newTier != null && newTier.ordinal() > tier.ordinal()) {
            upgradeTier(newTier);
            return true;
        }
        return false;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ParticleReconstructorMenu(id, inv, this, this.getContainerData());
    }
}