package com.quanta.block.cable;

import net.minecraft.core.BlockPos;
import java.util.*;

public class CableNetwork {
    
    private final UUID networkId;
    private final Set<BlockPos> cables = new HashSet<>();
    private final Map<CableType, Integer> totalEnergy = new EnumMap<>(CableType.class);
    private final Map<CableType, Integer> maxTransfer = new EnumMap<>(CableType.class);
    private boolean isActive = true;
    private long lastTickTime = 0;
    
    public CableNetwork(UUID id) {
        this.networkId = id;
        for (CableType type : CableType.values()) {
            totalEnergy.put(type, 0);
            maxTransfer.put(type, type.maxTransfer);
        }
    }
    
    public UUID getId() { return networkId; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public int getCableCount() { return cables.size(); }
    public Set<BlockPos> getCables() { return Collections.unmodifiableSet(cables); }
    
    public void addCable(BlockPos pos) { cables.add(pos); }
    public void removeCable(BlockPos pos) { cables.remove(pos); }
    
    public int getTotalEnergy(CableType type) {
        return totalEnergy.getOrDefault(type, 0);
    }
    
    public void addEnergy(CableType type, int amount) {
        totalEnergy.merge(type, amount, Integer::sum);
    }
    
    public int getMaxTransfer(CableType type) {
        return maxTransfer.getOrDefault(type, 0);
    }
    
    public void merge(CableNetwork other) {
        this.cables.addAll(other.cables);
        for (CableType type : CableType.values()) {
            this.totalEnergy.merge(type, other.totalEnergy.getOrDefault(type, 0), Integer::sum);
            this.maxTransfer.put(type, Math.min(this.maxTransfer.get(type), other.maxTransfer.get(type)));
        }
    }
    
    public void tick() {
        long now = System.currentTimeMillis();
        if (now - lastTickTime < 50) return;
        lastTickTime = now;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CableNetwork other)) return false;
        return networkId.equals(other.networkId);
    }
    
    @Override
    public int hashCode() {
        return networkId.hashCode();
    }
}