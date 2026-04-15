package com.quanta.experiment;

public class EnergyLevelCondition implements Condition {
    private final int minQuanta;
    
    public EnergyLevelCondition(int minQuanta) {
        this.minQuanta = minQuanta;
    }
    
    @Override
    public boolean check(ExperimentContext context) {
        return context.getAvailableQuanta() >= minQuanta;
    }
}