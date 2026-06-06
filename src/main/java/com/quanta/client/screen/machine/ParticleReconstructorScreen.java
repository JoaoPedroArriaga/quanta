package com.quanta.client.screen.machine;

import com.quanta.client.screen.component.*;
import com.quanta.content.menu.machine.ParticleReconstructorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ParticleReconstructorScreen extends Screen<ParticleReconstructorMenu> {

    private static final int SLOT = 18;
    private static final int GAP = 2;
    private static final int COL_GAP = 4;
    private static final int PROG_H_W = 34;
    private static final int PROG_H_H = 14;
    private static final int PROG_V_W = 14;
    private static final int PROG_V_H = 26;
    private static final int ENERGY_W = 12;
    private static final int ENERGY_H = 52;
    
    private static final int ENERGY_X = 7;
    private static final int ENERGY_Y = 12;
    private static final int ENERGY_BAR_END = ENERGY_X + ENERGY_W;
    private static final int MIN_DISTANCE = 12;
    private static final int MIN_START_X = ENERGY_BAR_END + MIN_DISTANCE;
    
    private static final int MODE_X = -22;
    private static final int MODE_Y = 6;
    private static final int INPUT_Y = 20;
    private static final int OUTPUT_Y_MULTI = INPUT_Y + SLOT + GAP + PROG_V_H + GAP;
    private static final int ADDON_X = 178;
    private static final int ADDON_Y = 9;

    private ProgressBar progressBarH;
    private ProgressBar[] progressBarsV;

    public ParticleReconstructorScreen(ParticleReconstructorMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.energyBar = new QuantaEnergyBar(ENERGY_X, ENERGY_Y, ENERGY_W, ENERGY_H);
        this.modeButton = new QuantaModeButton(MODE_X, MODE_Y, 16);
    }

    @Override
    protected void renderMachineSlots(GuiGraphics guiGraphics, int leftPos, int topPos) {
        int pairCount = menu.getSlotPairs();
        int addonCount = menu.getAddonSlots();

        if (pairCount == 1) {
            int totalW = SLOT + GAP + PROG_H_W + GAP + SLOT;
            int startX = (176 - totalW) / 2;
            int inputX = startX;
            int outputX = startX + SLOT + GAP + PROG_H_W + GAP;
            
            int centerY = INPUT_Y + 12;

            GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_INPUT, leftPos + inputX - 1, topPos + centerY - 1, SLOT);
            GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_OUTPUT, leftPos + outputX - 1, topPos + centerY - 1, SLOT);

            this.progressBarH = new ProgressBar(
                startX + SLOT + GAP - 1,
                centerY + GAP - 1,
                PROG_H_W,
                SLOT - GAP * 2,
                false
            );
            this.progressBarsV = null;
        } else {
            this.progressBarH = null;
            this.progressBarsV = new ProgressBar[pairCount];

            int totalW = pairCount * SLOT + (pairCount - 1) * COL_GAP;
            int startX = (176 - totalW) / 2;
            
            // Garante distância mínima da energy bar
            if (startX < MIN_START_X) {
                startX = MIN_START_X;
            }

            for (int i = 0; i < pairCount; i++) {
                int colX = startX + i * (SLOT + COL_GAP);

                GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_INPUT, leftPos + colX - 1, topPos + INPUT_Y - 1, SLOT);
                GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_OUTPUT, leftPos + colX - 1, topPos + OUTPUT_Y_MULTI - 1, SLOT);

                int progX = colX + (SLOT - PROG_V_W) / 2 - 1;
                int progY = INPUT_Y + SLOT + GAP - 1;
                int progH = OUTPUT_Y_MULTI - (INPUT_Y + SLOT + GAP) - GAP;
                
                this.progressBarsV[i] = new ProgressBar(progX, progY, PROG_V_W, progH, true);
            }
        }

        for (int i = 0; i < addonCount; i++) {
            GuiComponent.renderSlot(guiGraphics, GuiTexture.SLOT_ADDON,
                leftPos + ADDON_X - 1, topPos + ADDON_Y + i * 20 - 1, SLOT);
        }
    }

    @Override
    protected void renderMachineSpecific(GuiGraphics guiGraphics, int leftPos, int topPos,
                                          float partialTick, int mouseX, int mouseY) {
        energyBar.render(guiGraphics, leftPos, topPos, menu.getEnergyStored(), menu.getEnergyCapacity());

        int prog = menu.getProgress();
        int maxProg = menu.getMaxProgress();

        if (progressBarH != null) {
            progressBarH.renderBg(guiGraphics, leftPos, topPos);
            if (prog > 0 && maxProg > 0) progressBarH.renderFill(guiGraphics, leftPos, topPos, prog, maxProg);
        } else if (progressBarsV != null) {
            for (ProgressBar pb : progressBarsV) {
                pb.renderBg(guiGraphics, leftPos, topPos);
                if (prog > 0 && maxProg > 0) pb.renderFill(guiGraphics, leftPos, topPos, prog, maxProg);
            }
        }

        modeButton.render(guiGraphics, leftPos, topPos, menu.isSuperposed());
    }

    @Override
    protected Component getEnergyTooltip() {
        return Component.literal(menu.getEnergyStored() + " / " + menu.getEnergyCapacity() + " Q");
    }
}