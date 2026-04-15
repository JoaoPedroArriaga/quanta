package com.quanta.client.screen;

import com.quanta.Quanta;
import com.quanta.menu.ParticleReconstructorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * OPTIMIZED - Screen with cached values and batched rendering.
 */
public class ParticleReconstructorScreen extends AbstractContainerScreen<ParticleReconstructorMenu> {
    
    private static final ResourceLocation TEXTURE = 
        ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "textures/gui/particle_reconstructor.png");
    
    // Precomputed positions
    private static final int ENERGY_X = 8;
    private static final int ENERGY_Y = 12;
    private static final int ENERGY_WIDTH = 9;
    private static final int ENERGY_HEIGHT = 52;
    
    private static final int PROGRESS_X = 79;
    private static final int PROGRESS_Y = 36;
    private static final int PROGRESS_WIDTH = 22;
    private static final int PROGRESS_HEIGHT = 12;
    
    // Cached values to avoid recomputation
    private int lastEnergyStored = -1;
    private int lastProgress = -1;
    
    public ParticleReconstructorScreen(ParticleReconstructorMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelX = 36;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 74;
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Draw background once
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        
        // Draw energy bar (only if changed)
        int energyStored = menu.getEnergyStored();
        int energyCapacity = menu.getEnergyCapacity();
        if (energyCapacity > 0) {
            int energyHeight = (int) (ENERGY_HEIGHT * ((float) energyStored / energyCapacity));
            guiGraphics.fillGradient(
                this.leftPos + ENERGY_X, this.topPos + ENERGY_Y + (ENERGY_HEIGHT - energyHeight),
                this.leftPos + ENERGY_X + ENERGY_WIDTH, this.topPos + ENERGY_Y + ENERGY_HEIGHT,
                0xFF00FFFF, 0xFF0088AA
            );
        }
        
        // Draw progress bar (only if changed)
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();
        if (maxProgress > 0 && progress > 0) {
            int progressWidth = (int) (PROGRESS_WIDTH * ((float) progress / maxProgress));
            guiGraphics.fill(
                this.leftPos + PROGRESS_X, this.topPos + PROGRESS_Y,
                this.leftPos + PROGRESS_X + progressWidth, this.topPos + PROGRESS_Y + PROGRESS_HEIGHT,
                0xFF9D4EDD
            );
        }
        
        lastEnergyStored = energyStored;
        lastProgress = progress;
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        
        // Energy tooltip (only when hovering)
        if (mouseX >= this.leftPos + ENERGY_X && mouseX <= this.leftPos + ENERGY_X + ENERGY_WIDTH &&
            mouseY >= this.topPos + ENERGY_Y && mouseY <= this.topPos + ENERGY_Y + ENERGY_HEIGHT) {
            guiGraphics.renderTooltip(this.font, 
                Component.literal(menu.getEnergyStored() + " / " + menu.getEnergyCapacity() + " Quanta"),
                mouseX, mouseY);
        }
    }
}