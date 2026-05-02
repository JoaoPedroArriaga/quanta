package com.quanta.core.energy;

import com.quanta.QuantaConfig;
import com.quanta.api.cable.IQuantaStorage;
import com.quanta.core.tier.QuantumTier;
import net.minecraft.util.RandomSource;
import java.util.ArrayList;
import java.util.List;

public class QuantumEnergyStorage implements IQuantaStorage {

    private byte flags = 0;
    private static final byte FLAG_SUPERPOSITION = 0b00000001;
    private static final byte FLAG_DIRTY = 0b00000010;

    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private QuantumTier tier;
    private int totalExtracted;
    private int totalReceived;
    private int lastTransfer = 0;

    private IQuantaStorage[] connections;
    private int connectionCount;
    private static final int INITIAL_CONNECTION_CAPACITY = 4;

    private final int fePerQuanta;
    private final RandomSource random;

    public QuantumEnergyStorage(QuantumTier tier) {
        this.tier = tier;
        this.capacity = tier.energyTransfer * 20;
        this.maxReceive = tier.energyTransfer;
        this.maxExtract = tier.energyTransfer;
        this.energy = 0;
        this.totalExtracted = 0;
        this.totalReceived = 0;
        this.fePerQuanta = QuantaConfig.fePerQuanta();
        this.random = RandomSource.create();
        this.connections = new IQuantaStorage[INITIAL_CONNECTION_CAPACITY];
        this.connectionCount = 0;
    }

    public QuantumEnergyStorage(QuantumTier tier, int customCapacity) {
        this.tier = tier;
        this.capacity = customCapacity;
        this.maxReceive = tier.energyTransfer;
        this.maxExtract = tier.energyTransfer;
        this.energy = 0;
        this.totalExtracted = 0;
        this.totalReceived = 0;
        this.fePerQuanta = QuantaConfig.fePerQuanta();
        this.random = RandomSource.create();
        this.connections = new IQuantaStorage[INITIAL_CONNECTION_CAPACITY];
        this.connectionCount = 0;
    }

    private boolean hasFlag(byte flag) { return (flags & flag) != 0; }
    private void setFlag(byte flag, boolean value) {
        if (value) flags |= flag;
        else flags &= ~flag;
    }

    // ===== IEnergyStorage (FE compatibility) =====
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int quantaToReceive = maxReceive / fePerQuanta;
        if (quantaToReceive <= 0) return 0;
        int received = addQuanta(quantaToReceive);
        return received * fePerQuanta;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int quantaToExtract = maxExtract / fePerQuanta;
        if (quantaToExtract <= 0) return 0;
        int extracted = extractQuanta(quantaToExtract, false, simulate);
        return extracted * fePerQuanta;
    }

    @Override
    public int getEnergyStored() { return energy * fePerQuanta; }

    @Override
    public int getMaxEnergyStored() { return capacity * fePerQuanta; }

    @Override
    public boolean canExtract() { return true; }

    @Override
    public boolean canReceive() { return true; }

    // ===== IQuantaStorage =====
    @Override
    public int getQuantaStored() { return energy; }

    @Override
    public int getQuantaCapacity() { return capacity; }

    @Override
    public int addQuanta(int amount) {
        int received = Math.min(amount, capacity - energy);
        received = Math.min(received, maxReceive);
        if (received > 0) {
            energy += received;
            totalReceived += received;
            setFlag(FLAG_DIRTY, true);
        }
        return received;
    }

    @Override
    public int extractQuanta(int amount, boolean allowTunnel) {
        return extractQuanta(amount, allowTunnel, false);
    }

    @Override
    public int extractQuanta(int amount, boolean allowTunnel, boolean simulate) {
        int extracted = Math.min(amount, energy);
        extracted = Math.min(extracted, maxExtract);
        if (allowTunnel && hasFlag(FLAG_SUPERPOSITION) && !simulate
            && random.nextFloat() < 0.1f + getSuperpositionChanceBonus()) {
            int doubled = extracted << 1;
            if (doubled > energy) doubled = energy;
            extracted = doubled;
        }
        if (!simulate && extracted > 0) {
            energy -= extracted;
            totalExtracted += extracted;
            lastTransfer = extracted;
            setFlag(FLAG_DIRTY, true);
        }
        return extracted;
    }

    @Override
    public boolean isInSuperposition() { return hasFlag(FLAG_SUPERPOSITION); }

    public void setSuperposition(boolean enabled) {
        setFlag(FLAG_SUPERPOSITION, enabled);
        setFlag(FLAG_DIRTY, true);
    }

    @Override
    public float getSuperpositionChanceBonus() {
        if (!hasFlag(FLAG_SUPERPOSITION)) return 0;
        return switch (tier) {
            case DECOHERENT -> 0.05f;
            case ENTANGLED -> 0.10f;
            case SUPERPOSED -> 0.15f;
            case SINGULAR -> 0.20f;
        };
    }

    @Override
    public int getTier() { return tier.id; }

    public void addConnection(IQuantaStorage other) {
        if (other == this) return;
        for (int i = 0; i < connectionCount; i++) {
            if (connections[i] == other) return;
        }
        if (connectionCount >= connections.length) {
            IQuantaStorage[] newConn = new IQuantaStorage[connections.length << 1];
            System.arraycopy(connections, 0, newConn, 0, connections.length);
            connections = newConn;
        }
        connections[connectionCount++] = other;
    }

    public void removeConnection(IQuantaStorage other) {
        for (int i = 0; i < connectionCount; i++) {
            if (connections[i] == other) {
                int move = connectionCount - i - 1;
                if (move > 0) System.arraycopy(connections, i + 1, connections, i, move);
                connections[--connectionCount] = null;
                break;
            }
        }
    }

    public List<IQuantaStorage> getConnections() {
        List<IQuantaStorage> list = new ArrayList<>(connectionCount);
        for (int i = 0; i < connectionCount; i++) list.add(connections[i]);
        return list;
    }

    public int getTotalExtracted() { return totalExtracted; }
    public int getTotalReceived() { return totalReceived; }
    public void resetStats() { totalExtracted = 0; totalReceived = 0; setFlag(FLAG_DIRTY, true); }
    public void setEnergy(int energy) { this.energy = Math.min(capacity, Math.max(0, energy)); setFlag(FLAG_DIRTY, true); }
    public boolean isDirty() { return hasFlag(FLAG_DIRTY); }
    public void clearDirty() { setFlag(FLAG_DIRTY, false); }
    public void upgradeTier(QuantumTier newTier) { this.tier = newTier; setFlag(FLAG_DIRTY, true); }
    public int getLastTransfer() { return lastTransfer; }
}