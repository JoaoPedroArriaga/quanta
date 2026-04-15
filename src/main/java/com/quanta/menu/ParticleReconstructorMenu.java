package com.quanta.menu;

import com.quanta.blockentity.machine.ParticleReconstructorBE;
import com.quanta.menu.base.QuantaMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class ParticleReconstructorMenu extends QuantaMenu {
    
    public ParticleReconstructorMenu(int containerId, Inventory inv, ParticleReconstructorBE be) {
        this(containerId, inv, be, new SimpleContainerData(2));
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