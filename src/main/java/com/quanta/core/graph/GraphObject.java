package com.quanta.core.graph;

public interface GraphObject<T extends GraphObject<T>> {
    Graph<T> getGraph();
    void setGraph(Graph<T> graph);
    boolean isInGraph();
    void setInGraph(boolean inGraph);
    void onGraphChanged();
    boolean canConnectTo(T other);
}