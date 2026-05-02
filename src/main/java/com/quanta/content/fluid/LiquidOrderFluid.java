package com.quanta.content.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public abstract class LiquidOrderFluid extends BaseFlowingFluid {

    public LiquidOrderFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends LiquidOrderFluid {
        public Source(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_ORDER, ModFluids.LIQUID_ORDER_FLOWING).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return true; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return 8; }
    }

    public static class Flowing extends LiquidOrderFluid {
        public Flowing(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_ORDER_FLOWING, ModFluids.LIQUID_ORDER).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return false; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return s.getValue(LEVEL); }
    }
}