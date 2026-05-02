package com.quanta.api.calculus;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * An Equation is a complete spell, the result of combining
 * a Calculus (Form + Element + Effect) with sufficient Knowledge.
 */
public interface IEquation {
    
    String getId();
    String getName();

    int getKnowledgeCost();
    int getQuantaCost();
    int getCooldownTicks();

    ICalculus getFormula();
    boolean canCast(Player player, Level level);
    CastResult cast(Player player, Level level);
    record CastResult(boolean success, int quantaConsumed, String feedbackKey) {}
}
