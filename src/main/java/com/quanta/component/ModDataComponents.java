package com.quanta.component;

import com.mojang.serialization.Codec;
import com.quanta.Quanta;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Quanta.MOD_ID);
    
    // Component para armazenar dados do experimento
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> EXPERIMENT_DATA =
        DATA_COMPONENTS.registerComponentType("experiment_data",
            builder -> builder
                .persistent(CompoundTag.CODEC)
                .networkSynchronized(ByteBufCodecs.COMPOUND_TAG)
        );
    
    // Component para status do tester
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TESTER_CHARGES =
        DATA_COMPONENTS.registerComponentType("tester_charges",
            builder -> builder
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.VAR_INT)
        );
}