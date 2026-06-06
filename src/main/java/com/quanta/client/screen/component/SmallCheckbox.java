package com.quanta.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.BiConsumer;

public class SmallCheckbox extends AbstractButton {

    private boolean selected;
    private BiConsumer<SmallCheckbox, Boolean> onValueChange;

    public SmallCheckbox(int x, int y, Component message, boolean selected) {
        super(x, y, 10, 10, message);
        this.selected = selected;
    }

    public void setOnValueChange(BiConsumer<SmallCheckbox, Boolean> handler) {
        this.onValueChange = handler;
    }

    public boolean selected() {
        return selected;
    }

    @Override
    public void onPress() {
        selected = !selected;
        if (onValueChange != null) {
            onValueChange.accept(this, selected);
        }
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fill(getX(), getY(), getX() + 10, getY() + 10, 0xFF2A2A3C);
        guiGraphics.fill(getX(), getY(), getX() + 10, getY() + 1, 0xFF5A5A8C);
        guiGraphics.fill(getX(), getY() + 9, getX() + 10, getY() + 10, 0xFF5A5A8C);
        guiGraphics.fill(getX(), getY(), getX() + 1, getY() + 10, 0xFF5A5A8C);
        guiGraphics.fill(getX() + 9, getY(), getX() + 10, getY() + 10, 0xFF5A5A8C);

        if (selected) {
            guiGraphics.fill(getX() + 2, getY() + 2, getX() + 8, getY() + 8, 0xFF60B7B7);
        }

        guiGraphics.drawString(
            Minecraft.getInstance().font,
            getMessage(),
            getX() + 14, getY() + 1,
            0x60B7B7, false
        );
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}