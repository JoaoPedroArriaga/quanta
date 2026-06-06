package com.quanta.client.screen.component;

import com.quanta.client.screen.component.GuiComponent;
import com.quanta.client.screen.component.GuiTexture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class AddFilterButton extends AbstractButton {

    private final Runnable onPressAction;

    public AddFilterButton(int x, int y, Runnable onPressAction) {
        super(x, y, 16, 16, Component.empty());
        this.onPressAction = onPressAction;
    }

    @Override
    public void onPress() {
        if (onPressAction != null) onPressAction.run();
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        GuiComponent.renderTexture(guiGraphics, GuiTexture.ADD_FILTER_BUTTON, getX(), getY(), 16, 16);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        defaultButtonNarrationText(output);
    }
}