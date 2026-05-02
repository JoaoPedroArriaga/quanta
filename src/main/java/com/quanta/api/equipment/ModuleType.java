package com.quanta.api.equipment;

public enum ModuleType {
    MINING("mining"),
    COMBAT("combat"),
    UTILITY("utility"),
    DEFENSE("defense"),
    SPECIAL("special");

    private final String name;

    ModuleType(String name) { this.name = name; }
    public String getName() { return name; }
}