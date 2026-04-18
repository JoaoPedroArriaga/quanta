package com.quanta.blockentity.base;

import com.quanta.block.machine.base.MachineTier;
import com.quanta.block.base.QuantaMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public abstract class QuantaProcessingBE extends QuantaEnergyBE {
    
    private byte stateFlags = 0;
    private static final byte FLAG_PROCESSING = 0b00000001;
    private static final byte FLAG_INVENTORY_INIT = 0b00000010;
    
    protected ItemStackHandler inventory;
    private int progress;
    private final int maxProgress;
    private MachineTier tier;
    
    private int cachedEnergyCost;
    private int lastInputHash;
    private boolean lastProcessableResult;
    
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_OUTPUT = 1;
    
    private ContainerData cachedContainerData = null;
    
    public QuantaProcessingBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                               int inventorySlots, int energyCapacity, int energyMaxTransfer,
                               int baseMaxProgress, MachineTier tier) {
        super(type, pos, state, inventorySlots, energyCapacity, energyMaxTransfer);
        this.tier = tier;
        this.maxProgress = (int)(baseMaxProgress / tier.speedMultiplier);
        this.progress = 0;
        this.cachedEnergyCost = (int)(getBaseEnergyCost() * tier.energyEfficiency);
    }
    
    private boolean hasFlag(byte flag) { return (stateFlags & flag) != 0; }
    private void setFlag(byte flag, boolean value) {
        if (value) stateFlags |= flag;
        else stateFlags &= ~flag;
    }
    
    private void ensureInventory() {
        if (!hasFlag(FLAG_INVENTORY_INIT)) {
            this.inventory = new ItemStackHandler(inventorySlots) {
                @Override
                protected void onContentsChanged(int slot) {
                    setChangedAndSync();
                    onInventoryChanged(slot);
                }
            };
            setFlag(FLAG_INVENTORY_INIT, true);
        }
    }
    
    public ItemStackHandler getInventory() {
        ensureInventory();
        return inventory;
    }
    
    public ContainerData getContainerData() {
        if (cachedContainerData == null) {
            cachedContainerData = new ContainerData() {
                @Override
                public int get(int index) {
                    return switch (index) {
                        case 0 -> progress;
                        case 1 -> maxProgress;
                        default -> 0;
                    };
                }
                
                @Override
                public void set(int index, int value) {
                    if (index == 0) progress = value;
                }
                
                @Override
                public int getCount() { return 2; }
            };
        }
        return cachedContainerData;
    }
    
    private boolean isProcessableFast(ItemStack input) {
        int currentHash = System.identityHashCode(input.getItem());
        if (currentHash == lastInputHash && !input.isEmpty()) {
            return lastProcessableResult;
        }
        lastInputHash = currentHash;
        lastProcessableResult = canProcess(input);
        return lastProcessableResult;
    }
    
    public void tickServer() {
        if (level == null | level.isClientSide) return;
        
        int energyStored = energy.getQuantumStored();
        if (energyStored < cachedEnergyCost) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        ensureInventory();
        ItemStack input = inventory.getStackInSlot(SLOT_INPUT);
        
        if (input.isEmpty()) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        if (!isProcessableFast(input)) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        if (!hasFlag(FLAG_PROCESSING)) startProcessing();
        
        if (hasFlag(FLAG_PROCESSING)) {
            energy.setEnergy(energyStored - cachedEnergyCost);
            progress++;
            if (progress >= maxProgress) completeProcessing();
            setChangedAndSync();
        }
    }
    
    private void startProcessing() {
        setFlag(FLAG_PROCESSING, true);
        updateLitState(true);
    }
    
    private void stopProcessing() {
        setFlag(FLAG_PROCESSING, false);
        progress = 0;
        updateLitState(false);
        setChangedAndSync();
    }
    
    private void completeProcessing() {
        doProcess();
        progress = 0;
        setFlag(FLAG_PROCESSING, false);
        updateLitState(false);
        setChangedAndSync();
    }
    
    private boolean lastLitValue = false;
    
    private void updateLitState(boolean lit) {
        if (level != null && !level.isClientSide) {
            if (lit != lastLitValue) {
                BlockState state = level.getBlockState(worldPosition);
                if (state.hasProperty(QuantaMachineBlock.LIT)) {
                    level.setBlock(worldPosition, state.setValue(QuantaMachineBlock.LIT, lit), 3);
                    lastLitValue = lit;
                }
            }
        }
    }
    
    protected abstract int getBaseEnergyCost();
    protected abstract boolean canProcess(ItemStack input);
    protected abstract void doProcess();
    protected void onInventoryChanged(int slot) {}
    
    public int getProgress() { return progress; }
    public int getMaxProgress() { return maxProgress; }
    public boolean isProcessing() { return hasFlag(FLAG_PROCESSING); }
    public MachineTier getTier() { return tier; }
    
    @Override
    protected int getEnergyCost() { return cachedEnergyCost; }
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (hasFlag(FLAG_INVENTORY_INIT)) {
            tag.put("inventory", inventory.serializeNBT(registries));
        }
        tag.putInt("progress", progress);
        tag.putBoolean("processing", hasFlag(FLAG_PROCESSING));
        tag.putInt("tier", tier.id);
    }
    
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("inventory")) {
            ensureInventory();
            inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        }
        progress = tag.getInt("progress");
        setFlag(FLAG_PROCESSING, tag.getBoolean("processing"));
        this.tier = MachineTier.fromId(tag.getInt("tier"));
        this.cachedEnergyCost = (int)(getBaseEnergyCost() * this.tier.energyEfficiency);
    }
}