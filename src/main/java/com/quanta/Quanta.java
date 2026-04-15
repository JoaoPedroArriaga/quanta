package com.quanta;

import com.quanta.block.ModBlockItems;
import com.quanta.block.ModBlocks;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.capability.QuantaCapabilities;
import com.quanta.component.ModDataComponents;
import com.quanta.item.ModItems;
import com.quanta.menu.ModMenuTypes;
import com.quanta.network.QuantaNetwork;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Quanta.MOD_ID)
public class Quanta {
    public static final String MOD_ID = "quanta";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Quanta(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Configuração
        modContainer.registerConfig(Type.COMMON, QuantaConfig.COMMON_SPEC);
        
        // 2. Registro de componentes
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        
        // 3. Event listeners
        modEventBus.addListener(QuantaCapabilities::register);
        modEventBus.addListener(QuantaNetwork::register);
        
        LOGGER.info("⚡ Quanta mod initialized! ⚡");
    }
}