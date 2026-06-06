package com.quanta.client.renderer.state;

import com.quanta.client.animation.ClientAnimationHandler;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.Priority;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import java.util.*;

public final class CableRenderState {

    public static final ModelProperty<CableRenderState> PROPERTY = new ModelProperty<>();

    private List<TransferType> presentCables = List.of();
    private final int[] connectionMasks = new int[6];
    private TransferType baseType = TransferType.QUANTA;
    private List<TransferType> particles = List.of();
    private boolean isStraightLine = false;
    private int cachedHash = 0;
    private int activeTransferMask = 0;

    private final ParticleState particleState = new ParticleState();
    private final Map<Direction, ConnectionConfig> connectionConfigs = new EnumMap<>(Direction.class);
    private final boolean[] connectionEnabled = new boolean[6];

    public void update(List<TransferType> cables, int[] connections, Map<Direction, ConnectionConfig> configs, int activeTransferMask) {
        this.presentCables = cables;
        System.arraycopy(connections, 0, this.connectionMasks, 0, 6);
        this.connectionConfigs.clear();
        this.connectionConfigs.putAll(configs);
        this.activeTransferMask = activeTransferMask;

        for (Direction dir : Direction.values()) {
            ConnectionConfig config = configs.get(dir);
            connectionEnabled[dir.ordinal()] = config != null && config.getMode().isEnabled();
        }

        if (!cables.isEmpty()) {
            this.baseType = cables.get(0);
            this.particles = cables.size() > 1 ? cables.subList(1, cables.size()) : List.of();
            updateParticleState();
        }

        List<Direction> connectedDirs = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            ConnectionConfig config = connectionConfigs.get(dir);
            if (connectionMasks[dir.ordinal()] != 0 && config != null && config.getMode().isEnabled()) {
                connectedDirs.add(dir);
            }
        }
        this.isStraightLine = connectedDirs.size() == 2 && connectedDirs.get(0).getOpposite() == connectedDirs.get(1);
        cachedHash = Objects.hash(presentCables, Arrays.hashCode(connectionMasks));
    }

    public void updateAngles() {
    }

    private void updateParticleState() {
        particleState.clear();

        // Partículas orbitais (2+ tipos)
        for (int i = 0; i < particles.size(); i++) {
            TransferType type = particles.get(i);
            var config = getParticleConfig(type);
            particleState.addOrbitalParticle(type, config.speed, config.radius, config.heightAmp,
                config.verticalFreq, config.startAngle + (i * 90), config.direction);
        }

        for (Direction dir : Direction.values()) {
            ConnectionConfig config = connectionConfigs.get(dir);
            if (config == null) {
                continue;
            }
            if (connectionMasks[dir.ordinal()] == 0) {
                continue;
            }
            if (config.getMode() == ConnectionConfig.Mode.DISABLED) {
                continue;
            }
            if ((activeTransferMask & (1 << dir.ordinal())) == 0) {
                continue;
            }

            // Ajuste: usar 0.35 para ficar próximo ao centro do cabo (core)
            float offset = 0.35f;
            float x = 0.5f + dir.getStepX() * offset;
            float y = 0.5f + dir.getStepY() * offset;
            float z = 0.5f + dir.getStepZ() * offset;

            for (TransferType type : presentCables) {
                if ((connectionMasks[dir.ordinal()] & type.bitMask) != 0) {
                    particleState.addTypeIndicatorParticle(dir, type, config.getMode(),
                        config.isWhitelist(), x, y, z);
                    break;
                }
            }

            particleState.addPriorityParticle(dir, config.getPriority(), x, y, z);
        }
    }

    public ParticleState getParticleState() {
        return particleState;
    }

    public boolean isConnectionEnabled(Direction dir) {
        return connectionEnabled[dir.ordinal()];
    }

    public ConnectionConfig.Mode getDirectionMode(Direction dir) {
        ConnectionConfig config = connectionConfigs.get(dir);
        return config != null ? config.getMode() : ConnectionConfig.Mode.INSERT;
    }

    public List<TransferType> getPresentCables() {
        return presentCables;
    }

    public TransferType getBaseType() {
        return baseType;
    }

    public List<TransferType> getParticles() {
        return particles;
    }

    public boolean hasParticles() {
        return !particles.isEmpty() || particleState.hasTypeIndicatorParticles() || particleState.hasPriorityParticles();
    }

    public boolean shouldShowCore() {
        return !isStraightLine;
    }

    public boolean hasConnection(Direction dir) {
        return connectionMasks[dir.ordinal()] != 0;
    }

    public int getConnectionMask(Direction dir) {
        return connectionMasks[dir.ordinal()];
    }

    public float getOrbitAngle() {
        return ClientAnimationHandler.getOrbitAngle();
    }

    public float getFloatOffset() {
        return ClientAnimationHandler.getFloatOffset();
    }

    public int getCacheHash() {
        return cachedHash;
    }

    private record ParticleConfig(float speed, float radius, float heightAmp, float verticalFreq, int startAngle, float direction) {
    }

    private ParticleConfig getParticleConfig(TransferType type) {
        return switch (type) {
            case QUANTA -> new ParticleConfig(2.8f, 0.35f, 0.12f, 2.2f, 45, 1.0f);
            case ITEM -> new ParticleConfig(2.2f, 0.30f, 0.10f, 1.8f, 135, 1.0f);
            case FLUID -> new ParticleConfig(2.0f, 0.32f, 0.14f, 2.5f, 225, -1.0f);
            case GAS -> new ParticleConfig(3.2f, 0.38f, 0.16f, 3.0f, 315, -1.0f);
        };
    }
}