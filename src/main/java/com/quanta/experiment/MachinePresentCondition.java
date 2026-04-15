package com.quanta.experiment;

public class MachinePresentCondition implements Condition {
    private final String machineId;
    
    public MachinePresentCondition(String machineId) {
        this.machineId = machineId;
    }
    
    @Override
    public boolean check(ExperimentContext context) {
        return context.hasMachine(machineId);
    }
}