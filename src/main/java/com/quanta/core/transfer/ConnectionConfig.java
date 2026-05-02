package com.quanta.core.transfer;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.nbt.CompoundTag;

public class ConnectionConfig {

    public enum Mode {
        DISABLED,
        INSERT,
        EXTRACT;

        private static final Mode[] VALUES = values();

        public Mode next() { return VALUES[(ordinal() + 1) % VALUES.length]; }
        public boolean isInsert() { return this == INSERT; }
        public boolean isExtract() { return this == EXTRACT; }
        public boolean isEnabled() { return this != DISABLED; }
    }

    private Mode mode = Mode.INSERT;
    private Mode previousMode = Mode.INSERT;
    private Priority priority = Priority.NORMAL;
    private final IntSet filterTypes = new IntOpenHashSet();
    private int transferLimit = 64;
    private byte flags = 0;

    private static final byte FLAG_WHITELIST = 0b00000001;

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) {
        if (this.mode != Mode.DISABLED && mode == Mode.DISABLED) {
            this.previousMode = this.mode;
        }
        this.mode = mode;
    }
    public void cycleMode() { setMode(mode.next()); }
    public Mode getPreviousMode() { return previousMode; }

    public boolean isWhitelist() { return (flags & FLAG_WHITELIST) != 0; }
    public void setWhitelist(boolean whitelist) {
        if (whitelist) flags |= FLAG_WHITELIST;
        else flags &= ~FLAG_WHITELIST;
    }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void increasePriority() { priority = priority.next(); }
    public void decreasePriority() { priority = priority.previous(); }

    public void addFilterType(TransferType type) { filterTypes.add(type.ordinal()); }
    public void removeFilterType(TransferType type) { filterTypes.remove(type.ordinal()); }
    public void clearFilter() { filterTypes.clear(); }
    public boolean isFiltered(TransferType type) { return filterTypes.contains(type.ordinal()); }
    public boolean hasFilter() { return !filterTypes.isEmpty(); }

    public int getTransferLimit() { return transferLimit; }
    public void setTransferLimit(int limit) { this.transferLimit = Math.clamp(limit, 1, 1024); }

    public boolean canTransfer(TransferType type) {
        if (!mode.isEnabled()) return false;
        if (!hasFilter()) return true;
        return isWhitelist() == isFiltered(type);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putByte("m", (byte) mode.ordinal());
        tag.putByte("pm", (byte) previousMode.ordinal());
        tag.putByte("p", (byte) priority.ordinal());
        tag.putByte("f", flags);
        tag.putShort("l", (short) transferLimit);
        if (!filterTypes.isEmpty()) tag.putIntArray("ft", filterTypes.toIntArray());
        return tag;
    }

    public void load(CompoundTag tag) {
        mode = Mode.VALUES[tag.getByte("m")];
        previousMode = Mode.VALUES[tag.getByte("pm")];
        priority = Priority.values()[tag.getByte("p")];
        flags = tag.getByte("f");
        transferLimit = tag.getShort("l");
        filterTypes.clear();
        if (tag.contains("ft")) for (int id : tag.getIntArray("ft")) filterTypes.add(id);
    }

    public ConnectionConfig copy() {
        ConnectionConfig copy = new ConnectionConfig();
        copy.mode = this.mode;
        copy.previousMode = this.previousMode;
        copy.priority = this.priority;
        copy.flags = this.flags;
        copy.transferLimit = this.transferLimit;
        copy.filterTypes.addAll(this.filterTypes);
        return copy;
    }
}