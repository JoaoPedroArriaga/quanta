package com.quanta.core.component;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ComponentManager {

    private static final Map<Class<?>, DataComponentType<?>> COMPONENT_MAP = new HashMap<>();

    public static <T> void register(Class<T> clazz, DataComponentType<T> componentType) {
        COMPONENT_MAP.put(clazz, componentType);
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<DataComponentType<T>> getComponentType(Class<T> clazz) {
        return Optional.ofNullable((DataComponentType<T>) COMPONENT_MAP.get(clazz));
    }

    public static <T> T get(ItemStack stack, DataComponentType<T> type) {
        return stack.get(type);
    }

    public static <T> void set(ItemStack stack, DataComponentType<T> type, T value) {
        stack.set(type, value);
    }

    public static <T> void update(ItemStack stack, DataComponentType<T> type, T defaultValue,
                                   java.util.function.UnaryOperator<T> updater) {
        stack.update(type, defaultValue, updater);
    }
}