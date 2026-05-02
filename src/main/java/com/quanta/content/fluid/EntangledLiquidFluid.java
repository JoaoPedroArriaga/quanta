package com.quanta.content.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public abstract class EntangledLiquidFluid extends BaseFlowingFluid {

    public EntangledLiquidFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends EntangledLiquidFluid {
        public Source(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.ENTANGLED_LIQUID, ModFluids.ENTANGLED_LIQUID_FLOWING).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return true; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return 8; }
    }

    public static class Flowing extends EntangledLiquidFluid {
        public Flowing(Supplier<FluidType> fluidType) {
            super(new Properties(fluidType, ModFluids.ENTANGLED_LIQUID_FLOWING, ModFluids.ENTANGLED_LIQUID).block(null).bucket(null));
        }
        @Override public boolean isSource(net.minecraft.world.level.material.FluidState s) { return false; }
        @Override public int getAmount(net.minecraft.world.level.material.FluidState s) { return s.getValue(LEVEL); }
    }
}