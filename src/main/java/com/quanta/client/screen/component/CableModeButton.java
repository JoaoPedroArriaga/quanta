package com.quanta.client.screen.component;

import com.quanta.core.transfer.ConnectionConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CableModeButton extends AbstractButton {

    private static final int SIZE = 16;
    private ConnectionConfig.Mode mode;
    private final Runnable onChanged;

    public CableModeButton(int x, int y, ConnectionConfig.Mode mode, Runnable onChanged) {
        super(x, y, SIZE, SIZE, Component.empty());
        this.mode = mode;
        this.onChanged = onChanged;
    }

    @Override
    public void onPress() {
        mode = mode.next();
        if (onChanged != null) onChanged.run();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered()) {
            if (button == 0) {
                onPress();
            } else if (button == 1) {
                mode = mode.next();
                mode = mode.next();
                if (onChanged != null) onChanged.run();
            }
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public ConnectionConfig.Mode getMode() {
        return mode;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        ResourceLocation texture;
        switch (mode) {
            case INSERT: texture = GuiTexture.INSERT_MODE; break;
            case EXTRACT: texture = GuiTexture.EXTRACT_MODE; break;
            default: texture = GuiTexture.DISABLED_MODE; break;
        }
        GuiComponent.renderTexture(guiGraphics, texture, getX(), getY(), SIZE, SIZE);

        if (isHovered()) {
            guiGraphics.renderTooltip(
                Minecraft.getInstance().font,
                Component.translatable("gui.quanta.cable.mode." + mode.name().toLowerCase()),
                mouseX, mouseY
            );
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}