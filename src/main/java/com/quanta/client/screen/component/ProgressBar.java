package com.quanta.client.screen.component;

import net.minecraft.client.gui.GuiGraphics;

public class ProgressBar {

    private final int x, y, width, height;
    private final boolean vertical;

    public ProgressBar(int x, int y, int width, int height) {
        this(x, y, width, height, false);
    }

    public ProgressBar(int x, int y, int width, int height, boolean vertical) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vertical = vertical;
    }

    public void renderBg(GuiGraphics guiGraphics, int leftPos, int topPos) {
        GuiComponent.renderTexture(guiGraphics,
            vertical ? GuiTexture.PROGRESS_BAR_V : GuiTexture.PROGRESS_BAR_H,
            leftPos + x, topPos + y, width, height);
    }

    public void renderFill(GuiGraphics guiGraphics, int leftPos, int topPos,
                            int progress, int maxProgress) {
        if (vertical) {
            GuiComponent.renderProgressBarFillVertical(guiGraphics,
                leftPos + x, topPos + y, progress, maxProgress, width, height);
        } else {
            GuiComponent.renderProgressBarFill(guiGraphics,
                leftPos + x, topPos + y, progress, maxProgress, width, height);
        }
    }
}