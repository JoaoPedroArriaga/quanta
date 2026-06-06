package com.quanta.client.screen.component;

import com.quanta.client.screen.component.GuiComponent;
import com.quanta.client.screen.component.GuiTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class WidgetButton extends AbstractButton {

    private final Runnable onPressAction;

    public WidgetButton(int x, int y, int width, int height, Component message, Runnable onPressAction) {
        super(x, y, width, height, message);
        this.onPressAction = onPressAction;
    }

    @Override
    public void onPress() {
        if (onPressAction != null) onPressAction.run();
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.WIDGET_BUTTON, getX(), getY(), getWidth(), getHeight());

        int textW = Minecraft.getInstance().font.width(getMessage());
        guiGraphics.drawString(Minecraft.getInstance().font, getMessage(),
            getX() + (getWidth() - textW) / 2,
            getY() + (getHeight() - 8) / 2,
            0x60B7B7, false);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}