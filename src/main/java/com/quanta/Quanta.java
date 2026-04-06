package com.quanta;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quanta.item.ModItems;

@Mod(Quanta.MOD_ID)
public class Quanta {
    public static final String MOD_ID = "quanta";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Quanta(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);

        LOGGER.info("⚡ Quanta mod inicializado! ⚡");
    }
}
