package com.quanta.api.implant;

public enum ImplantSlot {
    EYES("eyes"),
    ARMS("arms"),
    LEGS("legs"),
    CHEST("chest"),
    BRAIN("brain");

    private final String name;

    ImplantSlot(String name) { this.name = name; }
    public String getName() { return name; }
}
