package com.quanta.experiment;

import net.minecraft.world.item.ItemStack;

public class ItemPresentCondition implements Condition {
    private final ItemStack item;
    
    public ItemPresentCondition(ItemStack item) {
        this.item = item;
    }
    
    @Override
    public boolean check(ExperimentContext context) {
        return context.hasItem(item);
    }
}