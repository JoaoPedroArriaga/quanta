package com.quanta.menu.base;

import com.quanta.blockentity.base.QuantaProcessingBE;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

/**
 * OPTIMIZED: Menu with pre-calculated slot indices as constants.
 */
public abstract class QuantaMenu extends AbstractContainerMenu {
    
    // ========== CONSTANT SLOT INDICES (precomputed for performance) ==========
    protected static final int INPUT_SLOT = 0;
    protected static final int OUTPUT_SLOT = 1;
    protected static final int PLAYER_INV_START = 2;
    protected static final int PLAYER_INV_END = 29;      // 2 + 27 slots
    protected static final int HOTBAR_START = 29;
    protected static final int HOTBAR_END = 38;          // 29 + 9 slots
    protected static final int TOTAL_SLOTS = 38;
    
    protected final QuantaProcessingBE blockEntity;
    protected final ContainerData data;
    
    public QuantaMenu(@Nullable MenuType<?> type, int id, Inventory inv, 
                      QuantaProcessingBE be, int inputX, int inputY, 
                      int outputX, int outputY) {
        this(type, id, inv, be, new SimpleContainerData(2), inputX, inputY, outputX, outputY);
    }
    
    public QuantaMenu(@Nullable MenuType<?> type, int id, Inventory inv, 
                      QuantaProcessingBE be, ContainerData data,
                      int inputX, int inputY, int outputX, int outputY) {
        super(type, id);
        this.blockEntity = be;
        this.data = data;
        
        // Machine slots
        this.addSlot(new SlotItemHandler(be.getInventory(), INPUT_SLOT, inputX, inputY));
        this.addSlot(new SlotItemHandler(be.getInventory(), OUTPUT_SLOT, outputX, outputY) {
            @Override
            public boolean mayPlace(ItemStack stack) { return false; }
        });
        
        // Player inventory (3 rows)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        
        // Hotbar (1 row)
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
        
        addDataSlots(data);
    }
    
    // ========== GETTERS ==========
    public int getProgress() { return data.get(0); }
    public int getMaxProgress() { return data.get(1); }
    public int getEnergyStored() { return blockEntity.getEnergyStoredQuanta(); }
    public int getEnergyCapacity() { return blockEntity.getEnergyCapacityQuanta(); }
    
    // ========== QUICK MOVE (optimized with constants) ==========
    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();
        
        if (slotIndex == INPUT_SLOT || slotIndex == OUTPUT_SLOT) {
            // Machine → Player inventory
            if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }
        } else if (isItemProcessable(stack)) {
            // Player inventory → Machine input
            if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (slotIndex >= PLAYER_INV_START && slotIndex < HOTBAR_START) {
            // Inventory → Hotbar
            if (!this.moveItemStackTo(stack, HOTBAR_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }
        } else if (slotIndex >= HOTBAR_START && slotIndex < HOTBAR_END) {
            // Hotbar → Inventory
            if (!this.moveItemStackTo(stack, PLAYER_INV_START, HOTBAR_START, false)) {
                return ItemStack.EMPTY;
            }
        }
        
        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        
        return copy;
    }
    
    // ========== ABSTRACT ==========
    protected abstract boolean isItemProcessable(ItemStack stack);
    
    // ========== VALIDATION ==========
    @Override
    public boolean stillValid(Player player) {
        return player.level().getBlockEntity(blockEntity.getBlockPos()) == blockEntity;
    }
}