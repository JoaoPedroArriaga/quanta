package com.quanta.content.menu.cable;

import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.content.menu.ModMenuTypes;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.Priority;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CableConfigMenu extends AbstractContainerMenu {

    public final CableBlockEntity cable;
    public final Direction face;
    public final ConnectionConfig config;

    public CableConfigMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenuTypes.CABLE_CONFIG.get(), containerId);
        BlockPos pos = buf.readBlockPos();
        this.face = Direction.from3DDataValue(buf.readByte());
        BlockEntity be = inv.player.level().getBlockEntity(pos);
        this.cable = be instanceof CableBlockEntity ? (CableBlockEntity) be : null;
        this.config = cable != null ? cable.getConnectionManager().getConfig(face).copy() : new ConnectionConfig();
        addPlayerSlots(inv);
    }

    public CableConfigMenu(int containerId, Inventory inv, CableBlockEntity cable, Direction face) {
        super(ModMenuTypes.CABLE_CONFIG.get(), containerId);
        this.cable = cable;
        this.face = face;
        this.config = cable.getConnectionManager().getConfig(face).copy();
        addPlayerSlots(inv);
    }

    private void addPlayerSlots(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 104 + row * 18));
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 162));
    }

    public void applyChanges() {
        if (cable != null) {
            cable.getConnectionManager().setConfig(face, config);
            cable.updateConnections();
            cable.syncToClient();
            cable.requestModelDataUpdate();
        }
    }

    public void setMode(ConnectionConfig.Mode mode) { config.setMode(mode); }
    public void setPriority(Priority priority) { config.setPriority(priority); }
    public void toggleFilterEnabled() { config.setFilterEnabled(!config.isFilterEnabled()); }
    public void setFilterEnabled(boolean enabled) { config.setFilterEnabled(enabled); }

    public void addFilter(ConnectionConfig.ItemFilter filter) {
        for (ConnectionConfig.ItemFilter existing : config.getItemFilters()) {
            if (existing.stack.isEmpty() && filter.stack.isEmpty() 
                && existing.tagOrRegex.equals(filter.tagOrRegex)) return;
            if (!existing.stack.isEmpty() && !filter.stack.isEmpty() 
                && ItemStack.isSameItemSameComponents(existing.stack, filter.stack)) return;
        }
        config.addItemFilter(filter);
    }

    public void removeFilter(int index) { config.removeItemFilter(index); }

    @Override
    public ItemStack quickMoveStack(Player player, int index) { return ItemStack.EMPTY; }

    @Override
    public boolean stillValid(Player player) { return cable != null && !cable.isRemoved(); }
}