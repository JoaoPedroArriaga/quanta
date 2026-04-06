package com.quanta.cable;

public enum CableType {
    ENERGY,
    FLUID,
    ITEM,
    GAS;
    
    public boolean canShareSpaceWith(CableType other) {
        return this != other; // Cabos diferentes podem ocupar o mesmo bloco
    }
}
