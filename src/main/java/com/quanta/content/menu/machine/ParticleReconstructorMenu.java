package com.quanta.content.menu.machine;

import com.quanta.content.blockentity.machine.ParticleReconstructorBE;
import com.quanta.content.menu.ModMenuTypes;
import com.quanta.content.menu.base.QuantaMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ParticleReconstructorMenu extends QuantaMenu {

    private final ParticleReconstructorBE blockEntity;
    private final int pairCount;
    private final int addonSlots;
    private final boolean superposed;
    private final SimpleContainer machineInventory;

    private static final int SLOT = 18;
    private static final int GAP = 2;
    private static final int COL_GAP = 4;
    private static final int PROG_H_W = 34;
    private static final int PROG_V_H = 26;
    
    private static final int ENERGY_X = 7;
    private static final int ENERGY_W = 12;
    private static final int ENERGY_BAR_END = ENERGY_X + ENERGY_W;
    private static final int MIN_DISTANCE = 12;
    private static final int MIN_START_X = ENERGY_BAR_END + MIN_DISTANCE;
    
    private static final int INPUT_Y = 20;
    private static final int SINGLE_Y = INPUT_Y + 12;
    private static final int OUTPUT_Y_MULTI = INPUT_Y + SLOT + GAP + PROG_V_H + GAP;
    private static final int ADDON_X = 178;
    private static final int ADDON_Y = 9;

    public ParticleReconstructorMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), containerId, new SimpleContainerData(4));
        
        BlockPos pos = buf.readBlockPos();
        int tierId = buf.readVarInt();
        this.pairCount = buf.readVarInt();
        this.addonSlots = buf.readVarInt();
        this.superposed = buf.readBoolean();
        
        this.machineInventory = new SimpleContainer(pairCount * 2 + addonSlots);
        
        BlockEntity be = inv.player.level().getBlockEntity(pos);
        this.blockEntity = be instanceof ParticleReconstructorBE ? (ParticleReconstructorBE) be : null;
        
        addMachineSlots();
        addPlayerSlots(inv);
    }

    public ParticleReconstructorMenu(int containerId, Inventory inv, ParticleReconstructorBE be) {
        super(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), containerId, be.getContainerData());
        this.blockEntity = be;
        this.pairCount = be.getTier().getSlotPairs();
        this.addonSlots = be.getTier().getAddonSlots();
        this.superposed = be.isSuperposed();
        this.machineInventory = be.getInventory();
        addMachineSlots();
        addPlayerSlots(inv);
    }

    public ParticleReconstructorMenu(int containerId, Inventory inv, ParticleReconstructorBE be, ContainerData data) {
        super(ModMenuTypes.PARTICLE_RECONSTRUCTOR.get(), containerId, data);
        this.blockEntity = be;
        this.pairCount = be.getTier().getSlotPairs();
        this.addonSlots = be.getTier().getAddonSlots();
        this.superposed = be.isSuperposed();
        this.machineInventory = be.getInventory();
        addMachineSlots();
        addPlayerSlots(inv);
    }

    private void addMachineSlots() {
        int slotIdx = 0;

        if (pairCount == 1) {
            int totalW = SLOT + GAP + PROG_H_W + GAP + SLOT;
            int startX = (176 - totalW) / 2;
            int inputX = startX;
            int outputX = startX + SLOT + GAP + PROG_H_W + GAP;

            this.addSlot(new Slot(this.machineInventory, slotIdx++, inputX, SINGLE_Y) {
                @Override
                public boolean mayPlace(ItemStack stack) { return isItemProcessable(stack); }
            });
            this.addSlot(new Slot(this.machineInventory, slotIdx++, outputX, SINGLE_Y) {
                @Override
                public boolean mayPlace(ItemStack stack) { return false; }
            });
        } else {
            int totalW = pairCount * SLOT + (pairCount - 1) * COL_GAP;
            int startX = (176 - totalW) / 2;
            
            if (startX < MIN_START_X) {
                startX = MIN_START_X;
            }

            for (int i = 0; i < pairCount; i++) {
                int colX = startX + i * (SLOT + COL_GAP);
                this.addSlot(new Slot(this.machineInventory, slotIdx++, colX, INPUT_Y) {
                    @Override
                    public boolean mayPlace(ItemStack stack) { return isItemProcessable(stack); }
                });
            }
            for (int i = 0; i < pairCount; i++) {
                int colX = startX + i * (SLOT + COL_GAP);
                this.addSlot(new Slot(this.machineInventory, slotIdx++, colX, OUTPUT_Y_MULTI) {
                    @Override
                    public boolean mayPlace(ItemStack stack) { return false; }
                });
            }
        }

        for (int i = 0; i < addonSlots; i++) {
            this.addSlot(new Slot(this.machineInventory, slotIdx++, ADDON_X, ADDON_Y + i * 20) {
                @Override
                public boolean mayPlace(ItemStack stack) { return isAddonItem(stack); }
                @Override
                public int getMaxStackSize() { return 1; }
            });
        }
    }

    private boolean isAddonItem(ItemStack stack) {
        String name = stack.getItem().toString().toLowerCase();
        return name.contains("coil") || name.contains("addon");
    }

    @Override
    public int getAddonSlots() { return addonSlots; }
    @Override
    public int getSlotPairs() { return pairCount; }
    @Override
    public boolean isSuperposed() { return blockEntity != null ? blockEntity.isSuperposed() : superposed; }
    @Override
    public void toggleSuperposition() { if (blockEntity != null) blockEntity.toggleSuperposition(); }

    @Override
    protected boolean isItemProcessable(ItemStack stack) {
        String path = stack.getItem().toString().toLowerCase();
        return path.contains("dust") || path.contains("redstone") || path.contains("glowstone");
    }
}