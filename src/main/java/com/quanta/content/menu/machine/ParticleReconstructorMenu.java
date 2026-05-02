package com.quanta.content.menu.machine;

import com.quanta.content.blockentity.base.QuantaProcessingBE;
import com.quanta.content.blockentity.machine.ParticleReconstructorBE;
import com.quanta.content.menu.ModMenuTypes;
import com.quanta.content.menu.base.QuantaMenu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class ParticleReconstructorMenu extends QuantaMenu {
    
    public ParticleReconstructorMenu(int containerId, Inventory inv, QuantaProcessingBE be) {
        super(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), containerId, inv, be, 56, 34, 116, 34);
    }
    
    public ParticleReconstructorMenu(int containerId, Inventory inv, ParticleReconstructorBE be, ContainerData data) {
        super(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), containerId, inv, be, data, 56, 34, 116, 34);
    }
    
    @Override
    protected boolean isItemProcessable(ItemStack stack) {
        String path = stack.getItem().getDescriptionId();
        return path.contains("ore") || path.contains("ender_pearl");
    }
}