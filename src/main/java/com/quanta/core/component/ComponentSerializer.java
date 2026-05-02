package com.quanta.core.component;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;

import java.util.Optional;

public class ComponentSerializer {

    public static <T> Optional<CompoundTag> encode(Codec<T> codec, T value) {
        return codec.encodeStart(NbtOps.INSTANCE, value)
            .result()
            .filter(tag -> tag instanceof CompoundTag)
            .map(tag -> (CompoundTag) tag);
    }

    public static <T> Optional<T> decode(Codec<T> codec, CompoundTag tag) {
        return codec.parse(NbtOps.INSTANCE, tag).result();
    }

    public static <T> Optional<T> decode(Codec<T> codec, Tag tag) {
        return codec.parse(NbtOps.INSTANCE, tag).result();
    }
}