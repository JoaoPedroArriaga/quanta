package com.quanta.block.cable;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum CableType implements StringRepresentable {
    ENERGY(0, 0xFF9D4EDD, "energy", "quanta_cable", 10000, 1000, "quanta"),
    ITEM(1, 0xFF3B82F6, "item", "quantum_item_pipe", 0, 0, "items"),
    LIQUID(2, 0xFF10B981, "liquid", "quantum_liquid_pipe", 0, 0, "fluids"),
    GAS(3, 0xFFF59E0B, "gas", "quantum_pressurized_pipe", 0, 0, "gases");
    
    public final int id;
    public final int color;
    public final String name;
    public final String textureName;
    public final int maxTransfer;
    public final int maxReceive;
    public final String transferType;
    public final String translationKey;
    private final int bitMask;
    
    private static final CableType[] BY_ID = new CableType[4];
    
    static {
        for (CableType type : values()) {
            BY_ID[type.id] = type;
        }
    }
    
    CableType(int id, int color, String name, String textureName, int maxTransfer, int maxReceive, String transferType) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.textureName = textureName;
        this.maxTransfer = maxTransfer;
        this.maxReceive = maxReceive;
        this.transferType = transferType;
        this.translationKey = "item.quanta." + textureName;
        this.bitMask = 1 << id;
    }
    
    public int getBitMask() { return bitMask; }
    
    public static CableType fromId(int id) {
        return id >= 0 && id < BY_ID.length ? BY_ID[id] : ENERGY;
    }
    
    public static CableType fromName(String name) {
        for (CableType type : values()) {
            if (type.name.equals(name)) return type;
        }
        return ENERGY;
    }
    
    @Override
    @NotNull
    public String getSerializedName() {
        return name;
    }
}