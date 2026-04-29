package com.quanta.block;

import com.quanta.Quanta;
import com.quanta.item.cable.QuantaCableItem;
import com.quanta.item.cable.QuantumItemPipeItem;
import com.quanta.item.cable.QuantumLiquidPipeItem;
import com.quanta.item.cable.QuantumPressurizedPipeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockItems {
    public static final DeferredRegister.Items ITEMS =
        DeferredRegister.createItems(Quanta.MOD_ID);
    
    // MÁQUINAS
    public static final DeferredItem<Item> PARTICLE_RECONSTRUCTOR = ITEMS.register(
        "particle_reconstructor",
        () -> new BlockItem(ModBlocks.PARTICLE_RECONSTRUCTOR.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> ENERGY_QUANTIFIER = ITEMS.register(
        "energy_quantifier",
        () -> new BlockItem(ModBlocks.ENERGY_QUANTIFIER.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTA_COLLAPSER = ITEMS.register(
        "quanta_collapser",
        () -> new BlockItem(ModBlocks.QUANTA_COLLAPSER.get(), new Item.Properties()));

    public static final DeferredItem<Item> QUANTA_INFUSER = ITEMS.register(
        "quanta_infuser",
        () -> new BlockItem(ModBlocks.QUANTA_INFUSER.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> TUNEL_RELAY = ITEMS.register(
        "tunel_relay",
        () -> new BlockItem(ModBlocks.TUNEL_RELAY.get(), new Item.Properties()));

    // ========== CABOS E PIPES ==========
    public static final DeferredItem<Item> QUANTA_CABLE = ITEMS.register(
        "quanta_cable", 
        () -> new QuantaCableItem(ModBlocks.QUANTUM_CABLE.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTUM_ITEM_PIPE = ITEMS.register(
        "quantum_item_pipe", 
        () -> new QuantumItemPipeItem(ModBlocks.QUANTUM_CABLE.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTUM_LIQUID_PIPE = ITEMS.register(
        "quantum_liquid_pipe", 
        () -> new QuantumLiquidPipeItem(ModBlocks.QUANTUM_CABLE.get(), new Item.Properties()));
    
    public static final DeferredItem<Item> QUANTUM_PRESSURIZED_PIPE = ITEMS.register(
        "quantum_pressurized_pipe", 
        () -> new QuantumPressurizedPipeItem(ModBlocks.QUANTUM_CABLE.get(), new Item.Properties()));
}