package com.quanta.client;

import com.quanta.Quanta;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = Quanta.MOD_ID, value = Dist.CLIENT)
public class ModModelLoaders {

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(
            ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "cable_bundle"),
            new CableBundleGeometry.Loader()
        );
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_core")));
        event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_connection")));
        event.register(ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "entity/particle_orbit")));
    }
}