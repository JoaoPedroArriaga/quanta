package com.quanta.content.item.tool;

import com.quanta.content.component.ModDataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class QuantumTesterItem extends Item {

    private static final int MAX_USES = 100;

    public QuantumTesterItem() {
        super(new Properties()
            .stacksTo(1)
            .durability(MAX_USES)
            .component(ModDataComponents.TESTER_CHARGES.get(), MAX_USES));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            CompoundTag data = stack.get(ModDataComponents.EXPERIMENT_DATA.get());
            if (data == null) data = new CompoundTag();
            data.putLong("last_use", level.getGameTime());
            stack.set(ModDataComponents.EXPERIMENT_DATA.get(), data);
            stack.update(ModDataComponents.TESTER_CHARGES.get(), MAX_USES, c -> Math.max(0, c - 1));
        }
        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}