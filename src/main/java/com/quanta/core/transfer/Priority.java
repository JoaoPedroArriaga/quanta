package com.quanta.core.transfer;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Priority implements StringRepresentable {
    LOWEST("lowest", 0),
    LOW("low", 1),
    NORMAL("normal", 2),
    HIGH("high", 3),
    HIGHEST("highest", 4),
    CRITICAL("critical", 5);

    private final String name;
    public final int level;
    private static final Priority[] VALUES = values();

    Priority(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public Priority next() {
        int next = ordinal() + 1;
        return next < VALUES.length ? VALUES[next] : CRITICAL;
    }

    public Priority previous() {
        int prev = ordinal() - 1;
        return prev >= 0 ? VALUES[prev] : LOWEST;
    }

    @Override
    @NotNull
    public String getSerializedName() { return name; }
}