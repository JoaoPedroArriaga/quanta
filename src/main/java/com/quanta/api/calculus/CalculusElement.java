package com.quanta.api.calculus;

public enum CalculusElement {
    NEUTRAL("neutral", 1.0f, 1.0f, 0f),
    ORDER("order", 0.9f, 1.25f, -0.10f),
    CHAOS("chaos", 1.15f, 0.75f, +0.15f),
    SINGULARITY("singularity", 2.0f, 2.0f, 0f);
    
    private final String name;
    private final float powerMultiplier;
    private final float costMultiplier;
    private final float failureModifier;
    
    CalculusElement(String name, float powerMultiplier, float costMultiplier, float failureModifier) {
        this.name = name;
        this.powerMultiplier = powerMultiplier;
        this.costMultiplier = costMultiplier;
        this.failureModifier = failureModifier;
    }
    
    public String getName() { return name; }
    public float getPowerMultiplier() { return powerMultiplier; }
    public float getCostMultiplier() { return costMultiplier; }
    public float getFailureModifier() { return failureModifier; }
}
