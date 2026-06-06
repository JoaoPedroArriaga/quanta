package com.quanta.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class QuantaModeButton {

    private final int x, y, size;

    public QuantaModeButton(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean superposed) {
        GuiComponent.renderTexture(guiGraphics,
            superposed ? GuiTexture.MODE_SUPERPOSED : GuiTexture.MODE_DECOHERENT,
            leftPos + x, topPos + y, size, size);
    }

    public void renderTooltip(GuiGraphics guiGraphics, int leftPos, int topPos,
                               int mouseX, int mouseY, boolean superposed) {
        if (isHovering(leftPos, topPos, mouseX, mouseY)) {
            guiGraphics.renderTooltip(Minecraft.getInstance().font,
                Component.translatable(superposed ? "gui.quanta.mode.superposed" : "gui.quanta.mode.decoherent"),
                mouseX, mouseY);
        }
    }

    public boolean isHovering(int leftPos, int topPos, int mouseX, int mouseY) {
        return GuiComponent.isHovering(leftPos + x, topPos + y, size, size, mouseX, mouseY);
    }

    public boolean mouseClicked(int leftPos, int topPos, double mouseX, double mouseY) {
        return isHovering(leftPos, topPos, (int) mouseX, (int) mouseY);
    }
}