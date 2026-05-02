package com.quanta;

import com.quanta.content.block.ModBlocks;
import com.quanta.content.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Quanta.MOD_ID);

    // ===== QUANTA (MAIN) =====
    public static final Supplier<CreativeModeTab> QUANTA_TAB = CREATIVE_TABS.register("quanta",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.quanta"))
            .icon(() -> new ItemStack(ModItems.QUANTUM_DUST.get()))
            .displayItems((params, output) -> {
                // Machines
                output.accept(ModBlocks.PARTICLE_RECONSTRUCTOR.get());
                output.accept(ModBlocks.ENERGY_QUANTIFIER.get());
                output.accept(ModBlocks.QUANTA_COLLAPSER.get());
                output.accept(ModBlocks.QUANTA_INFUSER.get());
                output.accept(ModBlocks.QUANTA_COLLECTOR.get());
                output.accept(ModBlocks.QUANTA_ENHANCER.get());
                output.accept(ModBlocks.QUANTUM_ASSEMBLER.get());
                output.accept(ModBlocks.QUANTUM_EVAPORATOR.get());
                output.accept(ModBlocks.QUANTUM_CONDENSER.get());
                output.accept(ModBlocks.QUANTUM_PRESS.get());
                output.accept(ModBlocks.QUANTUM_WIRE_DRAWER.get());
                output.accept(ModBlocks.QUANTA_ENGINE.get());
                output.accept(ModBlocks.GAS_BURNER.get());
                output.accept(ModBlocks.ENTANGLER.get());
                output.accept(ModBlocks.VOID_GENERATOR.get());
                output.accept(ModBlocks.QUANTUM_LIQUID_MIXER.get());
                output.accept(ModBlocks.TUNEL_RELAY.get());

                // Multiblocks
                output.accept(ModBlocks.QUANTUM_FORGE_CONTROLLER.get());
                output.accept(ModBlocks.BLACK_HOLE_FORGE_CONTROLLER.get());
                output.accept(ModBlocks.ENTANGLEMENT_REACTOR_CORE.get());
                output.accept(ModBlocks.PLASMA_FORGE_CONTROLLER.get());
                output.accept(ModBlocks.CYBER_CHAMBER_CONTROLLER.get());

                // Network
                output.accept(ModBlocks.QUANTUM_TERMINAL.get());
                output.accept(ModBlocks.ENTANGLEMENT_SWITCH.get());
                output.accept(ModBlocks.QUANTUM_COMPUTER.get());
                output.accept(ModBlocks.QUANTUM_MAINFRAME.get());
                output.accept(ModBlocks.QUANTUM_SUPERCOMPUTER.get());

                // Automation
                output.accept(ModBlocks.QUANTUM_REDSTONE_RELAY.get());
                output.accept(ModBlocks.QUANTUM_ENERGY_MONITOR.get());
                output.accept(ModBlocks.QUANTUM_STORAGE_MONITOR.get());
                output.accept(ModBlocks.QUANTUM_MODE_SWITCHER.get());

                // Tanks
                output.accept(ModBlocks.QUANTUM_TANK.get());
                output.accept(ModBlocks.CONTAINMENT_TANK.get());
                output.accept(ModBlocks.PRESSURIZED_TANK.get());

                // Calculus
                output.accept(ModBlocks.BLACK_BOARD.get());
            })
            .build());

    // ===== MATERIALS =====
    public static final Supplier<CreativeModeTab> MATERIALS_TAB = CREATIVE_TABS.register("quanta_materials",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.quanta.materials"))
            .icon(() -> new ItemStack(ModItems.QUANTUM_CRYSTAL.get()))
            .displayItems((params, output) -> {
                // Tier 0-1
                output.accept(ModItems.ENDER_DUST.get());
                output.accept(ModItems.QUANTUM_DUST.get());
                output.accept(ModItems.QUANTUM_STEEL.get());
                output.accept(ModItems.QUANTUM_STEEL_SHEET.get());
                output.accept(ModItems.QUANTUM_STEEL_WIRE.get());
                output.accept(ModItems.QUANTUM_CIRCUIT.get());
                output.accept(ModItems.QUANTUM_COIL.get());
                output.accept(ModItems.QUANTUM_CRYSTAL.get());
                output.accept(ModItems.ESSENCE_OF_ORDER.get());
                output.accept(ModItems.ESSENCE_OF_CHAOS.get());

                // Tier 2
                output.accept(ModItems.ENTANGLED_CORE.get());
                output.accept(ModItems.QUANTUM_PROCESSOR.get());
                output.accept(ModItems.OBSERVER_LENS.get());
                output.accept(ModItems.CHAOS_FRAGMENT.get());

                // Tier 3
                output.accept(ModItems.QUANTUM_ALLOY.get());
                output.accept(ModItems.STABILIZED_SINGULARITY.get());

                // Upgrade Kits
                output.accept(ModItems.ENTANGLED_UPGRADE_KIT.get());
                output.accept(ModItems.SUPERPOSED_UPGRADE_KIT.get());
                output.accept(ModItems.SINGULAR_UPGRADE_KIT.get());
            })
            .build());

    // ===== TOOLS =====
    public static final Supplier<CreativeModeTab> TOOLS_TAB = CREATIVE_TABS.register("quanta_tools",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.quanta.tools"))
            .icon(() -> new ItemStack(ModItems.QUANTUM_WRENCH.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.QUANTUM_WRENCH.get());
                output.accept(ModItems.QUANTUM_TESTER.get());

                output.accept(ModItems.QUANTA_CABLE.get());
                output.accept(ModItems.QUANTUM_ITEM_PIPE.get());
                output.accept(ModItems.QUANTUM_LIQUID_PIPE.get());
                output.accept(ModItems.QUANTUM_PRESSURIZED_PIPE.get());
            })
            .build());
}