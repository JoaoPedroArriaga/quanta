package com.quanta.content.item.upgrade;

import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class QuantumUpgradeKit extends Item {

    private final QuantumTier targetTier;

    public QuantumUpgradeKit(Properties properties, QuantumTier targetTier) {
        super(properties);
        this.targetTier = targetTier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockEntity be = level.getBlockEntity(pos);

        if (!(be instanceof CableBlockEntity cable)) return InteractionResult.PASS;
        if (player == null) return InteractionResult.PASS;

        QuantumTier current = cable.getTier();
        if (targetTier.ordinal() <= current.ordinal()) {
            if (!level.isClientSide)
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide) {
            cable.upgradeTier(targetTier);
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Upgrades cable to " + targetTier.name));
    }
}