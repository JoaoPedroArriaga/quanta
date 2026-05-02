package com.quanta.core.calculus;

import com.quanta.api.calculus.*;

import java.util.Optional;

public class CalculusSolver {

    public static Optional<IEquation> solve(ICalculus calculus) {
        String equationId = CalculusRegistry.getEquationId((Calculus) calculus);
        if (equationId == null) return Optional.empty();
        return EquationRegistry.get(equationId);
    }

    public static boolean isValid(CalculusForm form, CalculusElement element, CalculusEffect effect) {
        return CalculusRegistry.find(form, element, effect).isPresent();
    }

    public static CalculusCost calculateCost(ICalculus calculus) {
        int knowledgeCost = calculus.getComplexity();
        int quantaCost = calculus.getForm().getComplexity();

        quantaCost = (int) (quantaCost * calculus.getElement().getCostMultiplier());

        return new CalculusCost(knowledgeCost, quantaCost);
    }

    public record CalculusCost(int knowledgeCost, int quantaCost) {}
}