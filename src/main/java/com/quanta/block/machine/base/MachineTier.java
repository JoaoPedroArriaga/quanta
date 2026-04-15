package com.quanta.block.machine.base;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum MachineTier implements StringRepresentable {
    BASIC(0, 1.0f, 1.0f, 0.05f),
    ADVANCED(1, 2.0f, 1.5f, 0.10f),
    ELITE(2, 4.0f, 2.0f, 0.15f),
    ULTIMATE(3, 8.0f, 3.0f, 0.20f);
    
    public final int id;
    public final float speedMultiplier;
    public final float energyEfficiency;
    public final float quantumBonus;
    
    private static final MachineTier[] BY_ID = new MachineTier[4];
    static {
        for (MachineTier tier : values()) {
            BY_ID[tier.id] = tier;
        }
    }
    
    MachineTier(int id, float speedMultiplier, float energyEfficiency, float quantumBonus) {
        this.id = id;
        this.speedMultiplier = speedMultiplier;
        this.energyEfficiency = energyEfficiency;
        this.quantumBonus = quantumBonus;
    }
    
    public static MachineTier fromId(int id) {
        return id >= 0 && id < BY_ID.length ? BY_ID[id] : BASIC;
    }
    
    @Override
    @NotNull
    public String getSerializedName() {
        return name().toLowerCase();
    }
}