package com.quanta.core.energy;

import com.quanta.api.cable.IQuantaStorage;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class EnergyDistribution {
    private final ObjectList<IQuantaStorage> nodes = new ObjectArrayList<>();
    private int totalEnergy = 0;
    
    public void addNode(IQuantaStorage node) {
        nodes.add(node);
    }
    
    public void removeNode(IQuantaStorage node) {
        nodes.remove(node);
    }
    
    public void distributeEnergy() {
        totalEnergy = 0;
        for (int i = 0; i < nodes.size(); i++) {
            totalEnergy += nodes.get(i).getQuantaStored();
        }
        
        if (totalEnergy <= 0) return;
        
        // Distribuição balanceada entre todos os nós
        int targetPerNode = totalEnergy / nodes.size();
        
        for (int i = 0; i < nodes.size(); i++) {
            IQuantaStorage node = nodes.get(i);
            int current = node.getQuantaStored();
            
            if (current < targetPerNode) {
                int needed = targetPerNode - current;
                int received = requestEnergyFromOthers(node, needed);
                if (received > 0) {
                    node.addQuanta(received);
                }
            }
        }
    }
    
    private int requestEnergyFromOthers(IQuantaStorage requester, int needed) {
        int received = 0;
        for (int i = 0; i < nodes.size() && received < needed; i++) {
            IQuantaStorage donor = nodes.get(i);
            if (donor == requester) continue;
            
            int available = donor.getQuantaStored();
            if (available <= 0) continue;
            
            int toTake = Math.min(needed - received, available);
            int taken = donor.extractQuanta(toTake, false);
            received += taken;
        }
        return received;
    }
    
    public int getTotalEnergy() { return totalEnergy; }
    public void clear() { nodes.clear(); }
}