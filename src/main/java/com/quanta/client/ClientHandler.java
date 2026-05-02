package com.quanta.client;

import com.quanta.Quanta;
import com.quanta.client.screen.machine.ParticleReconstructorScreen;
import com.quanta.content.menu.ModMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Quanta.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), ParticleReconstructorScreen::new);
    }
}