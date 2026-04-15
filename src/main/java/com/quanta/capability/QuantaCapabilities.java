package com.quanta.capability;

import com.quanta.api.IQuantumEnergyStorage;
import com.quanta.Quanta;
import com.quanta.blockentity.machine.ParticleReconstructorBE;
import com.quanta.blockentity.ModBlockEntities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class QuantaCapabilities {
    public static final BlockCapability<IQuantumEnergyStorage, Direction> QUANTA_ENERGY =
        BlockCapability.createSided(
            ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "quanta_energy"),
            IQuantumEnergyStorage.class
        );
    
    public static void register(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
            QUANTA_ENERGY,
            ModBlockEntities.PARTICLE_RECONSTRUCTOR.get(),
            (be, side) -> ((ParticleReconstructorBE) be).getEnergyStorage()
        );
        
        // Comentar até QuantaInfuserBE existir
        // event.registerBlockEntity(
        //     QUANTA_ENERGY,
        //     ModBlockEntities.QUANTA_INFUSER.get(),
        //     (be, side) -> ((QuantaInfuserBE) be).getEnergyStorage()
        // );
    }
}