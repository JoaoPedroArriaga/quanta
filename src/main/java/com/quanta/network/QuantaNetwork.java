package com.quanta.network;

import com.quanta.Quanta;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = Quanta.MOD_ID)
public class QuantaNetwork {
    
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Registrar packets aqui quando necessário
        Quanta.LOGGER.info("Quanta Network inicializada!");
    }
}