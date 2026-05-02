package com.quanta.content.fluid;

import com.quanta.Quanta;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
        DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Quanta.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
        DeferredRegister.create(net.minecraft.core.registries.Registries.FLUID, Quanta.MOD_ID);

    // === LIQUIDS ===

    // Liquid Quanta
    public static final DeferredHolder<FluidType, FluidType> LIQUID_QUANTA_TYPE =
        FLUID_TYPES.register("liquid_quanta", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_QUANTA =
        FLUIDS.register("liquid_quanta", () -> new LiquidQuantaFluid.Source(LIQUID_QUANTA_TYPE));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_QUANTA_FLOWING =
        FLUIDS.register("liquid_quanta_flowing", () -> new LiquidQuantaFluid.Flowing(LIQUID_QUANTA_TYPE));

    // Liquid Chaos
    public static final DeferredHolder<FluidType, FluidType> LIQUID_CHAOS_TYPE =
        FLUID_TYPES.register("liquid_chaos", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_CHAOS =
        FLUIDS.register("liquid_chaos", () -> new LiquidChaosFluid.Source(LIQUID_CHAOS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_CHAOS_FLOWING =
        FLUIDS.register("liquid_chaos_flowing", () -> new LiquidChaosFluid.Flowing(LIQUID_CHAOS_TYPE));

    // Liquid Order
    public static final DeferredHolder<FluidType, FluidType> LIQUID_ORDER_TYPE =
        FLUID_TYPES.register("liquid_order", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_ORDER =
        FLUIDS.register("liquid_order", () -> new LiquidOrderFluid.Source(LIQUID_ORDER_TYPE));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_ORDER_FLOWING =
        FLUIDS.register("liquid_order_flowing", () -> new LiquidOrderFluid.Flowing(LIQUID_ORDER_TYPE));

    // Stabilized Liquid
    public static final DeferredHolder<FluidType, FluidType> STABILIZED_LIQUID_TYPE =
        FLUID_TYPES.register("stabilized_liquid", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> STABILIZED_LIQUID =
        FLUIDS.register("stabilized_liquid", () -> new StabilizedLiquidFluid.Source(STABILIZED_LIQUID_TYPE));
    public static final DeferredHolder<Fluid, Fluid> STABILIZED_LIQUID_FLOWING =
        FLUIDS.register("stabilized_liquid_flowing", () -> new StabilizedLiquidFluid.Flowing(STABILIZED_LIQUID_TYPE));

    // Entangled Liquid
    public static final DeferredHolder<FluidType, FluidType> ENTANGLED_LIQUID_TYPE =
        FLUID_TYPES.register("entangled_liquid", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> ENTANGLED_LIQUID =
        FLUIDS.register("entangled_liquid", () -> new EntangledLiquidFluid.Source(ENTANGLED_LIQUID_TYPE));
    public static final DeferredHolder<Fluid, Fluid> ENTANGLED_LIQUID_FLOWING =
        FLUIDS.register("entangled_liquid_flowing", () -> new EntangledLiquidFluid.Flowing(ENTANGLED_LIQUID_TYPE));

    // Liquid Singularity
    public static final DeferredHolder<FluidType, FluidType> LIQUID_SINGULARITY_TYPE =
        FLUID_TYPES.register("liquid_singularity", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_SINGULARITY =
        FLUIDS.register("liquid_singularity", () -> new LiquidSingularityFluid.Source(LIQUID_SINGULARITY_TYPE));
    public static final DeferredHolder<Fluid, Fluid> LIQUID_SINGULARITY_FLOWING =
        FLUIDS.register("liquid_singularity_flowing", () -> new LiquidSingularityFluid.Flowing(LIQUID_SINGULARITY_TYPE));

    // === GASES ===

    // Quanta Gas
    public static final DeferredHolder<FluidType, FluidType> QUANTA_GAS_TYPE =
        FLUID_TYPES.register("quanta_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> QUANTA_GAS =
        FLUIDS.register("quanta_gas", () -> new QuantaGasFluid.Source(QUANTA_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> QUANTA_GAS_FLOWING =
        FLUIDS.register("quanta_gas_flowing", () -> new QuantaGasFluid.Flowing(QUANTA_GAS_TYPE));

    // Chaos Gas
    public static final DeferredHolder<FluidType, FluidType> CHAOS_GAS_TYPE =
        FLUID_TYPES.register("chaos_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> CHAOS_GAS =
        FLUIDS.register("chaos_gas", () -> new ChaosGasFluid.Source(CHAOS_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> CHAOS_GAS_FLOWING =
        FLUIDS.register("chaos_gas_flowing", () -> new ChaosGasFluid.Flowing(CHAOS_GAS_TYPE));

    // Order Gas
    public static final DeferredHolder<FluidType, FluidType> ORDER_GAS_TYPE =
        FLUID_TYPES.register("order_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> ORDER_GAS =
        FLUIDS.register("order_gas", () -> new OrderGasFluid.Source(ORDER_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> ORDER_GAS_FLOWING =
        FLUIDS.register("order_gas_flowing", () -> new OrderGasFluid.Flowing(ORDER_GAS_TYPE));

    // Entangled Gas
    public static final DeferredHolder<FluidType, FluidType> ENTANGLED_GAS_TYPE =
        FLUID_TYPES.register("entangled_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> ENTANGLED_GAS =
        FLUIDS.register("entangled_gas", () -> new EntangledGasFluid.Source(ENTANGLED_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> ENTANGLED_GAS_FLOWING =
        FLUIDS.register("entangled_gas_flowing", () -> new EntangledGasFluid.Flowing(ENTANGLED_GAS_TYPE));

    // Stabilized Gas
    public static final DeferredHolder<FluidType, FluidType> STABILIZED_GAS_TYPE =
        FLUID_TYPES.register("stabilized_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> STABILIZED_GAS =
        FLUIDS.register("stabilized_gas", () -> new StabilizedGasFluid.Source(STABILIZED_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> STABILIZED_GAS_FLOWING =
        FLUIDS.register("stabilized_gas_flowing", () -> new StabilizedGasFluid.Flowing(STABILIZED_GAS_TYPE));

    // Singularity Gas
    public static final DeferredHolder<FluidType, FluidType> SINGULARITY_GAS_TYPE =
        FLUID_TYPES.register("singularity_gas", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, Fluid> SINGULARITY_GAS =
        FLUIDS.register("singularity_gas", () -> new SingularityGasFluid.Source(SINGULARITY_GAS_TYPE));
    public static final DeferredHolder<Fluid, Fluid> SINGULARITY_GAS_FLOWING =
        FLUIDS.register("singularity_gas_flowing", () -> new SingularityGasFluid.Flowing(SINGULARITY_GAS_TYPE));
}