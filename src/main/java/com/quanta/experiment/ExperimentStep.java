package com.quanta.experiment;

public class ExperimentStep {
    private final String action;
    
    public ExperimentStep(String action) {
        this.action = action;
    }
    
    public boolean execute(ExperimentContext context) {
        // Implementar lógica
        return true;
    }
}