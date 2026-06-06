package com.quanta.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class QuantaEnergyBar {

    private final int x, y, width, height;

    public QuantaEnergyBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(GuiGraphics guiGraphics, int leftPos, int topPos,
                        int stored, int capacity) {
        GuiComponent.renderEnergyBar(guiGraphics,
            leftPos + x, topPos + y, stored, capacity, width, height);
    }

    public void renderTooltip(GuiGraphics guiGraphics, int leftPos, int topPos,
                               int mouseX, int mouseY, Component text) {
        if (isHovering(leftPos, topPos, mouseX, mouseY)) {
            guiGraphics.renderTooltip(Minecraft.getInstance().font, text, mouseX, mouseY);
        }
    }

    public boolean isHovering(int leftPos, int topPos, int mouseX, int mouseY) {
        return GuiComponent.isHovering(leftPos + x, topPos + y, width, height, mouseX, mouseY);
    }
}