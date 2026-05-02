package com.quanta.api.calculus;

/**
 * Interface for the Black Board block entity.
 * Where players write Calculus formulas.
 */
public interface IBlackBoard {
    
    void setForm(CalculusForm form);
    void setElement(CalculusElement element);
    void setEffect(CalculusEffect effect);

    ICalculus getCurrentCalculus();
    IEquation solve();
    
    void clear();
    boolean hasCompleteFormula();
}
