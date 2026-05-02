package com.quanta.content.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public abstract class ChaosGasFluid extends BaseFlowingFluid {

    public ChaosGasFluid(Properties properties) { super(properties); }

    public static class Source extends ChaosGasFluid {
        public Source(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.CHAOS_GAS, ModFluids.CHAOS_GAS_FLOWING).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return true; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return 8; }
    }

    public static class Flowing extends ChaosGasFluid {
        public Flowing(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.CHAOS_GAS_FLOWING, ModFluids.CHAOS_GAS).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return false; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return s.getValue(LEVEL); }
    }
}