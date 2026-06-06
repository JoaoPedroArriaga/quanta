package com.quanta.client.screen.cable;

import com.quanta.client.screen.component.*;
import com.quanta.content.menu.cable.CableConfigMenu;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class CableConfigScreen extends AbstractContainerScreen<CableConfigMenu> {

    private CableModeButton modeButton;
    private CablePriorityButton priorityButton;
    private AddFilterButton addFilterButton;
    private SmallCheckbox whitelistCheck;
    private SmallCheckbox filterEnabledCheck;
    private final List<FilterRow> filterRows = new ArrayList<>();

    private static final int LIST_X = 52;
    private static final int LIST_Y = 20;
    private static final int LIST_W = 120;
    private static final int LIST_H = 52;
    private static final int SCROLLBAR_X = LIST_X + LIST_W - 8;
    private static final int ITEM_H = 12;
    private static final int VISIBLE_ITEMS = 4;

    private int scrollOffset = 0;

    public CableConfigScreen(CableConfigMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 186;
        this.titleLabelX = 10;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 94;
    }

    @Override
    protected void init() {
        super.init();

        modeButton = new CableModeButton(leftPos + 7, topPos + 22,
            menu.config.getMode(), () -> menu.setMode(modeButton.getMode()));
        addRenderableWidget(modeButton);

        priorityButton = new CablePriorityButton(leftPos + 7, topPos + 40,
            menu.config.getPriority(), () -> menu.setPriority(priorityButton.getPriority()));
        addRenderableWidget(priorityButton);

        addFilterButton = new AddFilterButton(leftPos + 7, topPos + 58,
            () -> minecraft.setScreen(new FilterAddScreen(menu, minecraft.player.getInventory())));
        addRenderableWidget(addFilterButton);
        boolean supportsFilter = menu.cable != null && (
            menu.cable.getTypeManager().hasType(TransferType.ITEM)
            || menu.cable.getTypeManager().hasType(TransferType.FLUID)
            || menu.cable.getTypeManager().hasType(TransferType.GAS)
        );

        filterEnabledCheck = new SmallCheckbox(leftPos + 27, topPos + 58,
            Component.translatable("gui.quanta.cable.enable_filter"),
            menu.config.isFilterEnabled());
        filterEnabledCheck.setOnValueChange((cb, val) -> menu.setFilterEnabled(val));
        filterEnabledCheck.active = supportsFilter;
        addRenderableWidget(filterEnabledCheck);

        whitelistCheck = new SmallCheckbox(leftPos + 27, topPos + 76,
            Component.translatable("gui.quanta.cable.whitelist"),
            menu.config.isWhitelist());
        whitelistCheck.setOnValueChange((cb, val) -> menu.config.setWhitelist(val));
        whitelistCheck.active = supportsFilter;
        addRenderableWidget(whitelistCheck);

        addFilterButton.active = supportsFilter;

        rebuildFilterRows();
    }

    public void rebuildFilterRows() {
        filterRows.clear();
        List<ConnectionConfig.ItemFilter> filters = menu.config.getItemFilters();
        for (int i = scrollOffset; i < Math.min(filters.size(), scrollOffset + VISIBLE_ITEMS); i++) {
            int rowY = LIST_Y + 2 + (i - scrollOffset) * ITEM_H;
            filterRows.add(new FilterRow(leftPos + LIST_X + 2, topPos + rowY, i, filters.get(i)));
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(font,
            Component.translatable("screen.quanta.cable_config").append(": " + menu.face.getName().toUpperCase()),
            this.titleLabelX, this.titleLabelY, 0x60B7B7, false);
        guiGraphics.drawString(font, this.playerInventoryTitle,
            this.inventoryLabelX, this.inventoryLabelY, 0x60B7B7, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.PANEL_EXTENDED, leftPos, topPos, 176, 186);

        int lx = leftPos + LIST_X;
        int ly = topPos + LIST_Y;
        guiGraphics.fill(lx, ly, lx + LIST_W, ly + LIST_H, 0xFF1A1A2E);
        guiGraphics.fill(lx, ly, lx + LIST_W, ly + 1, 0xFF5A5A8C);
        guiGraphics.fill(lx, ly + LIST_H - 1, lx + LIST_W, ly + LIST_H, 0xFF5A5A8C);
        guiGraphics.fill(lx, ly, lx + 1, ly + LIST_H, 0xFF5A5A8C);
        guiGraphics.fill(lx + LIST_W - 1, ly, lx + LIST_W, ly + LIST_H, 0xFF5A5A8C);

        for (FilterRow row : filterRows) row.render(guiGraphics, mouseX, mouseY);
        renderScrollbar(guiGraphics);
    }

    private void renderScrollbar(GuiGraphics guiGraphics) {
        List<ConnectionConfig.ItemFilter> filters = menu.config.getItemFilters();
        if (filters.size() <= VISIBLE_ITEMS) return;

        int sbX = leftPos + SCROLLBAR_X;
        int sbY = topPos + LIST_Y + 2;
        int sbH = LIST_H - 4;

        float ratio = (float) VISIBLE_ITEMS / filters.size();
        int thumbH = Math.max(11, (int)(sbH * ratio));
        int maxScrollY = sbH - thumbH;
        int thumbY = sbY + (int)(maxScrollY * ((float) scrollOffset / (filters.size() - VISIBLE_ITEMS)));

        guiGraphics.fill(sbX, sbY, sbX + 6, sbY + sbH, 0xFF2A2A3C);
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_SCROLLBAR_THUMB, sbX, thumbY, 6, 11);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (isMouseOverList(mouseX, mouseY)) {
            int maxScroll = Math.max(0, menu.config.getItemFilters().size() - VISIBLE_ITEMS);
            scrollOffset = Math.clamp(scrollOffset - (int)Math.signum(scrollY), 0, maxScroll);
            rebuildFilterRows();
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    private boolean isMouseOverList(double mouseX, double mouseY) {
        return mouseX >= leftPos + LIST_X && mouseX <= leftPos + LIST_X + LIST_W
            && mouseY >= topPos + LIST_Y && mouseY <= topPos + LIST_Y + LIST_H;
    }

    @Override
    public void onClose() { menu.applyChanges(); super.onClose(); }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (FilterRow row : filterRows) {
            if (row.isDeleteHovered(mouseX, mouseY)) {
                menu.removeFilter(row.index);
                rebuildFilterRows();
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private class FilterRow {
        private final int x, y, index;
        private final ConnectionConfig.ItemFilter filter;

        FilterRow(int x, int y, int index, ConnectionConfig.ItemFilter filter) {
            this.x = x; this.y = y; this.index = index; this.filter = filter;
        }

        void render(GuiGraphics guiGraphics, int mouseX, int mouseY) {
            String display = filter.stack.isEmpty() ? filter.tagOrRegex : filter.stack.getDisplayName().getString();
            int maxW = LIST_W - 34; // reserve space for delete button
            if (font.width(display) > maxW) {
                while (font.width(display + ".") > maxW && display.length() > 0) display = display.substring(0, display.length() - 1);
                display = display + ".";
            }
            guiGraphics.drawString(font, display, x, y + 2, 0xFFFFFFFF, false);

            int delX = x + LIST_W - 16;
            GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_DELETE, delX, y + 2, 8, 8);
        }

        boolean isDeleteHovered(double mouseX, double mouseY) {
            return mouseX >= x + 120 && mouseX <= x + 128 && mouseY >= y + 2 && mouseY <= y + 10;
        }
    }
}