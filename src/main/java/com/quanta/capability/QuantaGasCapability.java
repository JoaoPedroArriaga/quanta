package com.quanta.capability;

import com.quanta.api.cable.IGasHandler;
import com.quanta.core.transfer.GasStack;

/**
 * Helper class for interacting with Quanta gas capability.
 */
public class QuantaGasCapability {

    /**
     * Attempts to transfer gas between two handlers.
     * Returns the amount actually transferred.
     */
    public static int transfer(IGasHandler from, IGasHandler to, int maxAmount) {
        GasStack drained = from.drain(maxAmount, true);
        if (drained.isEmpty()) return 0;

        int filled = to.fill(drained, true);
        if (filled <= 0) return 0;

        GasStack actual = from.drain(filled, false);
        to.fill(actual, false);
        return filled;
    }

    /**
     * Checks if two gas handlers can exchange (same gas type or one is empty).
     */
    public static boolean canExchange(IGasHandler from, IGasHandler to) {
        if (from.isEmpty()) return false;
        if (to.isFull()) return false;

        GasStack sample = from.drain(1, true);
        if (sample.isEmpty()) return false;

        return to.fill(sample, true) > 0;
    }

    /**
     * Gets remaining capacity of a gas handler.
     */
    public static int getRemainingCapacity(IGasHandler handler) {
        return handler.getMaxGasAmount() - handler.getGasAmount();
    }

    /**
     * Gets fill percentage (0.0 to 1.0).
     */
    public static float getFillPercentage(IGasHandler handler) {
        int max = handler.getMaxGasAmount();
        return max > 0 ? (float) handler.getGasAmount() / max : 0f;
    }
}