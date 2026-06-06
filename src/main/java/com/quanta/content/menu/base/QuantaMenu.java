package com.quanta.content.menu.base;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public abstract class QuantaMenu extends AbstractContainerMenu {

    protected final ContainerData data;

    protected QuantaMenu(MenuType<?> type, int containerId, ContainerData data) {
        super(type, containerId);
        this.data = data;
        addDataSlots(data);
    }

    protected final void addPlayerSlots(Inventory inv) {
        int extraHeight = getSlotPairs() > 1 ? 20 : 0;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + extraHeight + row * 18));
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 142 + extraHeight));
    }

    public int getEnergyStored() { return data.get(0); }
    public int getEnergyCapacity() { return data.get(1); }
    public int getProgress() { return data.get(2); }
    public int getMaxProgress() { return data.get(3); }
    public int getAddonSlots() { return 0; }
    public int getSlotPairs() { return 1; }
    public boolean isSuperposed() { return false; }
    public void toggleSuperposition() {}

    protected abstract boolean isItemProcessable(ItemStack stack);

    @Override
    public ItemStack quickMoveStack(net.minecraft.world.entity.player.Player player, int index) {
        if (index < 0 || index >= this.slots.size()) return ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();
        int machineSlots = this.slots.size() - 36;
        if (index < machineSlots) {
            if (!moveItemStackTo(stack, machineSlots, this.slots.size(), true)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, 0, getSlotPairs(), false)) return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        return copy;
    }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return true;
    }
}