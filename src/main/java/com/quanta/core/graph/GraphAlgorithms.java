package com.quanta.core.graph;

import java.util.*;

public class GraphAlgorithms {

    public static <T extends GraphObject<T>> Set<T> bfs(Graph<T> graph, T start) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            for (T neighbor : graph.getConnectedNodes(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }

    public static <T extends GraphObject<T>> List<T> findPath(Graph<T> graph, T start, T end) {
        Map<T, T> parent = new HashMap<>();
        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            if (current == end) break;
            for (T neighbor : graph.getConnectedNodes(current)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parent.containsKey(end)) return Collections.emptyList();
        List<T> path = new ArrayList<>();
        for (T at = end; at != null; at = parent.get(at)) path.add(at);
        Collections.reverse(path);
        return path;
    }
}