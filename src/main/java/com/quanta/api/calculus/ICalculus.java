package com.quanta.api.calculus;

/**
 * A Calculus is a mathematical/quantum formula written on the Black Board.
 * Combination: Form + Element + Effect = potential Equation.
 */
public interface ICalculus {
    
    CalculusForm getForm();
    CalculusElement getElement();
    CalculusEffect getEffect();

    int getComplexity();
    String getFormulaString();
    boolean isBaseCalculus();

    KnowledgeType[] getRequiredKnowledgeTypes();
}
