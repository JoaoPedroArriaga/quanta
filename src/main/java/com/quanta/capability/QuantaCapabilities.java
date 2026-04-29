package com.quanta.capability;

import com.quanta.api.IQuantumEnergyStorage;
import com.quanta.Quanta;
import com.quanta.blockentity.machine.ParticleReconstructorBE;
import com.quanta.blockentity.machine.QuantaInfuserBE;
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
        var particleReconstructor = ModBlockEntities.PARTICLE_RECONSTRUCTOR.get();
        if (particleReconstructor != null) {
            event.registerBlockEntity(
                QUANTA_ENERGY,
                particleReconstructor,
                (be, side) -> ((ParticleReconstructorBE) be).getEnergyStorage()
            );
        }
        
        var quantaInfuser = ModBlockEntities.QUANTA_INFUSER.get();
        if (quantaInfuser != null) {
            event.registerBlockEntity(
                QUANTA_ENERGY,
                quantaInfuser,
                (be, side) -> ((QuantaInfuserBE) be).getEnergyStorage()
            );
        }
    }
}