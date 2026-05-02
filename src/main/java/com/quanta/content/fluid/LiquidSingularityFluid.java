package com.quanta.content.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public abstract class LiquidSingularityFluid extends BaseFlowingFluid {

    public LiquidSingularityFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends LiquidSingularityFluid {
        public Source(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_SINGULARITY, ModFluids.LIQUID_SINGULARITY_FLOWING).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return true; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return 8; }
    }

    public static class Flowing extends LiquidSingularityFluid {
        public Flowing(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.LIQUID_SINGULARITY_FLOWING, ModFluids.LIQUID_SINGULARITY).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return false; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return s.getValue(LEVEL); }
    }
}