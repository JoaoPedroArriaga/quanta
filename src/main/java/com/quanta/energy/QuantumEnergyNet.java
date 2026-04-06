package com.quanta.energy;

import java.util.ArrayList;
import java.util.List;

public class QuantumEnergyNet {
    private final List<QuantumEnergyStorage> nodes = new ArrayList<>();
    private int totalEnergy = 0;
    
    public void addNode(QuantumEnergyStorage node) {
        nodes.add(node);
    }
    
    public void distributeEnergy() {
        totalEnergy = nodes.stream().mapToInt(QuantumEnergyStorage::getQuantumStored).sum();
        // Lógica de distribuição
    }
}
