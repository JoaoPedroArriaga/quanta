package com.quanta.pipe.filter;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import java.util.HashSet;
import java.util.Set;

public class FilterConfig {
    private boolean whitelist = true;
    private boolean matchNbt = true;
    private boolean matchMod = false;
    private Set<Item> allowedItems = new HashSet<>();
    private Set<Fluid> allowedFluids = new HashSet<>();
    private Set<String> allowedMods = new HashSet<>();
    
    public boolean isWhitelist() { return whitelist; }
    public void setWhitelist(boolean whitelist) { this.whitelist = whitelist; }
    
    public boolean canTransferItem(ItemStack stack) {
        if (allowedItems.isEmpty() && allowedMods.isEmpty()) return true;
        
        boolean contains = allowedItems.contains(stack.getItem());
        
        if (!contains && matchMod) {
            String modId = stack.getItem().toString().split(":")[0];
            contains = allowedMods.contains(modId);
        }
        
        return whitelist ? contains : !contains;
    }
    
    public boolean canTransferFluid(FluidStack stack) {
        if (allowedFluids.isEmpty()) return true;
        boolean contains = allowedFluids.contains(stack.getFluid());
        return whitelist ? contains : !contains;
    }
    
    public void addItem(Item item) { allowedItems.add(item); }
    public void removeItem(Item item) { allowedItems.remove(item); }
    public void clearItems() { allowedItems.clear(); }
    
    public void addFluid(Fluid fluid) { allowedFluids.add(fluid); }
    public void removeFluid(Fluid fluid) { allowedFluids.remove(fluid); }
    public void clearFluids() { allowedFluids.clear(); }
    
    public void addMod(String modId) { allowedMods.add(modId); }
    public void removeMod(String modId) { allowedMods.remove(modId); }
    
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("whitelist", whitelist);
        tag.putBoolean("matchNbt", matchNbt);
        tag.putBoolean("matchMod", matchMod);
        
        int[] itemIds = allowedItems.stream().mapToInt(i -> Item.getId(i)).toArray();
        tag.putIntArray("items", itemIds);
        
        String[] modsArray = allowedMods.toArray(new String[0]);
        tag.putString("mods", String.join(",", modsArray));
        
        return tag;
    }
    
    public void fromNBT(CompoundTag tag) {
        this.whitelist = tag.getBoolean("whitelist");
        this.matchNbt = tag.getBoolean("matchNbt");
        this.matchMod = tag.getBoolean("matchMod");
        
        int[] itemIds = tag.getIntArray("items");
        for (int id : itemIds) {
            allowedItems.add(Item.byId(id));
        }
        
        String modsStr = tag.getString("mods");
        if (!modsStr.isEmpty()) {
            for (String modId : modsStr.split(",")) {
                allowedMods.add(modId);
            }
        }
    }
}