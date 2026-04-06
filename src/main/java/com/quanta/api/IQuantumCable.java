package com.quanta.api;

import com.quanta.cable.CableType;

public interface IQuantumCable {
    boolean canConnect(CableType type);
    void transferEnergy(int amount);
}
