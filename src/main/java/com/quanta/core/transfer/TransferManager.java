package com.quanta.core.transfer;

import com.quanta.api.cable.ICableComponent;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;

public class TransferManager {

    private final Map<TransferType, ICableComponent> handlers = new Object2ObjectOpenHashMap<>();
    private final CableContext context;
    private int tickCounter;

    public TransferManager(CableContext context) {
        this.context = context;
    }

    public void register(ICableComponent handler) {
        handlers.put(handler.getType(), handler);
    }

    public void unregister(TransferType type) {
        handlers.remove(type);
    }

    public ICableComponent getHandler(TransferType type) {
        return handlers.get(type);
    }

    public void tick() {
        tickCounter++;
        int cycle = tickCounter & 0b11;

        for (var entry : handlers.entrySet()) {
            if ((entry.getKey().ordinal() & 0b11) == cycle) {
                entry.getValue().tick(context);
            }
        }
    }

    public int getActiveHandlers() { return handlers.size(); }
}