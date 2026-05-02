package com.quanta.core.calculus;

import com.quanta.api.calculus.*;

import java.util.Objects;

public class Calculus implements ICalculus {

    private final CalculusForm form;
    private final CalculusElement element;
    private final CalculusEffect effect;
    private final int complexity;
    private final boolean base;
    private final KnowledgeType[] requiredTypes;

    public Calculus(CalculusForm form, CalculusElement element, CalculusEffect effect,
                    int complexity, boolean base, KnowledgeType... requiredTypes) {
        this.form = form;
        this.element = element;
        this.effect = effect;
        this.complexity = complexity;
        this.base = base;
        this.requiredTypes = requiredTypes;
    }

    @Override
    public CalculusForm getForm() { return form; }

    @Override
    public CalculusElement getElement() { return element; }

    @Override
    public CalculusEffect getEffect() { return effect; }

    @Override
    public int getComplexity() { return complexity; }

    @Override
    public String getFormulaString() {
        return form.getSymbol() + "(" + element.getName() + ") → " + effect.getName();
    }

    @Override
    public boolean isBaseCalculus() { return base; }

    @Override
    public KnowledgeType[] getRequiredKnowledgeTypes() { return requiredTypes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calculus other)) return false;
        return form == other.form && element == other.element && effect == other.effect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(form, element, effect);
    }
}