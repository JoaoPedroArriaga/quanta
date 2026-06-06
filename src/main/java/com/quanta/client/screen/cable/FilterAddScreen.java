package com.quanta.client.screen.cable;

import com.quanta.client.screen.component.FilterEditBox;
import com.quanta.client.screen.component.GuiComponent;
import com.quanta.client.screen.component.GuiTexture;
import com.quanta.client.screen.component.SmallCheckbox;
import com.quanta.client.screen.component.WidgetButton;
import com.quanta.content.menu.cable.CableConfigMenu;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidUtil;

public class FilterAddScreen extends AbstractContainerScreen<CableConfigMenu> {

    private final CableConfigMenu parentMenu;
    private FilterEditBox tagField;
    private SmallCheckbox nbtCheck;
    private SmallCheckbox damageCheck;
    private SmallCheckbox enchantsCheck;
    private SmallCheckbox countCheck;

    private ItemStack filterStack = ItemStack.EMPTY;
    private ConnectionConfig.FilterType detectedType = null;

    public FilterAddScreen(CableConfigMenu menu, Inventory inv) {
        super(menu, inv, Component.translatable("screen.quanta.add_filter"));
        this.parentMenu = menu;
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

        int x = leftPos;
        int y = topPos;

        tagField = new FilterEditBox(x + 29, y + 22, 130, 14,
            Component.translatable("gui.quanta.cable.filter.tag_hint"));
        addRenderableWidget(tagField);

        nbtCheck = new SmallCheckbox(x + 29, y + 42, Component.literal("NBT"), false);
        addRenderableWidget(nbtCheck);

        damageCheck = new SmallCheckbox(x + 80, y + 42, Component.literal("Damage"), false);
        addRenderableWidget(damageCheck);

        enchantsCheck = new SmallCheckbox(x + 29, y + 58, Component.literal("Enchants"), false);
        addRenderableWidget(enchantsCheck);

        countCheck = new SmallCheckbox(x + 80, y + 58, Component.literal("Count"), false);
        addRenderableWidget(countCheck);

        addRenderableWidget(new WidgetButton(x + 23, y + 76, 50, 14,
            Component.translatable("gui.quanta.cable.config.save"), () -> saveFilter()));

        addRenderableWidget(new WidgetButton(x + 103, y + 76, 50, 14,
            Component.translatable("gui.quanta.cable.config.cancel"), () -> onClose()));
    }

    private void saveFilter() {
        ConnectionConfig.ItemFilter filter = new ConnectionConfig.ItemFilter();

        if (detectedType != null) {
            filter.type = detectedType;
            filter.stack = filterStack.copy();
        } else {
            filter.type = ConnectionConfig.FilterType.TAG;
            filter.stack = ItemStack.EMPTY;
        }

        filter.tagOrRegex = tagField.getValue().trim();
        filter.matchNbt = nbtCheck.selected();
        filter.ignoreDamage = damageCheck.selected();
        filter.ignoreEnchants = enchantsCheck.selected();
        filter.ignoreCount = countCheck.selected();
        parentMenu.addFilter(filter);
        onClose();
    }

    @Override
    public void onClose() {
        if (minecraft != null) {
            minecraft.setScreen(new CableConfigScreen(parentMenu, minecraft.player.getInventory(),
                Component.translatable("screen.quanta.cable_config")));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= leftPos + 7 && mouseX <= leftPos + 25 && mouseY >= topPos + 18 && mouseY <= topPos + 36) {
            handleFilterSlotClick(button);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void handleFilterSlotClick(int button) {
        var player = minecraft.player;
        if (player == null) return;

        ItemStack carried = player.containerMenu.getCarried();

        if (carried.isEmpty()) {
            filterStack = ItemStack.EMPTY;
            detectedType = null;
            tagField.setValue("");
            return;
        }

        if (button == 1 && parentMenu.cable != null
            && parentMenu.cable.getTypeManager().hasType(TransferType.FLUID)) {
            var fluidHandlerOpt = FluidUtil.getFluidHandler(carried);
            if (fluidHandlerOpt.isPresent()) {
                var fluidHandler = fluidHandlerOpt.get();
                net.neoforged.neoforge.fluids.FluidStack contained = net.neoforged.neoforge.fluids.FluidStack.EMPTY;
                try {
                    // Try to read tank 0 if available
                    contained = fluidHandler.getFluidInTank(0);
                } catch (Throwable ignored) {
                    try {
                        // Fallback: try draining a small amount as simulation
                        contained = fluidHandler.drain(1000, net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE);
                    } catch (Throwable ignored2) { }
                }
                if (!contained.isEmpty()) {
                    // Use fluid id as filter while still showing the container in the slot
                    filterStack = carried.copyWithCount(1);
                    detectedType = ConnectionConfig.FilterType.FLUID;
                    var fluid = contained.getFluid();
                    var id = net.minecraft.core.registries.BuiltInRegistries.FLUID.getKey(fluid);
                    if (id != null) tagField.setValue(id.toString()); else tagField.setValue("");
                    return;
                }
            }
        }

        // Default: set as item filter
        filterStack = carried.copyWithCount(1);
        detectedType = ConnectionConfig.FilterType.ITEM;

        ResourceLocation id = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(filterStack.getItem());
        if (id != null) {
            tagField.setValue(id.toString());
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(font,
            Component.translatable("screen.quanta.add_filter"),
            this.titleLabelX, this.titleLabelY, 0x60B7B7, false);
        guiGraphics.drawString(font, this.playerInventoryTitle,
            this.inventoryLabelX, this.inventoryLabelY, 0x60B7B7, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.PANEL_EXTENDED, leftPos, topPos, imageWidth, imageHeight);

        GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_FILTER, leftPos + 7, topPos + 18, 18);
        if (!filterStack.isEmpty()) {
            guiGraphics.renderItem(filterStack, leftPos + 8, topPos + 19);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}