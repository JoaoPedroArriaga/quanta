package com.quanta.core.transfer;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum TransferType implements StringRepresentable {
    QUANTA("quanta", 10000),
    ITEM("item", 0),
    FLUID("fluid", 0),
    GAS("gas", 0);

    private final String name;
    public final int defaultTransfer;
    public final int bitMask;

    private static final TransferType[] VALUES = values();

    TransferType(String name, int defaultTransfer) {
        this.name = name;
        this.defaultTransfer = defaultTransfer;
        this.bitMask = 1 << ordinal();
    }

    @Override
    @NotNull
    public String getSerializedName() { return name; }

    public static TransferType fromId(int id) {
        return id >= 0 && id < VALUES.length ? VALUES[id] : QUANTA;
    }

    public static int maskOf(TransferType... types) {
        int mask = 0;
        for (TransferType type : types) {
            mask |= type.bitMask;
        }
        return mask;
    }
}