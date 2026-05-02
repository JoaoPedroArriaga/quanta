package com.quanta.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class NbtHelper {

    public static CompoundTag writeBlockPos(BlockPos pos) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        return tag;
    }

    public static BlockPos readBlockPos(CompoundTag tag) {
        return new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    }

    public static void putIntArray(CompoundTag tag, String key, int... values) {
        tag.putIntArray(key, values);
    }

    public static int[] getIntArray(CompoundTag tag, String key, int defaultValue) {
        return tag.contains(key) ? tag.getIntArray(key) : new int[0];
    }

    public static ListTag createListTag(Tag... tags) {
        ListTag list = new ListTag();
        for (Tag t : tags) list.add(t);
        return list;
    }

    public static CompoundTag getOrCreateCompound(CompoundTag parent, String key) {
        if (!parent.contains(key)) parent.put(key, new CompoundTag());
        return parent.getCompound(key);
    }
}