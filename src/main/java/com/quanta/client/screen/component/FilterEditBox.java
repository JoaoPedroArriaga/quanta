package com.quanta.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class FilterEditBox extends EditBox {

    public FilterEditBox(int x, int y, int width, int height, Component hint) {
        super(Minecraft.getInstance().font, x, y, width, height, hint);
        setBordered(false);
        setMaxLength(128);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_TEXT_FIELD, getX(), getY(), getWidth(), getHeight());

        String display = getValue().isEmpty() && !isFocused() ? getMessage().getString() : getValue();
        // truncate to fit
        int maxW = getWidth() - 8;
        while (Minecraft.getInstance().font.width(display) > maxW && display.length() > 0) {
            display = display.substring(0, display.length() - 1);
        }
        int textY = getY() + (getHeight() - 8) / 2;
        int textColor = getValue().isEmpty() && !isFocused() ? 0x4060B7B7 : 0xFF60B7B7;

        guiGraphics.drawString(Minecraft.getInstance().font, display, getX() + 4, textY, textColor, false);
    }
}