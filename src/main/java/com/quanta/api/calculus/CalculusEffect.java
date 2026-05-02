package com.quanta.api.calculus;

public enum CalculusEffect {
    DAMAGE("damage"),
    HEAL("heal"),
    TELEPORT("teleport"),
    ACCELERATION("acceleration"),
    INFUSION("infusion"),
    REVELATION("revelation"),
    STABILIZATION("stabilization"),
    LINK("link");
    
    private final String name;
    
    CalculusEffect(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
