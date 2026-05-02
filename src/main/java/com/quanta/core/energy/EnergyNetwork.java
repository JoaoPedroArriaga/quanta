package com.quanta.core.energy;

import com.quanta.api.cable.IQuantaStorage;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;

import java.util.List;

public class EnergyNetwork {

    private final List<NetworkNode> nodes = new ObjectArrayList<>();
    private int totalEnergy;
    private int totalCapacity;
    private boolean dirty;

    public void addNode(BlockPos pos, IQuantaStorage storage) {
        nodes.add(new NetworkNode(pos, storage));
        recalculate();
    }

    public void removeNode(BlockPos pos) {
        nodes.removeIf(n -> n.pos.equals(pos));
        recalculate();
    }

    public void tick() {
        if (!dirty) return;

        int targetPerNode = nodes.isEmpty() ? 0 : totalEnergy / nodes.size();

        for (NetworkNode node : nodes) {
            int current = node.storage.getQuantaStored();
            if (current < targetPerNode) {
                int needed = targetPerNode - current;
                int received = requestFromOthers(node, needed);
                if (received > 0) {
                    node.storage.addQuanta(received);
                }
            }
        }
        dirty = false;
    }

    private int requestFromOthers(NetworkNode requester, int needed) {
        int received = 0;
        for (NetworkNode donor : nodes) {
            if (donor == requester) continue;
            int available = donor.storage.getQuantaStored();
            if (available <= 0) continue;
            int toTake = Math.min(needed - received, available);
            int taken = donor.storage.extractQuanta(toTake, false);
            received += taken;
            if (received >= needed) break;
        }
        return received;
    }

    private void recalculate() {
        totalEnergy = 0;
        totalCapacity = 0;
        for (NetworkNode node : nodes) {
            totalEnergy += node.storage.getQuantaStored();
            totalCapacity += node.storage.getQuantaCapacity();
        }
        dirty = true;
    }

    public int getTotalEnergy() { return totalEnergy; }
    public int getTotalCapacity() { return totalCapacity; }
    public int getNodeCount() { return nodes.size(); }
    public void markDirty() { dirty = true; }

    private record NetworkNode(BlockPos pos, IQuantaStorage storage) {}
}