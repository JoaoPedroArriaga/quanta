package com.quanta.core.network;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class CableNetwork {

    private final UUID id;
    private final Set<BlockPos> cables = new ObjectOpenHashSet<>();
    private boolean dirty;

    public CableNetwork(UUID id) {
        this.id = id;
    }

    public UUID getId() { return id; }
    public int size() { return cables.size(); }
    public boolean isEmpty() { return cables.isEmpty(); }
    public boolean isDirty() { return dirty; }
    public void clearDirty() { dirty = false; }
    public Set<BlockPos> getCables() { return Collections.unmodifiableSet(cables); }

    public void addCable(BlockPos pos) {
        if (cables.add(pos)) dirty = true;
    }

    public void removeCable(BlockPos pos) {
        if (cables.remove(pos)) dirty = true;
    }

    public void merge(CableNetwork other) {
        if (other == this) return;
        cables.addAll(other.cables);
        dirty = true;
    }
}
