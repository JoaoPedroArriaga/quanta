package com.quanta.content.item;

import com.quanta.Quanta;
import com.quanta.content.item.cable.CableBlockItem;
import com.quanta.content.item.material.QuantumCrystalItem;
import com.quanta.content.item.material.QuantumDustItem;
import com.quanta.content.item.tool.QuantumTesterItem;
import com.quanta.content.item.tool.QuantumWrenchItem;
import com.quanta.content.item.upgrade.QuantumUpgradeKit;
import com.quanta.core.tier.QuantumTier;
import com.quanta.core.transfer.TransferType;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Quanta.MOD_ID);

    // Materials
    public static final DeferredItem<Item> ENDER_DUST = ITEMS.registerSimpleItem("ender_dust");
    public static final DeferredItem<Item> QUANTUM_DUST = ITEMS.register("quantum_dust",
        () -> new QuantumDustItem(new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_STEEL = ITEMS.registerSimpleItem("quantum_steel");
    public static final DeferredItem<Item> QUANTUM_STEEL_SHEET = ITEMS.registerSimpleItem("quantum_steel_sheet");
    public static final DeferredItem<Item> QUANTUM_STEEL_WIRE = ITEMS.registerSimpleItem("quantum_steel_wire");
    public static final DeferredItem<Item> QUANTUM_CIRCUIT = ITEMS.registerSimpleItem("quantum_circuit");
    public static final DeferredItem<Item> QUANTUM_COIL = ITEMS.registerSimpleItem("quantum_coil");
    public static final DeferredItem<Item> QUANTUM_CRYSTAL = ITEMS.register("quantum_crystal",
        () -> new QuantumCrystalItem(new Item.Properties()));
    public static final DeferredItem<Item> ESSENCE_OF_ORDER = ITEMS.registerSimpleItem("essence_of_order");
    public static final DeferredItem<Item> ESSENCE_OF_CHAOS = ITEMS.registerSimpleItem("essence_of_chaos");
    public static final DeferredItem<Item> ENTANGLED_CORE = ITEMS.registerSimpleItem("entangled_core");
    public static final DeferredItem<Item> QUANTUM_PROCESSOR = ITEMS.registerSimpleItem("quantum_processor");
    public static final DeferredItem<Item> OBSERVER_LENS = ITEMS.registerSimpleItem("observer_lens");
    public static final DeferredItem<Item> CHAOS_FRAGMENT = ITEMS.registerSimpleItem("chaos_fragment");
    public static final DeferredItem<Item> QUANTUM_ALLOY = ITEMS.registerSimpleItem("quantum_alloy");
    public static final DeferredItem<Item> STABILIZED_SINGULARITY = ITEMS.registerSimpleItem("stabilized_singularity");

    // Tools
    public static final DeferredItem<Item> QUANTUM_WRENCH = ITEMS.register("quantum_wrench",
        () -> new QuantumWrenchItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> QUANTUM_TESTER = ITEMS.register("quantum_tester",
        QuantumTesterItem::new);

    // Upgrade Kits
    public static final DeferredItem<Item> ENTANGLED_UPGRADE_KIT = ITEMS.register("entangled_upgrade_kit",
        () -> new QuantumUpgradeKit(new Item.Properties(), QuantumTier.ENTANGLED));
    public static final DeferredItem<Item> SUPERPOSED_UPGRADE_KIT = ITEMS.register("superposed_upgrade_kit",
        () -> new QuantumUpgradeKit(new Item.Properties(), QuantumTier.SUPERPOSED));
    public static final DeferredItem<Item> SINGULAR_UPGRADE_KIT = ITEMS.register("singular_upgrade_kit",
        () -> new QuantumUpgradeKit(new Item.Properties(), QuantumTier.SINGULAR));

    // Cables
    public static final DeferredItem<Item> QUANTA_CABLE = ITEMS.register("quanta_cable",
    () -> CableBlockItem.create(TransferType.QUANTA, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_ITEM_PIPE = ITEMS.register("quantum_item_pipe",
        () -> CableBlockItem.create(TransferType.ITEM, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_LIQUID_PIPE = ITEMS.register("quantum_liquid_pipe",
        () -> CableBlockItem.create(TransferType.FLUID, new Item.Properties()));
    public static final DeferredItem<Item> QUANTUM_PRESSURIZED_PIPE = ITEMS.register("quantum_pressurized_pipe",
        () -> CableBlockItem.create(TransferType.GAS, new Item.Properties()));
}