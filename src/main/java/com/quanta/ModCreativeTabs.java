package com.quanta;

import com.quanta.block.ModBlockItems;
import com.quanta.block.ModBlocks;
import com.quanta.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModCreativeTabs {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Quanta.MOD_ID);
    
    public static final Supplier<CreativeModeTab> QUANTA_TAB = CREATIVE_TABS.register("quanta_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.quanta"))
            .icon(() -> new ItemStack(ModItems.QUANTUM_DUST.get()))
            .displayItems((parameters, output) -> {
                
                // ========== ITENS DE CABO/PIPE ==========
                output.accept(ModBlockItems.QUANTA_CABLE.get());
                output.accept(ModBlockItems.QUANTUM_ITEM_PIPE.get());
                output.accept(ModBlockItems.QUANTUM_LIQUID_PIPE.get());
                output.accept(ModBlockItems.QUANTUM_PRESSURIZED_PIPE.get());
                
                // ========== MÁQUINAS ==========
                output.accept(ModBlocks.PARTICLE_RECONSTRUCTOR.get());
                output.accept(ModBlocks.ENERGY_QUANTIFIER.get());
                output.accept(ModBlocks.QUANTA_COLLAPSER.get());
                output.accept(ModBlocks.QUANTA_INFUSER.get());
                output.accept(ModBlocks.TUNEL_RELAY.get());

                // ========== ITENS ==========
                output.accept(ModItems.ENDER_DUST.get());
                output.accept(ModItems.QUANTUM_DUST.get());
                output.accept(ModItems.QUANTUM_STEEL.get());
                output.accept(ModItems.QUANTUM_STEEL_SHEET.get());
                output.accept(ModItems.QUANTUM_STEEL_WIRE.get());
                output.accept(ModItems.QUANTUM_CIRCUIT.get());
                output.accept(ModItems.QUANTUM_COIL.get());
                output.accept(ModItems.ESSENCE_OF_ORDER.get());
                output.accept(ModItems.ESSENCE_OF_CHAOS.get());
                output.accept(ModItems.QUANTUM_CRYSTAL.get());
                output.accept(ModItems.ENTANGLED_CORE.get());
                output.accept(ModItems.QUANTUM_PROCESSOR.get());
                output.accept(ModItems.OBSERVER_LENS.get());
                output.accept(ModItems.CHAOS_FRAGMENT.get());
                output.accept(ModItems.QUANTUM_ALLOY.get());
                output.accept(ModItems.STABILIZED_SINGULARITY.get());
            })
            .build());
}