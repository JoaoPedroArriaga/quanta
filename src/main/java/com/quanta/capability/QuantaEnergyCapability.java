package com.quanta.capability;

import com.quanta.api.cable.IQuantaStorage;
import net.minecraft.core.Direction;

/**
 * Helper class for interacting with Quanta energy capability.
 */
public class QuantaEnergyCapability {

    public static int transfer(IQuantaStorage from, IQuantaStorage to, int amount) {
        int extracted = from.extractQuanta(amount, false, true);
        if (extracted <= 0) return 0;

        int inserted = to.addQuanta(extracted);
        if (inserted > 0) {
            from.extractQuanta(inserted, false, false);
        }
        return inserted;
    }

    public static int fillToCapacity(IQuantaStorage storage, int available) {
        int space = storage.getQuantaCapacity() - storage.getQuantaStored();
        int toAdd = Math.min(available, space);
        return storage.addQuanta(toAdd);
    }

    public static boolean areConnected(IQuantaStorage a, IQuantaStorage b) {
        return a.getConnections().contains(b);
    }

    public static IQuantaStorage getStorage(IQuantaStorage sided, Direction side) {
        return sided; // IQuantaStorage is not side-dependent by default
    }
}