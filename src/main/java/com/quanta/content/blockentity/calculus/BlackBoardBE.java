package com.quanta.content.blockentity.calculus;

import com.quanta.api.calculus.*;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.base.QuantaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class BlackBoardBE extends QuantaBlockEntity implements IBlackBoard {

    private CalculusForm form;
    private CalculusElement element;
    private CalculusEffect effect;

    public BlackBoardBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLACK_BOARD.get(), pos, state);
    }

    @Override
    public void setForm(CalculusForm form) { this.form = form; setChangedAndSync(); }

    @Override
    public void setElement(CalculusElement element) { this.element = element; setChangedAndSync(); }

    @Override
    public void setEffect(CalculusEffect effect) { this.effect = effect; setChangedAndSync(); }

    @Override
    public ICalculus getCurrentCalculus() {
        if (form == null || element == null || effect == null) return null;
        return new com.quanta.core.calculus.Calculus(form, element, effect, 0, true);
    }

    @Override
    public IEquation solve() {
        ICalculus calculus = getCurrentCalculus();
        if (calculus == null) return null;
        return com.quanta.core.calculus.CalculusSolver.solve(calculus).orElse(null);
    }

    @Override
    public void clear() {
        form = null;
        element = null;
        effect = null;
        setChangedAndSync();
    }

    @Override
    public boolean hasCompleteFormula() {
        return form != null && element != null && effect != null;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) { return null; }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}