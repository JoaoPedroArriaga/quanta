package com.quanta.core.component;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;

public class DataComponentHelper {

    public static <T> T getOrDefault(ItemStack stack, DataComponentType<T> type, T defaultValue) {
        T value = stack.get(type);
        return value != null ? value : defaultValue;
    }

    public static boolean hasComponent(ItemStack stack, DataComponentType<?> type) {
        return stack.has(type);
    }

    public static <T> ItemStack copyWithComponent(ItemStack stack, DataComponentType<T> type, T value) {
        ItemStack copy = stack.copy();
        copy.set(type, value);
        return copy;
    }

    public static DataComponentMap.Builder builder() {
        return DataComponentMap.builder();
    }
}