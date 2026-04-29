package com.quanta.client.renderer.state;

import com.quanta.block.cable.CableType;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import java.util.*;

public final class CableRenderState {
    public static final ModelProperty<CableRenderState> PROPERTY = new ModelProperty<>();

    private ObjectList<CableType> presentCables = new ObjectArrayList<>();
    private final int[] connectionMasks = new int[6];
    private CableType baseType = CableType.ENERGY;
    private ObjectList<CableType> particles = new ObjectArrayList<>();
    private boolean isStraightLine = false;
    private int cachedHash = 0;
    
    private static final Object2ObjectOpenHashMap<CableType, ParticleConfig> PARTICLE_CONFIGS = new Object2ObjectOpenHashMap<>();
    
    static {
        PARTICLE_CONFIGS.put(CableType.ENERGY, new ParticleConfig(1.2f, 0.35f, 1.5f, 45, 1.0f));
        PARTICLE_CONFIGS.put(CableType.ITEM,   new ParticleConfig(1.0f, 0.30f, 1.2f, 135, 1.0f));
        PARTICLE_CONFIGS.put(CableType.LIQUID, new ParticleConfig(0.8f, 0.32f, 1.3f, 225, -1.0f));
        PARTICLE_CONFIGS.put(CableType.GAS,    new ParticleConfig(1.5f, 0.38f, 1.8f, 315, -1.0f));
    }
    
    public record ParticleConfig(float speed, float radius, float verticalFreq, int startAngle, float direction) {}
    
    private static final CableType[] PRIORITY = {
        CableType.ENERGY, CableType.ITEM, CableType.LIQUID, CableType.GAS
    };

    public void update(List<CableType> cables, int[] connections) {
        if (cables == null || connections == null) return;
        
        boolean cablesChanged = !presentCables.equals(cables) || 
                                !Arrays.equals(this.connectionMasks, connections);
        
        if (cablesChanged) {
            presentCables.clear();
            presentCables.addAll(cables);
            System.arraycopy(connections, 0, this.connectionMasks, 0, 6);
            
            if (!cables.isEmpty()) {
                // Primeiro cabo é o primário (define textura de core e conectores)
                this.baseType = cables.get(0);
                this.particles.clear();
                if (cables.size() > 1) {
                    this.particles.addAll(cables.subList(1, cables.size()));
                }
            } else {
                this.particles.clear();
            }
            
            IntList connectedDirs = new IntArrayList(2);
            for (Direction dir : Direction.values()) {
                if (connectionMasks[dir.ordinal()] != 0) {
                    connectedDirs.add(dir.ordinal());
                }
            }
            isStraightLine = connectedDirs.size() == 2 && 
                Direction.from3DDataValue(connectedDirs.getInt(0)).getOpposite() == 
                Direction.from3DDataValue(connectedDirs.getInt(1));
        }
        
        cachedHash = presentCables.hashCode() + Arrays.hashCode(connectionMasks);
    }
    
    public float getOrbitAngle() {
        return com.quanta.client.ClientAnimationHandler.getOrbitAngle();
    }
    
    public float getFloatOffset() {
        return com.quanta.client.ClientAnimationHandler.getFloatOffset();
    }
    
    public ParticleConfig getParticleConfig(CableType type) {
        return PARTICLE_CONFIGS.getOrDefault(type, PARTICLE_CONFIGS.get(CableType.ENERGY));
    }
    
    public int getCacheHash() { return cachedHash; }
    public List<CableType> getPresentCables() { return presentCables; }
    public CableType getBaseType() { return baseType; }
    public List<CableType> getParticles() { return particles; }
    public boolean hasParticles() { return !particles.isEmpty(); }
    public boolean shouldShowCore() { return !isStraightLine; }
    public boolean shouldShowParticles() { return !particles.isEmpty(); }
    
    // SEMPRE retorna o tipo primário para os conectores
    public CableType getPrimaryConnectedTypeForDirection(Direction dir) {
        return baseType;
    }
    
    public int getConnectionMask(Direction dir) {
        return connectionMasks[dir.ordinal()];
    }
    
    public boolean hasConnection(Direction dir) {
        return connectionMasks[dir.ordinal()] != 0;
    }
}