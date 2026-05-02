package com.quanta.capability;

import com.quanta.Quanta;
import com.quanta.api.cable.IGasHandler;
import com.quanta.api.cable.IQuantaStorage;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;

public class QuantaCapabilities {

    public static final BlockCapability<IQuantaStorage, Direction> QUANTA_ENERGY =
        BlockCapability.createSided(
            ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "quanta_energy"),
            IQuantaStorage.class
        );

    public static final BlockCapability<IGasHandler, Direction> QUANTA_GAS =
        BlockCapability.createSided(
            ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "quanta_gas"),
            IGasHandler.class
        );
}