package com.quanta.client.renderer.state;

import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.Priority;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.Direction;
import java.util.*;

public class ParticleState {

    private final List<OrbitalParticleData> orbitalParticles = new ArrayList<>();
    private final Map<Direction, TypeIndicatorParticleData> typeIndicatorParticles = new EnumMap<>(Direction.class);
    private final Map<Direction, PriorityParticleData> priorityParticles = new EnumMap<>(Direction.class);

    public record OrbitalParticleData(TransferType type, float speed, float radius,
                                       float heightAmp, float verticalFreq,
                                       int startAngle, float direction) {}

    public record TypeIndicatorParticleData(Direction face, TransferType type, ConnectionConfig.Mode mode,
                                             boolean whitelist, float x, float y, float z) {}

    public record PriorityParticleData(Direction face, Priority priority, int count, float sizeMultiplier,
                                        float x, float y, float z) {}

    public void addOrbitalParticle(TransferType type, float speed, float radius, float heightAmp,
                                    float verticalFreq, int startAngle, float direction) {
        orbitalParticles.add(new OrbitalParticleData(type, speed, radius, heightAmp, verticalFreq, startAngle, direction));
    }

    public void addTypeIndicatorParticle(Direction face, TransferType type, ConnectionConfig.Mode mode,
                                          boolean whitelist, float x, float y, float z) {
        typeIndicatorParticles.put(face, new TypeIndicatorParticleData(face, type, mode, whitelist, x, y, z));
    }

    public void addPriorityParticle(Direction face, Priority priority, float x, float y, float z) {
        int count = switch (priority) {
            case NORMAL -> 0;
            case LOW -> 1; case LOWEST -> 2;
            case HIGH -> 1; case HIGHEST -> 2; case CRITICAL -> 3;
        };
        float sizeMultiplier = switch (priority) {
            case NORMAL -> 0;
            case LOW, LOWEST -> 0.8f;
            case HIGH -> 1.0f; case HIGHEST -> 1.3f; case CRITICAL -> 1.6f;
        };
        if (count > 0) priorityParticles.put(face, new PriorityParticleData(face, priority, count, sizeMultiplier, x, y, z));
    }

    public void clear() { orbitalParticles.clear(); typeIndicatorParticles.clear(); priorityParticles.clear(); }

    public List<OrbitalParticleData> getOrbitalParticles() { return orbitalParticles; }
    public Map<Direction, TypeIndicatorParticleData> getTypeIndicatorParticles() { return typeIndicatorParticles; }
    public Map<Direction, PriorityParticleData> getPriorityParticles() { return priorityParticles; }
    public boolean hasOrbitalParticles() { return !orbitalParticles.isEmpty(); }
    public boolean hasTypeIndicatorParticles() { return !typeIndicatorParticles.isEmpty(); }
    public boolean hasPriorityParticles() { return !priorityParticles.isEmpty(); }
}