package com.quanta.core.transfer;

import net.minecraft.world.level.material.Fluid;

public class GasStack {
    public static final GasStack EMPTY = new GasStack(null, 0);

    private final Fluid gas;
    private int amount;

    public GasStack(Fluid gas, int amount) {
        this.gas = gas;
        this.amount = amount;
    }

    public boolean isEmpty() { return gas == null || amount <= 0; }
    public Fluid getGas() { return gas; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public boolean isGasEqual(GasStack other) { return other != null && gas == other.gas; }
    public GasStack copy() { return new GasStack(gas, amount); }
}