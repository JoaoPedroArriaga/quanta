package com.quanta.cybernetics;

public class CyberPart {
    private final String id;
    private final CyberSlot slot;
    
    public CyberPart(String id, CyberSlot slot) {
        this.id = id;
        this.slot = slot;
    }
    
    public String getId() { return id; }
    public CyberSlot getSlot() { return slot; }
}
