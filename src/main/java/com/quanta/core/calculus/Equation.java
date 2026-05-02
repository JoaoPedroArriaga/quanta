package com.quanta.core.calculus;

import com.quanta.api.calculus.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Equation implements IEquation {

    private final String id;
    private final String name;
    private final int knowledgeCost;
    private final int quantaCost;
    private final int cooldownTicks;
    private final ICalculus formula;

    public Equation(String id, String name, int knowledgeCost, int quantaCost,
                    int cooldownTicks, ICalculus formula) {
        this.id = id;
        this.name = name;
        this.knowledgeCost = knowledgeCost;
        this.quantaCost = quantaCost;
        this.cooldownTicks = cooldownTicks;
        this.formula = formula;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public int getKnowledgeCost() { return knowledgeCost; }

    @Override
    public int getQuantaCost() { return quantaCost; }

    @Override
    public int getCooldownTicks() { return cooldownTicks; }

    @Override
    public ICalculus getFormula() { return formula; }

    @Override
    public boolean canCast(Player player, Level level) {
        // Check if player has enough Quanta in their Codex or network
        return true;
    }

    @Override
    public CastResult cast(Player player, Level level) {
        return new CastResult(true, quantaCost, "equation." + id + ".cast");
    }
}