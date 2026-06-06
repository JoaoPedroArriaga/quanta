package com.quanta.content.blockentity.cable.component;

import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.network.CableNetwork;
import com.quanta.core.network.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class CableNetworkProxy {

    private final CableBlockEntity be;
    private CableNetwork cachedNetwork;
    private int networkCheckCounter;
    private int tickCounter;

    public CableNetworkProxy(CableBlockEntity be) {
        this.be = be;
    }

    private Level getLevel() {
        return be.getLevel();
    }

    private BlockPos getPos() {
        return be.getBlockPos();
    }

    public void setTickCounter(int tickCounter) {
        this.tickCounter = tickCounter;
    }

    public CableNetwork getNetwork() {
        Level level = getLevel();
        if (level == null) {
            return null;
        }
        if (cachedNetwork == null || networkCheckCounter != tickCounter) {
            cachedNetwork = NetworkManager.getNetwork(level, getPos());
            networkCheckCounter = tickCounter;
        }
        return cachedNetwork;
    }

    public void rebuildNetwork() {
        Level level = getLevel();
        if (level != null && !level.isClientSide) {
            NetworkManager.rebuildNetwork(level, getPos());
            cachedNetwork = null;
        }
    }

    public void removeCable() {
        Level level = getLevel();
        if (level != null && !level.isClientSide) {
            NetworkManager.removeCable(level, getPos());
            cachedNetwork = null;
        }
    }

    public void clearAllBuffers() {
        CableNetwork net = getNetwork();
        if (net != null) {
            net.clearAllBuffers();
        }
    }

    public void notifyNetwork() {
        Level level = getLevel();
        if (level == null) {
            return;
        }
        CableNetwork net = NetworkManager.getNetwork(level, getPos());
        if (net != null) {
            for (BlockPos cablePos : net.getCables()) {
                if (level.getBlockEntity(cablePos) instanceof CableBlockEntity cableBe) {
                    cableBe.invalidateCache();
                    cableBe.getConnectionManager().markDirty();
                }
            }
        }
    }

    public void invalidateCache() {
        cachedNetwork = null;
    }
}