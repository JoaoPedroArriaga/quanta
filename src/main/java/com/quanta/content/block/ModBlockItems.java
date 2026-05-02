// ============================================
// com/quanta/content/block/ModBlockItems.java (ATUALIZADO)
// ============================================
package com.quanta.content.block;

import com.quanta.Quanta;
import com.quanta.content.item.cable.CableBlockItem;
import com.quanta.core.transfer.TransferType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Quanta.MOD_ID);

    // Machines
    public static final DeferredItem<Item> ENERGY_QUANTIFIER = ITEMS.register("energy_quantifier",
        () -> new BlockItem(ModBlocks.ENERGY_QUANTIFIER.get(), new Item.Properties()));
    public static final DeferredItem<Item> PARTICLE_RECONSTRUCTOR = ITEMS.register("particle_reconstructor",
        () -> new BlockItem(ModBlocks.PARTICLE_RECONSTRUCTOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTA_COLLAPSER = ITEMS.register("quanta_collapser",
        () -> new BlockItem(ModBlocks.QUANTA_COLLAPSER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTA_INFUSER = ITEMS.register("quanta_infuser",
        () -> new BlockItem(ModBlocks.QUANTA_INFUSER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTA_COLLECTOR = ITEMS.register("quanta_collector",
        () -> new BlockItem(ModBlocks.QUANTA_COLLECTOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTA_ENHANCER = ITEMS.register("quanta_enhancer",
        () -> new BlockItem(ModBlocks.QUANTA_ENHANCER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_ASSEMBLER = ITEMS.register("quantum_assembler",
        () -> new BlockItem(ModBlocks.QUANTUM_ASSEMBLER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_EVAPORATOR = ITEMS.register("quantum_evaporator",
        () -> new BlockItem(ModBlocks.QUANTUM_EVAPORATOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_CONDENSER = ITEMS.register("quantum_condenser",
        () -> new BlockItem(ModBlocks.QUANTUM_CONDENSER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_PRESS = ITEMS.register("quantum_press",
        () -> new BlockItem(ModBlocks.QUANTUM_PRESS.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_WIRE_DRAWER = ITEMS.register("quantum_wire_drawer",
        () -> new BlockItem(ModBlocks.QUANTUM_WIRE_DRAWER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTA_ENGINE = ITEMS.register("quanta_engine",
        () -> new BlockItem(ModBlocks.QUANTA_ENGINE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GAS_BURNER = ITEMS.register("gas_burner",
        () -> new BlockItem(ModBlocks.GAS_BURNER.get(), new Item.Properties()));
    public static final DeferredItem<Item> ENTANGLER = ITEMS.register("entangler",
        () -> new BlockItem(ModBlocks.ENTANGLER.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOID_GENERATOR = ITEMS.register("void_generator",
        () -> new BlockItem(ModBlocks.VOID_GENERATOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_LIQUID_MIXER = ITEMS.register("quantum_liquid_mixer",
        () -> new BlockItem(ModBlocks.QUANTUM_LIQUID_MIXER.get(), new Item.Properties()));
    public static final DeferredItem<Item> TUNEL_RELAY = ITEMS.register("tunel_relay",
        () -> new BlockItem(ModBlocks.TUNEL_RELAY.get(), new Item.Properties()));

    // Multiblocks
    public static final DeferredItem<Item> QUANTUM_FORGE_CONTROLLER = ITEMS.register("quantum_forge_controller",
        () -> new BlockItem(ModBlocks.QUANTUM_FORGE_CONTROLLER.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLACK_HOLE_FORGE_CONTROLLER = ITEMS.register("black_hole_forge_controller",
        () -> new BlockItem(ModBlocks.BLACK_HOLE_FORGE_CONTROLLER.get(), new Item.Properties()));
    public static final DeferredItem<Item> ENTANGLEMENT_REACTOR_CORE = ITEMS.register("entanglement_reactor_core",
        () -> new BlockItem(ModBlocks.ENTANGLEMENT_REACTOR_CORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> PLASMA_FORGE_CONTROLLER = ITEMS.register("plasma_forge_controller",
        () -> new BlockItem(ModBlocks.PLASMA_FORGE_CONTROLLER.get(), new Item.Properties()));
    public static final DeferredItem<Item> CYBER_CHAMBER_CONTROLLER = ITEMS.register("cyber_chamber_controller",
        () -> new BlockItem(ModBlocks.CYBER_CHAMBER_CONTROLLER.get(), new Item.Properties()));

    // Network
    public static final DeferredItem<Item> QUANTUM_TERMINAL = ITEMS.register("quantum_terminal",
        () -> new BlockItem(ModBlocks.QUANTUM_TERMINAL.get(), new Item.Properties()));
    public static final DeferredItem<Item> ENTANGLEMENT_SWITCH = ITEMS.register("entanglement_switch",
        () -> new BlockItem(ModBlocks.ENTANGLEMENT_SWITCH.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_COMPUTER = ITEMS.register("quantum_computer",
        () -> new BlockItem(ModBlocks.QUANTUM_COMPUTER.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_MAINFRAME = ITEMS.register("quantum_mainframe",
        () -> new BlockItem(ModBlocks.QUANTUM_MAINFRAME.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_SUPERCOMPUTER = ITEMS.register("quantum_supercomputer",
        () -> new BlockItem(ModBlocks.QUANTUM_SUPERCOMPUTER.get(), new Item.Properties()));

    // Automation
    public static final DeferredItem<Item> QUANTUM_REDSTONE_RELAY = ITEMS.register("quantum_redstone_relay",
        () -> new BlockItem(ModBlocks.QUANTUM_REDSTONE_RELAY.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_ENERGY_MONITOR = ITEMS.register("quantum_energy_monitor",
        () -> new BlockItem(ModBlocks.QUANTUM_ENERGY_MONITOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_STORAGE_MONITOR = ITEMS.register("quantum_storage_monitor",
        () -> new BlockItem(ModBlocks.QUANTUM_STORAGE_MONITOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_MODE_SWITCHER = ITEMS.register("quantum_mode_switcher",
        () -> new BlockItem(ModBlocks.QUANTUM_MODE_SWITCHER.get(), new Item.Properties()));

    // Calculus
    public static final DeferredItem<Item> BLACK_BOARD = ITEMS.register("black_board",
        () -> new BlockItem(ModBlocks.BLACK_BOARD.get(), new Item.Properties()));

    // Tanks
    public static final DeferredItem<Item> QUANTUM_TANK = ITEMS.register("quantum_tank",
        () -> new BlockItem(ModBlocks.QUANTUM_TANK.get(), new Item.Properties()));
    public static final DeferredItem<Item> CONTAINMENT_TANK = ITEMS.register("containment_tank",
        () -> new BlockItem(ModBlocks.CONTAINMENT_TANK.get(), new Item.Properties()));
    public static final DeferredItem<Item> PRESSURIZED_TANK = ITEMS.register("pressurized_tank",
        () -> new BlockItem(ModBlocks.PRESSURIZED_TANK.get(), new Item.Properties()));

    // Cable items
    public static final DeferredItem<Item> QUANTA_CABLE = ITEMS.register("quanta_cable",
        () -> CableBlockItem.create(TransferType.QUANTA, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_ITEM_PIPE = ITEMS.register("quantum_item_pipe",
        () -> CableBlockItem.create(TransferType.ITEM, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_LIQUID_PIPE = ITEMS.register("quantum_liquid_pipe",
        () -> CableBlockItem.create(TransferType.FLUID, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_PRESSURIZED_PIPE = ITEMS.register("quantum_pressurized_pipe",
        () -> CableBlockItem.create(TransferType.GAS, new Item.Properties()));
}