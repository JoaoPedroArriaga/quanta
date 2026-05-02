package com.quanta.api.network;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import java.util.Set;

/**
 * Interface for nodes in the entanglement network.
 * Implemented by machines, switches, and computers.
 */
public interface INetworkNode {

    String getNodeId();
    BlockPos getNodePosition();
    Level getNodeLevel();
    NodeType getNodeType();

    Set<String> getProvidedResources();
    Set<String> getAcceptedResources();

    boolean isOnline();
    int getPriority();

    long getResourceAmount(String resourceId);
    long extractResource(String resourceId, long amount, boolean simulate);
    long insertResource(String resourceId, long amount, boolean simulate);
}
