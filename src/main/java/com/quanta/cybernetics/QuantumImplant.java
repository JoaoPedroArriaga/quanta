package com.quanta.cybernetics;

import com.quanta.cybernetics.ImplantSlot;

public class QuantumImplant {
    private final String id;
    private final  ImplantSlot slot ;
    
    public QuantumImplant(String id, ImplantSlot  slot) {
        this.id = id;
        this.slot = slot;
    }
    
    public String getId() { return id; }
    public ImplantSlot  getSlot() { return slot; }
}
