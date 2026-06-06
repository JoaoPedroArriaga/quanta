package com.quanta.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public final class GuiComponent {

    public static void renderTexture(GuiGraphics guiGraphics, ResourceLocation texture,
                                      int x, int y, int width, int height) {
        renderTexture(guiGraphics, texture, x, y, 0, 0, width, height, width, height);
    }

    public static void renderTexture(GuiGraphics guiGraphics, ResourceLocation texture,
                                      int x, int y, int u, int v,
                                      int width, int height, int textureWidth, int textureHeight) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableBlend();

        Matrix4f matrix = guiGraphics.pose().last().pose();
        BufferBuilder buffer = Tesselator.getInstance().begin(
            VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        float u0 = (float) u / textureWidth;
        float v0 = (float) v / textureHeight;
        float u1 = (float) (u + width) / textureWidth;
        float v1 = (float) (v + height) / textureHeight;

        buffer.addVertex(matrix, x, y + height, 0).setUv(u0, v1);
        buffer.addVertex(matrix, x + width, y + height, 0).setUv(u1, v1);
        buffer.addVertex(matrix, x + width, y, 0).setUv(u1, v0);
        buffer.addVertex(matrix, x, y, 0).setUv(u0, v0);

        BufferUploader.drawWithShader(buffer.buildOrThrow());
        RenderSystem.disableBlend();
    }

    public static void renderEnergyBar(GuiGraphics guiGraphics, int x, int y,
                                        int stored, int capacity,
                                        int width, int height) {
        renderTexture(guiGraphics, GuiTexture.ENERGY_BAR, x, y, width, height);
        if (capacity > 0 && stored > 0) {
            int fillHeight = (height * stored) / capacity;
            if (fillHeight > 0) {
                guiGraphics.fillGradient(x, y + height - fillHeight,
                    x + width, y + height, 0xFF00FFFF, 0xFF0088AA);
            }
        }
    }

    public static void renderProgressBarFill(GuiGraphics guiGraphics, int x, int y,
                                              int progress, int maxProgress,
                                              int width, int height) {
        if (maxProgress > 0 && progress > 0) {
            int progressSize = (width * progress) / maxProgress;
            if (progressSize > 0) {
                guiGraphics.fillGradient(x, y, x + progressSize, y + height,
                    0xFF9D4EDD, 0xFF6B2FA0);
            }
        }
    }

    public static void renderProgressBarFillVertical(GuiGraphics guiGraphics, int x, int y,
                                                      int progress, int maxProgress,
                                                      int width, int height) {
        if (maxProgress > 0 && progress > 0) {
            int progressSize = (height * progress) / maxProgress;
            if (progressSize > 0) {
                guiGraphics.fillGradient(x, y + height - progressSize,
                    x + width, y + height, 0xFF9D4EDD, 0xFF6B2FA0);
            }
        }
    }

    public static void renderSlot(GuiGraphics guiGraphics, ResourceLocation slotTexture,
                                   int x, int y, int size) {
        renderTexture(guiGraphics, slotTexture, x, y, size, size);
    }

    public static boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private GuiComponent() {}
}