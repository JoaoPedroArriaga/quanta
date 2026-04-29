package com.quanta.item.cable;

import com.quanta.block.ModBlocks;
import com.quanta.block.cable.CableType;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.client.renderer.CableItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.function.Consumer;

public class QuantumLiquidPipeItem extends BlockItem {
    
    private static final CableType TYPE = CableType.LIQUID;

    public QuantumLiquidPipeItem(Block block, Properties properties) {
        super(block, properties);
    }
    
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return CableItemRenderer.getInstance();
            }
        });
    }
    
    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(TYPE.translationKey);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedState = level.getBlockState(clickedPos);
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        Direction face = context.getClickedFace();
        
        if (clickedState.getBlock() == ModBlocks.QUANTUM_CABLE.get()) {
            if (!level.isClientSide && player != null) {
                level.getBlockEntity(clickedPos, ModBlockEntities.QUANTUM_CABLE.get()).ifPresent(be -> {
                    if (!be.hasCable(TYPE)) {
                        be.addCable(TYPE);
                        be.refreshConnections();
                        stack.shrink(1);
                    } else {
                        BlockPos placePos = clickedPos.relative(face);
                        if (level.isEmptyBlock(placePos)) {
                            placeNewCable(level, placePos, player, stack);
                        }
                    }
                });
            }
            return InteractionResult.SUCCESS;
        }
        
        BlockPos placePos = clickedPos.relative(face);
        
        if (!level.isEmptyBlock(placePos)) {
            return InteractionResult.FAIL;
        }
        
        BlockState neighborState = level.getBlockState(placePos);
        if (neighborState.getBlock() == ModBlocks.QUANTUM_CABLE.get()) {
            if (!level.isClientSide && player != null) {
                level.getBlockEntity(placePos, ModBlockEntities.QUANTUM_CABLE.get()).ifPresent(be -> {
                    if (!be.hasCable(TYPE)) {
                        be.addCable(TYPE);
                        be.refreshConnections();
                        stack.shrink(1);
                    }
                });
            }
            return InteractionResult.SUCCESS;
        }
        
        if (!level.isClientSide) {
            placeNewCable(level, placePos, player, stack);
        }
        return InteractionResult.SUCCESS;
    }
    
    private void placeNewCable(Level level, BlockPos pos, Player player, ItemStack stack) {
        if (level.isEmptyBlock(pos)) {
            BlockState newState = getBlock().defaultBlockState();
            level.setBlock(pos, newState, 3);
            
            level.getBlockEntity(pos, ModBlockEntities.QUANTUM_CABLE.get()).ifPresent(be -> {
                be.addCable(TYPE);
                be.refreshConnections();
            });
            
            stack.shrink(1);
            level.sendBlockUpdated(pos, newState, newState, 3);
        }
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.quanta.pipe.liquid"));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.quanta.bundle.click_to_add"));
        tooltip.add(Component.translatable("tooltip.quanta.bundle.click_to_place"));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}