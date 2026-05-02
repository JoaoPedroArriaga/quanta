package com.quanta.core.network;

import net.minecraft.core.BlockPos;

import java.util.*;

/**
 * Analyzes cable network topology.
 * Detects loops, finds bottlenecks, calculates optimal paths.
 */
public class NetworkTopology {

    /**
     * Detects if adding a cable at the given position would create a loop.
     */
    public static boolean wouldCreateLoop(Set<BlockPos> existing, BlockPos newPos,
                                           Map<BlockPos, Set<BlockPos>> connections) {
        Set<BlockPos> visited = new HashSet<>();
        return wouldCreateLoopDfs(newPos, newPos, existing, connections, visited);
    }

    private static boolean wouldCreateLoopDfs(BlockPos start, BlockPos current,
                                               Set<BlockPos> existing,
                                               Map<BlockPos, Set<BlockPos>> connections,
                                               Set<BlockPos> visited) {
        visited.add(current);
        Set<BlockPos> neighbors = connections.getOrDefault(current, Collections.emptySet());
        for (BlockPos neighbor : neighbors) {
            if (neighbor.equals(start) && visited.size() > 2) return true;
            if (!visited.contains(neighbor) && existing.contains(neighbor)) {
                if (wouldCreateLoopDfs(start, neighbor, existing, connections, visited)) return true;
            }
        }
        return false;
    }

    /**
     * Finds bottleneck cables that would split the network if removed.
     */
    public static Set<BlockPos> findBottlenecks(Set<BlockPos> cables,
                                                  Map<BlockPos, Set<BlockPos>> connections) {
        Set<BlockPos> bottlenecks = new HashSet<>();
        for (BlockPos cable : cables) {
            Set<BlockPos> neighbors = connections.getOrDefault(cable, Collections.emptySet());
            if (neighbors.size() <= 1) continue;

            // Check if removing this cable disconnects the network
            Map<BlockPos, Set<BlockPos>> withoutCable = new HashMap<>(connections);
            withoutCable.remove(cable);
            for (BlockPos neighbor : neighbors) {
                Set<BlockPos> nSet = new HashSet<>(withoutCable.getOrDefault(neighbor, Collections.emptySet()));
                nSet.remove(cable);
                withoutCable.put(neighbor, nSet);
            }

            if (!isConnected(neighbors.iterator().next(), cables, withoutCable)) {
                bottlenecks.add(cable);
            }
        }
        return bottlenecks;
    }

    private static boolean isConnected(BlockPos start, Set<BlockPos> cables,
                                        Map<BlockPos, Set<BlockPos>> connections) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            for (BlockPos neighbor : connections.getOrDefault(current, Collections.emptySet())) {
                if (!visited.contains(neighbor) && cables.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited.containsAll(cables);
    }
}