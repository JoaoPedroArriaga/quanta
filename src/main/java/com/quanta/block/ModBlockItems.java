package com.quanta.block;

import com.quanta.Quanta;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockItems {
    public static final DeferredRegister.Items ITEMS =
        DeferredRegister.createItems(Quanta.MOD_ID);
    
    public static final DeferredItem<Item> PARTICLE_RECONSTRUCTOR = ITEMS.register(
        "particle_reconstructor",
        () -> new BlockItem(ModBlocks.PARTICLE_RECONSTRUCTOR.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> ENERGY_QUANTIFIER = ITEMS.register(
        "energy_quantifier",
        () -> new BlockItem(ModBlocks.ENERGY_QUANTIFIER.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTA_COLLAPSER = ITEMS.register(
        "quanta_collapser",
        () -> new BlockItem(ModBlocks.QUANTA_COLLAPSER.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTUM_CABLE = ITEMS.register(
        "quantum_cable",
        () -> new BlockItem(ModBlocks.QUANTUM_CABLE.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> TUNEL_RELAY = ITEMS.register(
        "tunel_relay",
        () -> new BlockItem(ModBlocks.TUNEL_RELAY.get(), new Item.Properties()));
}