package com.quanta.api.network;

/**
 * Interface for blocks that provide entanglement capabilities.
 * Implemented by Entangler, Tunnel Relay, and Quantum Computers.
 */
public interface IEntanglementProvider {

    int getMaxConnections();
    int getActiveConnections();
    boolean supportsCrossDimensional();
    int getConnectionRange();

    boolean connect(IEntanglementProvider other);
    boolean disconnect(IEntanglementProvider other);
    boolean isConnectedTo(IEntanglementProvider other);
}
