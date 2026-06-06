package com.quanta.client.screen.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class Slot {

    private final int x, y;
    private final ResourceLocation texture;

    public Slot(int x, int y, ResourceLocation texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public void render(GuiGraphics guiGraphics, int leftPos, int topPos) {
        GuiComponent.renderSlot(guiGraphics, texture, leftPos + x, topPos + y, 18);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}