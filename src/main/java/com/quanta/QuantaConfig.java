package com.quanta;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class QuantaConfig {
    
    private static volatile int CACHED_FE_PER_QUANTA = 100;
    private static volatile int CACHED_PARTICLE_COST = 10;
    private static volatile int CACHED_PARTICLE_OUTPUT_MIN = 2;
    private static volatile int CACHED_PARTICLE_OUTPUT_MAX = 8;
    
    public static class Common {
        public final ModConfigSpec.IntValue fePerQuanta;
        public final ModConfigSpec.IntValue particleReconstructorCost;
        public final ModConfigSpec.IntValue particleReconstructorOutputMin;
        public final ModConfigSpec.IntValue particleReconstructorOutputMax;
        
        Common(ModConfigSpec.Builder builder) {
            builder.comment("Quanta Energy").push("energy");
            fePerQuanta = builder
                .comment("FE per Quanta (1 Quanta = X FE)")
                .defineInRange("fePerQuanta", 100, 1, 10000);
            builder.pop();
            
            builder.comment("Particle Reconstructor").push("particle_reconstructor");
            particleReconstructorCost = builder
                .comment("Quanta cost per operation tick")
                .defineInRange("cost", 10, 1, 1000);
            particleReconstructorOutputMin = builder
                .comment("Minimum output multiplier")
                .defineInRange("output_min", 2, 1, 16);
            particleReconstructorOutputMax = builder
                .comment("Maximum output multiplier")
                .defineInRange("output_max", 8, 1, 16);
            builder.pop();
        }
    }
    
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;
    
    static {
        Pair<Common, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON = pair.getLeft();
        COMMON_SPEC = pair.getRight();
    }
    
    public static void onConfigReload(ModConfigEvent event) {
        if (event instanceof ModConfigEvent.Reloading) {
            CACHED_FE_PER_QUANTA = COMMON.fePerQuanta.get();
            CACHED_PARTICLE_COST = COMMON.particleReconstructorCost.get();
            CACHED_PARTICLE_OUTPUT_MIN = COMMON.particleReconstructorOutputMin.get();
            CACHED_PARTICLE_OUTPUT_MAX = COMMON.particleReconstructorOutputMax.get();
        }
    }
    
    public static int fePerQuanta() { return CACHED_FE_PER_QUANTA; }
    public static int particleCost() { return CACHED_PARTICLE_COST; }
    public static int particleOutputMin() { return CACHED_PARTICLE_OUTPUT_MIN; }
    public static int particleOutputMax() { return CACHED_PARTICLE_OUTPUT_MAX; }
}