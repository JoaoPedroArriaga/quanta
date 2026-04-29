package com.quanta.pipe;

import com.quanta.block.cable.CableType;
import com.quanta.pipe.filter.FilterConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import java.util.*;

public class QuantumPipeLogic {
    
    private final Map<Direction, FilterConfig> inputFilters = new HashMap<>();
    private final Map<Direction, FilterConfig> outputFilters = new HashMap<>();
    private int priority = 0;
    private boolean roundRobin = false;
    private int lastExtractedSide = 0;
    
    public void setFilter(Direction side, FilterConfig filter, boolean isInput) {
        if (isInput) {
            inputFilters.put(side, filter);
        } else {
            outputFilters.put(side, filter);
        }
    }
    
    public FilterConfig getFilter(Direction side, boolean isInput) {
        return isInput ? inputFilters.get(side) : outputFilters.get(side);
    }
    
    public void tickExtract(Level level, BlockPos pos, Set<BlockPos> networkPositions, CableType type) {
        if (type != CableType.ITEM) return;
        
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            if (!networkPositions.contains(neighborPos)) {
                extractFromInventory(level, pos, neighborPos, dir);
            }
        }
    }
    
    private void extractFromInventory(Level level, BlockPos pipePos, BlockPos inventoryPos, Direction direction) {
        BlockEntity be = level.getBlockEntity(inventoryPos);
        if (be == null) return;
        
        IItemHandler handler = level.getCapability(Capabilities.ItemHandler.BLOCK, inventoryPos, direction.getOpposite());
        if (handler == null) return;
        
        FilterConfig filter = inputFilters.get(direction);
        
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.extractItem(slot, 64, true);
            if (stack.isEmpty()) continue;
            
            if (filter != null && !filter.canTransferItem(stack)) continue;
            
            if (tryInsertIntoNetwork(level, pipePos, stack)) {
                handler.extractItem(slot, stack.getCount(), false);
                break;
            }
        }
    }
    
    private boolean tryInsertIntoNetwork(Level level, BlockPos sourcePos, ItemStack stack) {
        // TODO: Implementar roteamento de itens pela rede
        return false;
    }
    
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public boolean isRoundRobin() { return roundRobin; }
    public void setRoundRobin(boolean roundRobin) { this.roundRobin = roundRobin; }
}