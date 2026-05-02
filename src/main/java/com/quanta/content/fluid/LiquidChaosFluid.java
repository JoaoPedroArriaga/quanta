package com.quanta.content.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public abstract class LiquidChaosFluid extends BaseFlowingFluid {

    public LiquidChaosFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends LiquidChaosFluid {
        public Source(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_CHAOS, ModFluids.LIQUID_CHAOS_FLOWING).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return true; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return 8; }
    }

    public static class Flowing extends LiquidChaosFluid {
        public Flowing(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_CHAOS_FLOWING, ModFluids.LIQUID_CHAOS).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return false; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return s.getValue(LEVEL); }
    }
}