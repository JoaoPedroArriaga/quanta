package com.quanta.api;

import com.quanta.block.cable.CableType;

public interface IQuantumCable {
    boolean canConnect(CableType type);
    void transferEnergy(int amount);
}
