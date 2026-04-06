package com.quanta.client;

import com.quanta.Quanta;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Quanta.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Quanta.LOGGER.info("Cliente do Quanta inicializado!");
    }
}