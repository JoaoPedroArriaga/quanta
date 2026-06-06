package com.quanta.core.tier;

import com.quanta.core.transfer.TransferType;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum QuantumTier implements StringRepresentable {
    DECOHERENT(0, "decoherent", 100, 1000, 64, 1000, 0x9D4EDD, 1.0f, 1, 2),
    ENTANGLED(1, "entangled", 1000, 8000, 128, 8000, 0x00FFFF, 0.8f, 3, 6),
    SUPERPOSED(2, "superposed", 10000, 64000, 256, 64000, 0xFFD700, 0.6f, 4, 8),
    SINGULAR(3, "singular", 100000, 512000, 512, 512000, 0xFFFFFF, 0.4f, 6, 12);

    public final int id;
    public final String name;
    public final int energyTransfer;
    public final int liquidTransfer;
    public final int itemTransfer;
    public final int gasTransfer;
    public final int color;
    public final float energyEfficiency;
    public final int addonSlots;
    public final int totalSlots;

    private static final QuantumTier[] BY_ID = new QuantumTier[4];

    static { for (QuantumTier tier : values()) BY_ID[tier.id] = tier; }

    QuantumTier(int id, String name, int energyTransfer, int liquidTransfer,
                int itemTransfer, int gasTransfer, int color, float energyEfficiency,
                int addonSlots, int totalSlots) {
        this.id = id;
        this.name = name;
        this.energyTransfer = energyTransfer;
        this.liquidTransfer = liquidTransfer;
        this.itemTransfer = itemTransfer;
        this.gasTransfer = gasTransfer;
        this.color = color;
        this.energyEfficiency = energyEfficiency;
        this.addonSlots = addonSlots;
        this.totalSlots = totalSlots;
    }

    public int getSlotPairs() { return totalSlots / 2; }
    public int getMaxTransfer(TransferType type) {
        return switch (type) {
            case QUANTA -> energyTransfer;
            case ITEM -> itemTransfer;
            case FLUID -> liquidTransfer;
            case GAS -> gasTransfer;
        };
    }
    public int getAddonSlots() { return addonSlots; }
    public static QuantumTier fromId(int id) { return id >= 0 && id < BY_ID.length ? BY_ID[id] : DECOHERENT; }
    public QuantumTier next() { int nextId = id + 1; return nextId < BY_ID.length ? BY_ID[nextId] : null; }

    @Override @NotNull
    public String getSerializedName() { return name; }
}