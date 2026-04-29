package com.quanta.api;

import com.quanta.block.cable.CableType;

public interface IQuantaCable {
    boolean canConnect(CableType type);
    void transferEnergy(int amount);
}
