package com.quanta.item;

import com.quanta.Quanta;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
        public static final DeferredRegister.Items ITEMS = 
                DeferredRegister.createItems(Quanta.MOD_ID);
    
        // ========== TIER 1 - Basic ==========

        public static final DeferredItem<Item> QUANTUM_DUST = 
                ITEMS.registerSimpleItem("quantum_dust");

        public static final DeferredItem<Item> QUANTUM_INGOT = 
                ITEMS.registerSimpleItem("quantum_ingot");
    
        public static final DeferredItem<Item> QUANTUM_CIRCUIT =
                ITEMS.registerSimpleItem("quantum_circuit");

        public static final DeferredItem<Item> QUANTUM_COIL =
                ITEMS.registerSimpleItem("quantum_coil");        

        public static final DeferredItem<Item> ESSENCE_OF_ORDER =
                ITEMS.registerSimpleItem("essence_of_order");
                
        public static final DeferredItem<Item> ESSENCE_OF_CHAOS =
                ITEMS.registerSimpleItem("essence_of_chaos");

        // ========== TIER 2 - Advanced ==========

        public static final DeferredItem<Item> QUANTUM_CRYSTAL =
                ITEMS.registerSimpleItem("quantum_crystal");
        
        public static final DeferredItem<Item> ENTANGLED_CORE =
                ITEMS.registerSimpleItem("entangled_core");

        public static final DeferredItem<Item> QUANTUM_PROCESSOR =
                ITEMS.registerSimpleItem("quantum_processor");

        public static final DeferredItem<Item> OBSERVER_LENS =
                ITEMS.registerSimpleItem("observer_lens");

        public static final DeferredItem<Item> CHAOS_FRAGMENT =
                ITEMS.registerSimpleItem("chaos_fragment");

        // ========== TIER 3 - Elite ==========

        public static final DeferredItem<Item> QUANTUM_ALLOY =
                ITEMS.registerSimpleItem("quantum_alloy");
        
        public static final DeferredItem<Item> STABILIZED_SINGULARITY =
                ITEMS.registerSimpleItem("stabilized_singularity");
}
