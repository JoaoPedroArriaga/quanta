package com.quanta.client.screen.component;

import com.quanta.content.menu.base.QuantaMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class Screen<T extends QuantaMenu> extends AbstractContainerScreen<T> {

    protected QuantaEnergyBar energyBar;
    protected QuantaModeButton modeButton;
    protected final int extraHeight;

    public Screen(T menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;

        int pairCount = menu.getSlotPairs();
        if (pairCount > 1) {
            extraHeight = 19;
        } else {
            extraHeight = 0;
        }

        this.imageHeight = 166 + extraHeight;
        this.titleLabelX = 36;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 74 + extraHeight;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        ResourceLocation panelTexture = extraHeight > 0 ? GuiTexture.PANEL_EXTENDED : GuiTexture.PANEL;
        GuiComponent.renderTexture(guiGraphics, panelTexture,
            this.leftPos, this.topPos, this.imageWidth, this.imageHeight);
        renderMachineSlots(guiGraphics, this.leftPos, this.topPos);
        renderMachineSpecific(guiGraphics, this.leftPos, this.topPos, partialTick, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x60B7B7, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x60B7B7, false);
    }

    protected abstract void renderMachineSlots(GuiGraphics guiGraphics, int leftPos, int topPos);
    protected abstract void renderMachineSpecific(GuiGraphics guiGraphics, int leftPos, int topPos,
                                                    float partialTick, int mouseX, int mouseY);

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        renderMachineTooltips(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
    }

    protected void renderMachineTooltips(GuiGraphics guiGraphics, int leftPos, int topPos,
                                          int mouseX, int mouseY) {
        if (energyBar != null) {
            energyBar.renderTooltip(guiGraphics, leftPos, topPos, mouseX, mouseY, getEnergyTooltip());
        }
        if (modeButton != null) {
            modeButton.renderTooltip(guiGraphics, leftPos, topPos, mouseX, mouseY, menu.isSuperposed());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (modeButton != null && modeButton.mouseClicked(this.leftPos, this.topPos, mouseX, mouseY)) {
            menu.toggleSuperposition();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    protected abstract Component getEnergyTooltip();
}