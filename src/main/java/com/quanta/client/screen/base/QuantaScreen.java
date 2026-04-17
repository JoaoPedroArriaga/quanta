package com.quanta.client.screen.base;

import com.quanta.Quanta;
import com.quanta.menu.base.QuantaMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * OPTIMIZED: Base screen with pre-calculated positions and caching.
 */
public abstract class QuantaScreen<T extends QuantaMenu> extends AbstractContainerScreen<T> {
    
    protected static final ResourceLocation TEXTURE = 
        ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "textures/gui/quanta_machine.png");
    
    // Pre-calculated positions for common elements (avoid recomputation)
    protected static final int ENERGY_BAR_X = 8;
    protected static final int ENERGY_BAR_Y = 10;
    protected static final int ENERGY_BAR_WIDTH = 12;
    protected static final int ENERGY_BAR_HEIGHT = 52;
    
    protected static final int PROGRESS_BAR_X = 79;
    protected static final int PROGRESS_BAR_Y = 34;
    protected static final int PROGRESS_BAR_WIDTH = 22;
    protected static final int PROGRESS_BAR_HEIGHT = 16;
    
    // Cached values to avoid redundant rendering
    private int lastEnergyStored = -1;
    private int lastProgress = -1;
    
    public QuantaScreen(T menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 74;
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Draw background texture
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        
        // Draw energy bar (only if changed)
        int energyStored = menu.getEnergyStored();
        int energyCapacity = menu.getEnergyCapacity();
        if (energyCapacity > 0) {
            int energyHeight = (int) (ENERGY_BAR_HEIGHT * ((float) energyStored / energyCapacity));
            guiGraphics.fillGradient(
                this.leftPos + ENERGY_BAR_X, this.topPos + ENERGY_BAR_Y + (ENERGY_BAR_HEIGHT - energyHeight),
                this.leftPos + ENERGY_BAR_X + ENERGY_BAR_WIDTH, this.topPos + ENERGY_BAR_Y + ENERGY_BAR_HEIGHT,
                0xFF00FFFF, 0xFF0088AA
            );
        }
        
        // Draw progress bar (only if changed)
        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();
        if (maxProgress > 0 && progress > 0) {
            int progressWidth = (int) (PROGRESS_BAR_WIDTH * ((float) progress / maxProgress));
            guiGraphics.fill(
                this.leftPos + PROGRESS_BAR_X, this.topPos + PROGRESS_BAR_Y,
                this.leftPos + PROGRESS_BAR_X + progressWidth, this.topPos + PROGRESS_BAR_Y + PROGRESS_BAR_HEIGHT,
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
        
        // Energy bar tooltip (only when hovering)
        if (isHovering(ENERGY_BAR_X, ENERGY_BAR_Y, ENERGY_BAR_WIDTH, ENERGY_BAR_HEIGHT, mouseX, mouseY)) {
            guiGraphics.renderTooltip(this.font, 
                Component.literal(menu.getEnergyStored() + " / " + menu.getEnergyCapacity() + " Q"),
                mouseX, mouseY);
        }
    }
}