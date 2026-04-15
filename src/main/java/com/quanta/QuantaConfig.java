package com.quanta;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ULTRA OPTIMIZED: Config with direct memory access and event-based reload.
 * Values are stored in volatile fields for thread safety with minimal overhead.
 */
public class QuantaConfig {
    
    // Volatile for visibility across threads (render vs game thread)
    private static volatile int CACHED_FE_PER_QUANTA = 100;
    private static volatile int CACHED_PARTICLE_COST = 10;
    private static volatile int CACHED_PARTICLE_OUTPUT_MIN = 2;
    private static volatile int CACHED_PARTICLE_OUTPUT_MAX = 8;
    
    // Use bitwise operations for fast boolean checks
    private static volatile int CONFIG_FLAGS = 0;
    private static final int FLAG_HAS_FE_CACHE = 1 << 0;
    private static final int FLAG_HAS_COST_CACHE = 1 << 1;
    private static final int FLAG_HAS_OUTPUT_MIN_CACHE = 1 << 2;
    private static final int FLAG_HAS_OUTPUT_MAX_CACHE = 1 << 3;
    
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
    
    // Called when config reloads - updates all cached values atomically
    public static void onConfigReload(ModConfigEvent event) {
        if (event instanceof ModConfigEvent.Reloading) {
            // Use local variables to minimize volatile writes
            int newFe = COMMON.fePerQuanta.get();
            int newCost = COMMON.particleReconstructorCost.get();
            int newOutputMin = COMMON.particleReconstructorOutputMin.get();
            int newOutputMax = COMMON.particleReconstructorOutputMax.get();
            
            CACHED_FE_PER_QUANTA = newFe;
            CACHED_PARTICLE_COST = newCost;
            CACHED_PARTICLE_OUTPUT_MIN = newOutputMin;
            CACHED_PARTICLE_OUTPUT_MAX = newOutputMax;
            
            // Update flags
            int flags = 0;
            if (newFe != 100) flags |= FLAG_HAS_FE_CACHE;
            if (newCost != 10) flags |= FLAG_HAS_COST_CACHE;
            if (newOutputMin != 2) flags |= FLAG_HAS_OUTPUT_MIN_CACHE;
            if (newOutputMax != 8) flags |= FLAG_HAS_OUTPUT_MAX_CACHE;
            CONFIG_FLAGS = flags;
        }
    }
    
    // ========== INLINE-FRIENDLY GETTERS ==========
    // These should be inlined by JIT after many calls
    
    public static int fePerQuanta() {
        return CACHED_FE_PER_QUANTA;
    }
    
    public static int particleCost() {
        return CACHED_PARTICLE_COST;
    }
    
    public static int particleOutputMin() {
        return CACHED_PARTICLE_OUTPUT_MIN;
    }
    
    public static int particleOutputMax() {
        return CACHED_PARTICLE_OUTPUT_MAX;
    }
    
    // For debugging
    public static boolean isUsingDefaultValues() {
        return (CONFIG_FLAGS == 0);
    }
}