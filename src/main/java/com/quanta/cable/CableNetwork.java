package com.quanta.cable;

import java.util.HashSet;
import java.util.Set;

public class CableNetwork {
    private final Set<MultiCableBlock> cables = new HashSet<>();
    private final CableType type;
    
    public CableNetwork(CableType type) {
        this.type = type;
    }
    
    public void addCable(MultiCableBlock cable) {
        cables.add(cable);
    }
    
    public CableType getType() { return type; }
}
