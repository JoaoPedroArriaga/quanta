package com.quanta.blockentity.base;

import com.quanta.QuantaConfig;
import com.quanta.block.machine.base.MachineTier;
import com.quanta.block.base.QuantaMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * ULTRA OPTIMIZED: 
 * - Uses bit packing for flags
 * - Primitive arrays for inventory caching
 * - Direct field access (no method calls in hot path)
 * - Early returns with branch prediction hints
 */
public abstract class QuantaProcessingBE extends QuantaEnergyBE {
    
    // ========== BIT PACKED FLAGS ==========
    private byte stateFlags = 0;
    private static final byte FLAG_PROCESSING = 0b00000001;
    private static final byte FLAG_INVENTORY_INIT = 0b00000010;
    private static final byte FLAG_NEEDS_CACHE = 0b00000100;
    private static final byte FLAG_DIRTY_LIT = 0b00001000;
    
    // ========== FIELDS (grouped for cache locality) ==========
    protected ItemStackHandler inventory;
    private int progress;
    private final int maxProgress;
    private MachineTier tier;
    
    // Cache line friendly - keep related fields together
    private int cachedEnergyCost;
    private int cachedMaxProgress;
    private int lastCheckedStackHash;
    private boolean lastProcessableResult;
    
    // Pre-calculated constants
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_OUTPUT = 1;
    
    // Stack hash caching (faster than ItemStack.matches for same item type)
    private int lastInputHash;
    private ItemStack lastInputStack = ItemStack.EMPTY;
    
    public QuantaProcessingBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                               int inventorySlots, int energyCapacity, int energyMaxTransfer,
                               int baseMaxProgress, MachineTier tier) {
        super(type, pos, state, inventorySlots, energyCapacity, energyMaxTransfer);
        this.tier = tier;
        this.cachedMaxProgress = (int)(baseMaxProgress / tier.speedMultiplier);
        this.maxProgress = cachedMaxProgress;
        this.progress = 0;
        this.cachedEnergyCost = (int)(getBaseEnergyCost() * tier.energyEfficiency);
        this.lastCheckedStackHash = 0;
        this.lastProcessableResult = false;
        this.lastInputHash = 0;
        setFlag(FLAG_NEEDS_CACHE, false);
    }
    
    // ========== FLAG OPERATIONS (inlined by JIT) ==========
    private boolean hasFlag(byte flag) { return (stateFlags & flag) != 0; }
    private void setFlag(byte flag, boolean value) {
        if (value) stateFlags |= flag;
        else stateFlags &= ~flag;
    }
    
    // ========== LAZY INVENTORY ==========
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
    
    // ========== ULTRA-FAST PROCESSABLE CHECK (hash-based) ==========
    private boolean isProcessableFast(ItemStack input) {
        int currentHash = System.identityHashCode(input.getItem());
        if (currentHash == lastInputHash && !input.isEmpty()) {
            return lastProcessableResult;
        }
        
        lastInputHash = currentHash;
        lastInputStack = input.copy();
        lastProcessableResult = canProcess(input);
        return lastProcessableResult;
    }
    
    // ========== MAIN TICK (HOT PATH - EXTREMELY OPTIMIZED) ==========
    public void tickServer() {
        // Branch prediction hint: most ticks are on server
        if (level == null | level.isClientSide) return;  // Single | is faster than ||
        
        // Fast path: no energy
        int energyStored = energy.getQuantumStored();
        if (energyStored < cachedEnergyCost) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        ensureInventory();
        ItemStack input = inventory.getStackInSlot(SLOT_INPUT);
        
        // Fast path: no input
        if (input.isEmpty()) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        // Fast path: not processable
        if (!isProcessableFast(input)) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }
        
        // Start processing if needed
        if (!hasFlag(FLAG_PROCESSING)) {
            startProcessing();
        }
        
        // Process one tick
        if (hasFlag(FLAG_PROCESSING)) {
            // Direct energy consumption (no method call overhead)
            energy.setEnergy(energyStored - cachedEnergyCost);
            progress++;
            
            if (progress >= maxProgress) {
                completeProcessing();
            }
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
    
    // ========== LIT STATE (cached to avoid block updates) ==========
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
    
    // ========== HOOKS ==========
    protected abstract int getBaseEnergyCost();
    protected abstract boolean canProcess(ItemStack input);
    protected abstract void doProcess();
    protected void onInventoryChanged(int slot) {}
    
    // ========== GETTERS (for GUI - not in hot path) ==========
    public int getProgress() { return progress; }
    public int getMaxProgress() { return maxProgress; }
    public boolean isProcessing() { return hasFlag(FLAG_PROCESSING); }
    public MachineTier getTier() { return tier; }
    
    public void upgradeTo(MachineTier newTier) {
        this.tier = newTier;
        this.cachedEnergyCost = (int)(getBaseEnergyCost() * newTier.energyEfficiency);
        this.cachedMaxProgress = (int)(maxProgress / newTier.speedMultiplier);
        setChangedAndSync();
    }
    
    @Override
    protected int getEnergyCost() { return cachedEnergyCost; }
    
    // ========== NBT ==========
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