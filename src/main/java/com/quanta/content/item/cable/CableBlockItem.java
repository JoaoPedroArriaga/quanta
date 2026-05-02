package com.quanta.content.item.cable;

import com.quanta.content.block.ModBlocks;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.function.Consumer;

public class CableBlockItem extends BlockItem {

    private final TransferType type;

    public CableBlockItem(TransferType type, Block block, Properties properties) {
        super(block, properties);
        this.type = type;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {}

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("item.quanta.cable." + type.getSerializedName());
    }

    public TransferType getType() { return type; }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Direction face = context.getClickedFace();

        if (level.getBlockEntity(clickedPos) instanceof CableBlockEntity cable) {
            if (!cable.hasType(type)) {
                if (!level.isClientSide) {
                    cable.addType(type);
                    cable.scheduleConnectionUpdate();
                    stack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
            BlockPos placePos = clickedPos.relative(face);
            if (level.isEmptyBlock(placePos) && !level.isClientSide) {
                level.setBlock(placePos, getBlock().defaultBlockState(), 3);
                if (level.getBlockEntity(placePos) instanceof CableBlockEntity newCable) {
                    newCable.addType(type);
                    newCable.scheduleConnectionUpdate();
                    stack.shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        }

        BlockPos placePos = clickedPos.relative(face);
        if (level.isEmptyBlock(placePos) && !level.isClientSide) {
            level.setBlock(placePos, getBlock().defaultBlockState(), 3);
            if (level.getBlockEntity(placePos) instanceof CableBlockEntity newCable) {
                newCable.addType(type);
                newCable.scheduleConnectionUpdate();
                stack.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.quanta.cable." + type.getSerializedName()));
        tooltip.add(Component.translatable("tooltip.quanta.bundle.click_to_add"));
        tooltip.add(Component.translatable("tooltip.quanta.bundle.click_to_place"));
    }

    public static CableBlockItem create(TransferType type, Properties properties) {
        return new CableBlockItem(type, ModBlocks.CABLE.get(), properties);
    }
}