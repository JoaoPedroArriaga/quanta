package com.quanta.block.cable;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

/**
 * ULTRA OPTIMIZED: Uses int IDs instead of enum for switch performance.
 * Precomputed arrays for O(1) lookups.
 */
public enum CableType implements StringRepresentable {
    ENERGY(0),
    FLUID(1),
    ITEM(2),
    GAS(3);
    
    public final int id;
    private static final CableType[] BY_ID = new CableType[4];
    private static final int[] COMPATIBILITY_MATRIX;
    
    static {
        for (CableType type : values()) {
            BY_ID[type.id] = type;
        }
        
        // Precompute compatibility matrix (bitmask)
        // Each type can share space with different types
        COMPATIBILITY_MATRIX = new int[4];
        COMPATIBILITY_MATRIX[ENERGY.id] = (1 << FLUID.id) | (1 << ITEM.id); // Energy shares with Fluid and Item
        COMPATIBILITY_MATRIX[FLUID.id] = (1 << ENERGY.id);                  // Fluid shares with Energy
        COMPATIBILITY_MATRIX[ITEM.id] = (1 << ENERGY.id);                   // Item shares with Energy
        COMPATIBILITY_MATRIX[GAS.id] = 0;                                   // Gas shares with nothing
    }
    
    CableType(int id) {
        this.id = id;
    }
    
    public static CableType fromId(int id) {
        return id >= 0 && id < BY_ID.length ? BY_ID[id] : ENERGY;
    }
    
    /**
     * Ultra-fast compatibility check using bitmask.
     * Single bitwise operation instead of enum comparison.
     */
    public boolean canShareSpaceWith(CableType other) {
        return (COMPATIBILITY_MATRIX[this.id] & (1 << other.id)) != 0;
    }
    
    @Override
    @NotNull
    public String getSerializedName() {
        return name().toLowerCase();
    }
}