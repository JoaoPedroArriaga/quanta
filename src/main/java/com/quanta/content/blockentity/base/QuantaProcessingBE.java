package com.quanta.content.blockentity.base;

import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class QuantaProcessingBE extends BlockEntity implements MenuProvider {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_OUTPUT = 1;

    protected SimpleContainer inventory;
    protected final ContainerData containerData;
    protected QuantumTier tier;
    protected int energyStored;
    protected int energyCapacity;
    protected int progress;
    protected int maxProgress;
    protected boolean superposed;
    protected int cachedEnergyCost;

    public QuantaProcessingBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                               int inventorySize, QuantumTier tier, int maxProgress) {
        super(type, pos, state);
        this.inventory = new SimpleContainer(inventorySize);
        this.containerData = new SimpleContainerData(4);
        this.tier = tier;
        this.energyCapacity = tier.energyTransfer * 20;
        this.maxProgress = maxProgress;
        this.cachedEnergyCost = (int) (getBaseEnergyCost() * tier.energyEfficiency);
    }

    public SimpleContainer getInventory() { return inventory; }
    public ContainerData getContainerData() { return containerData; }
    public QuantumTier getTier() { return tier; }
    public int getEnergyStored() { return energyStored; }
    public int getEnergyCapacity() { return energyCapacity; }
    public int getProgress() { return progress; }
    public int getMaxProgress() { return maxProgress; }
    public int getAddonSlots() { return tier.getAddonSlots(); }
    public boolean isSuperposed() { return superposed; }

    public void toggleSuperposition() {
        this.superposed = !this.superposed;
        setChanged();
    }

    public void upgradeTier(QuantumTier newTier) {
        this.tier = newTier;
        this.energyCapacity = newTier.energyTransfer * 20;
        this.cachedEnergyCost = (int) (getBaseEnergyCost() * newTier.energyEfficiency);
        int newSize = newTier.getSlotPairs() * 2 + newTier.getAddonSlots();
        SimpleContainer oldInv = this.inventory;
        this.inventory = new SimpleContainer(newSize);
        for (int i = 0; i < Math.min(oldInv.getContainerSize(), newSize); i++) {
            this.inventory.setItem(i, oldInv.getItem(i).copy());
        }
        setChanged();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    protected int getBaseEnergyCost() { return 10; }
    protected int getEnergyCost() { return cachedEnergyCost > 0 ? cachedEnergyCost : getBaseEnergyCost(); }
    protected int getOutputSlot() { return tier.getSlotPairs(); }

    public void tickServer() {
        if (level == null || level.isClientSide) return;
        boolean didWork = false;
        int pairs = tier.getSlotPairs();
        for (int p = 0; p < pairs; p++) {
            int inSlot = p;
            int outSlot = pairs + p;
            ItemStack input = inventory.getItem(inSlot);
            if (input.isEmpty()) continue;
            if (!canProcess(input)) continue;
            ItemStack output = inventory.getItem(outSlot);
            if (!output.isEmpty() && output.getCount() >= output.getMaxStackSize()) continue;
            int cost = getEnergyCost();
            if (energyStored < cost) continue;
            progress++;
            energyStored -= cost;
            didWork = true;
            if (progress >= maxProgress) {
                doProcess(inSlot, outSlot);
                progress = 0;
            }
        }
        if (!didWork && progress > 0) progress = Math.max(0, progress - 1);
        containerData.set(0, energyStored);
        containerData.set(1, energyCapacity);
        containerData.set(2, progress);
        containerData.set(3, maxProgress);
        setChanged();
    }

    protected abstract boolean canProcess(ItemStack input);
    protected void doProcess() { doProcess(SLOT_INPUT, getOutputSlot()); }
    protected void doProcess(int inputSlot, int outputSlot) {
        ItemStack input = inventory.getItem(inputSlot);
        ItemStack output = inventory.getItem(outputSlot);
        ItemStack result = input.copy();
        result.setCount(1);
        input.shrink(1);
        if (output.isEmpty()) inventory.setItem(outputSlot, result);
        else output.grow(result.getCount());
    }

    @Override @Nullable
    public abstract AbstractContainerMenu createMenu(int id, Inventory inv, Player player);

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putInt("energyStored", energyStored);
        tag.putInt("progress", progress);
        tag.putBoolean("superposed", superposed);
        tag.put("inventory", inventory.createTag(provider));
        tag.putInt("tier", tier.id);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        energyStored = tag.getInt("energyStored");
        progress = tag.getInt("progress");
        superposed = tag.getBoolean("superposed");
        if (tag.contains("tier")) {
            tier = QuantumTier.fromId(tag.getInt("tier"));
            cachedEnergyCost = (int) (getBaseEnergyCost() * tier.energyEfficiency);
            energyCapacity = tier.energyTransfer * 20;
        }
        int size = tier.getSlotPairs() * 2 + tier.getAddonSlots();
        this.inventory = new SimpleContainer(size);
        if (tag.contains("inventory")) {
            inventory.fromTag(tag.getList("inventory", 10), provider);
        }
    }
}