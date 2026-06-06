package com.quanta.core.transfer;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ConnectionConfig {

    public enum Mode {
        DISABLED, INSERT, EXTRACT;
        private static final Mode[] VALUES = values();
        public Mode next() { return VALUES[(ordinal() + 1) % VALUES.length]; }
        public boolean isInsert() { return this == INSERT; }
        public boolean isExtract() { return this == EXTRACT; }
        public boolean isEnabled() { return this != DISABLED; }
    }

    public enum FilterType {
        ITEM, FLUID, GAS, TAG, NBT, REGEX;
        private static final FilterType[] VALUES = values();
        public FilterType next() { return VALUES[(ordinal() + 1) % VALUES.length]; }
    }

    public static class ItemFilter {
        public FilterType type = FilterType.ITEM;
        public ItemStack stack = ItemStack.EMPTY;
        public String tagOrRegex = "";
        public boolean matchNbt = true;
        public boolean ignoreDamage = false;
        public boolean ignoreEnchants = false;
        public boolean ignoreCount = false;

        public CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.putByte("t", (byte) type.ordinal());
            if (!stack.isEmpty()) {
                ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, stack)
                    .result()
                    .ifPresent(item -> tag.put("item", item));
            }
            if (!tagOrRegex.isEmpty()) {
                tag.putString("tr", tagOrRegex);
            }
            tag.putBoolean("nbt", matchNbt);
            tag.putBoolean("idmg", ignoreDamage);
            tag.putBoolean("iench", ignoreEnchants);
            tag.putBoolean("icnt", ignoreCount);
            return tag;
        }

        public void load(CompoundTag tag) {
            type = FilterType.values()[tag.getByte("t")];
            if (tag.contains("item")) {
                stack = ItemStack.CODEC.parse(NbtOps.INSTANCE, tag.get("item"))
                    .result()
                    .orElse(ItemStack.EMPTY);
            }
            tagOrRegex = tag.getString("tr");
            matchNbt = tag.getBoolean("nbt");
            ignoreDamage = tag.getBoolean("idmg");
            ignoreEnchants = tag.getBoolean("iench");
            ignoreCount = tag.getBoolean("icnt");
        }

        public ItemFilter copy() {
            ItemFilter c = new ItemFilter();
            c.type = this.type;
            c.stack = this.stack.copy();
            c.tagOrRegex = this.tagOrRegex;
            c.matchNbt = this.matchNbt;
            c.ignoreDamage = this.ignoreDamage;
            c.ignoreEnchants = this.ignoreEnchants;
            c.ignoreCount = this.ignoreCount;
            return c;
        }
    }

    private Mode mode = Mode.INSERT;
    private Mode previousMode = Mode.INSERT;
    private Priority priority = Priority.NORMAL;
    private final IntSet filterTypes = new IntOpenHashSet();
    private int transferLimit = 64;
    private byte flags = 0;
    private boolean filterEnabled = false;
    private final List<ItemFilter> itemFilters = new ArrayList<>();
    private static final byte FLAG_WHITELIST = 0b00000001;

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) {
        if (this.mode != Mode.DISABLED && mode == Mode.DISABLED) this.previousMode = this.mode;
        this.mode = mode;
    }
    public void cycleMode() { setMode(mode.next()); }
    public Mode getPreviousMode() { return previousMode; }
    public boolean isWhitelist() { return (flags & FLAG_WHITELIST) != 0; }
    public void setWhitelist(boolean whitelist) {
        if (whitelist) flags |= FLAG_WHITELIST; else flags &= ~FLAG_WHITELIST;
    }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void cyclePriority() { priority = priority.next(); }
    public void addFilterType(TransferType type) { filterTypes.add(type.ordinal()); }
    public void removeFilterType(TransferType type) { filterTypes.remove(type.ordinal()); }
    public void clearFilter() { filterTypes.clear(); }
    public boolean isFiltered(TransferType type) { return filterTypes.contains(type.ordinal()); }
    public boolean hasFilter() { return !filterTypes.isEmpty(); }
    public int getTransferLimit() { return transferLimit; }
    public void setTransferLimit(int limit) { this.transferLimit = Math.clamp(limit, 1, 1024); }
    public boolean isFilterEnabled() { return filterEnabled; }
    public void setFilterEnabled(boolean filterEnabled) { this.filterEnabled = filterEnabled; }
    public List<ItemFilter> getItemFilters() { return itemFilters; }
    public void addItemFilter(ItemFilter filter) { itemFilters.add(filter); }
    public void removeItemFilter(int index) { if (index >= 0 && index < itemFilters.size()) itemFilters.remove(index); }
    public void clearItemFilters() { itemFilters.clear(); }

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
        tag.putBoolean("fe", filterEnabled);
        if (!filterTypes.isEmpty()) tag.putIntArray("ft", filterTypes.toIntArray());
        if (!itemFilters.isEmpty()) {
            CompoundTag filtersTag = new CompoundTag();
            for (int i = 0; i < itemFilters.size(); i++) filtersTag.put("f" + i, itemFilters.get(i).save());
            tag.put("if", filtersTag);
            tag.putInt("ifc", itemFilters.size());
        }
        return tag;
    }

    public void load(CompoundTag tag) {
        mode = Mode.VALUES[tag.getByte("m")];
        previousMode = Mode.VALUES[tag.getByte("pm")];
        priority = Priority.values()[tag.getByte("p")];
        flags = tag.getByte("f");
        transferLimit = tag.getShort("l");
        filterEnabled = tag.getBoolean("fe");
        filterTypes.clear();
        itemFilters.clear();
        if (tag.contains("ft")) for (int id : tag.getIntArray("ft")) filterTypes.add(id);
        if (tag.contains("if")) {
            CompoundTag filtersTag = tag.getCompound("if");
            int count = tag.getInt("ifc");
            for (int i = 0; i < count; i++) {
                if (filtersTag.contains("f" + i)) {
                    ItemFilter f = new ItemFilter();
                    f.load(filtersTag.getCompound("f" + i));
                    itemFilters.add(f);
                }
            }
        }
    }

    public ConnectionConfig copy() {
        ConnectionConfig c = new ConnectionConfig();
        c.mode = this.mode;
        c.previousMode = this.previousMode;
        c.priority = this.priority;
        c.flags = this.flags;
        c.transferLimit = this.transferLimit;
        c.filterEnabled = this.filterEnabled;
        c.filterTypes.addAll(this.filterTypes);
        for (ItemFilter f : this.itemFilters) c.itemFilters.add(f.copy());
        return c;
    }
}