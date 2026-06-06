package com.quanta.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class EnumCycleButton extends Button {

    private static final int WIDTH = 70;
    private static final int HEIGHT = 14;
    private static final int ARROW_WIDTH = 10;

    private final String[] values;
    private int index;
    private final Runnable onChanged;

    public EnumCycleButton(int x, int y, String[] values, int initialIndex, Runnable onChanged) {
        super(x, y, WIDTH, HEIGHT, Component.empty(), btn -> {}, DEFAULT_NARRATION);
        this.values = values;
        this.index = initialIndex;
        this.onChanged = onChanged;
    }

    @Override
    public void onPress() {
        index = (index + 1) % values.length;
        if (onChanged != null) onChanged.run();
    }

    public int getIndex() { return index; }
    public String getValue() { return values[index]; }
    public void setIndex(int idx) { if (idx >= 0 && idx < values.length) index = idx; }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_BUTTON, getX(), getY(), WIDTH, HEIGHT);
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_BUTTON_LEFT, getX(), getY(), ARROW_WIDTH, HEIGHT);
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_BUTTON_RIGHT, getX() + WIDTH - ARROW_WIDTH, getY(), ARROW_WIDTH, HEIGHT);
        String text = values[index];
        int textW = Minecraft.getInstance().font.width(text);
        guiGraphics.drawString(Minecraft.getInstance().font, text,
            getX() + (WIDTH - textW) / 2, getY() + (HEIGHT - 8) / 2, 0xFFFFFFFF, false);
    }
}