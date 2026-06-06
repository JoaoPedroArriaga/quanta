package com.quanta.client.screen.component;

import com.quanta.core.transfer.Priority;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CablePriorityButton extends AbstractButton {

    private static final int SIZE = 16;
    private Priority priority;
    private final Runnable onChanged;

    public CablePriorityButton(int x, int y, Priority priority, Runnable onChanged) {
        super(x, y, SIZE, SIZE, Component.empty());
        this.priority = priority;
        this.onChanged = onChanged;
    }

    @Override
    public void onPress() {
        priority = priority.next();
        if (onChanged != null) onChanged.run();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered()) {
            if (button == 0) {
                onPress();
            } else if (button == 1) {
                priority = priority.previous();
                if (onChanged != null) onChanged.run();
            }
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        ResourceLocation texture;
        switch (priority) {
            case LOWEST: texture = GuiTexture.PRIORITY_LOWEST; break;
            case LOW: texture = GuiTexture.PRIORITY_LOW; break;
            case NORMAL: texture = GuiTexture.PRIORITY_NORMAL; break;
            case HIGH: texture = GuiTexture.PRIORITY_HIGH; break;
            case HIGHEST: texture = GuiTexture.PRIORITY_HIGHEST; break;
            case CRITICAL: texture = GuiTexture.PRIORITY_CRITICAL; break;
            default: texture = GuiTexture.PRIORITY_NORMAL; break;
        }
        GuiComponent.renderTexture(guiGraphics, texture, getX(), getY(), SIZE, SIZE);

        if (isHovered()) {
            guiGraphics.renderTooltip(
                Minecraft.getInstance().font,
                Component.translatable("gui.quanta.cable.priority." + priority.name().toLowerCase()),
                mouseX, mouseY
            );
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}