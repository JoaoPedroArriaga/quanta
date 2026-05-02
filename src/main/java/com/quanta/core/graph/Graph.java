package com.quanta.core.graph;

import java.util.*;

public class Graph<T extends GraphObject<T>> {

    private final Set<T> nodes = new HashSet<>();
    private final Map<T, Set<T>> edges = new HashMap<>();
    private int graphId = 0;

    public void addNode(T node) {
        if (nodes.add(node)) {
            node.setGraph(this);
            node.setInGraph(true);
            edges.put(node, new HashSet<>());
            node.onGraphChanged();
        }
    }

    public void removeNode(T node) {
        if (nodes.remove(node)) {
            Set<T> connected = edges.remove(node);
            if (connected != null) {
                for (T other : connected) {
                    edges.get(other).remove(node);
                    other.onGraphChanged();
                }
            }
            node.setGraph(null);
            node.setInGraph(false);
            node.onGraphChanged();
        }
    }

    public void addEdge(T from, T to) {
        if (from == null || to == null || from == to) return;
        edges.get(from).add(to);
        edges.get(to).add(from);
        from.onGraphChanged();
        to.onGraphChanged();
    }

    public Set<T> getNodes() { return Collections.unmodifiableSet(nodes); }

    public Set<T> getConnectedNodes(T node) {
        return edges.getOrDefault(node, Collections.emptySet());
    }

    public void mergeWith(Graph<T> other) {
        if (other == null || other == this) return;
        for (T node : other.nodes) addNode(node);
        for (var entry : other.edges.entrySet()) {
            for (T to : entry.getValue()) addEdge(entry.getKey(), to);
        }
        other.clear();
    }

    public void clear() {
        for (T node : nodes) {
            node.setGraph(null);
            node.setInGraph(false);
        }
        nodes.clear();
        edges.clear();
    }

    public boolean isEmpty() { return nodes.isEmpty(); }
    public int size() { return nodes.size(); }
    public int getGraphId() { return graphId; }
    public void setGraphId(int id) { this.graphId = id; }
}