package com.quanta.content.blockentity.base;

import com.quanta.content.block.base.QuantaMachineBlock;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
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
    private final int inventorySlots;

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_OUTPUT = 1;

    private ContainerData cachedContainerData;

    public QuantaProcessingBE(BlockEntityType<?> type, BlockPos pos, BlockState state,
                              int inventorySlots, QuantumTier tier, int baseMaxProgress) {
        super(type, pos, state, tier);
        this.inventorySlots = inventorySlots;
        this.maxProgress = (int) (baseMaxProgress / getSpeedMultiplier(tier));
        this.progress = 0;
    }

    private float getSpeedMultiplier(QuantumTier tier) {
        return switch (tier) {
            case DECOHERENT -> 1.0f;
            case ENTANGLED -> 1.5f;
            case SUPERPOSED -> 2.0f;
            case SINGULAR -> 3.0f;
        };
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
            cachedContainerData = new SimpleContainerData(2) {
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
            };
        }
        return cachedContainerData;
    }

    public void tickServer() {
        if (level == null || level.isClientSide) return;

        ensureInventory();
        ItemStack input = inventory.getStackInSlot(SLOT_INPUT);

        if (input.isEmpty()) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }

        if (!canProcess(input)) {
            if (hasFlag(FLAG_PROCESSING)) stopProcessing();
            return;
        }

        if (!hasFlag(FLAG_PROCESSING)) {
            if (!hasEnoughEnergy(cachedEnergyCost)) return;
            startProcessing();
        }

        if (hasFlag(FLAG_PROCESSING)) {
            if (!hasEnoughEnergy(cachedEnergyCost)) {
                stopProcessing();
                return;
            }
            consumeEnergy(cachedEnergyCost);
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

    private void updateLitState(boolean lit) {
        if (level != null && !level.isClientSide) {
            BlockState state = level.getBlockState(worldPosition);
            if (state.hasProperty(QuantaMachineBlock.LIT)) {
                level.setBlock(worldPosition, state.setValue(QuantaMachineBlock.LIT, lit), 3);
            }
        }
    }

    protected abstract boolean canProcess(ItemStack input);
    protected abstract void doProcess();

    public int getProgress() { return progress; }
    public int getMaxProgress() { return maxProgress; }
    public boolean isProcessing() { return hasFlag(FLAG_PROCESSING); }
    public QuantumTier getTier() { return tier; }

    @Override
    protected int getBaseEnergyCost() { return 10; }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (hasFlag(FLAG_INVENTORY_INIT)) tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putBoolean("processing", hasFlag(FLAG_PROCESSING));
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
    }
}