package com.quanta.core;

import com.quanta.Quanta;
import com.quanta.content.blockentity.base.QuantaProcessingBE;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.List;

@EventBusSubscriber(modid = Quanta.MOD_ID)
public class QuantaTickManager {
    
    private static final List<QuantaProcessingBE> pendingTicks = new ObjectArrayList<>();
    private static final int BATCH_SIZE = 50;
    private static int tickCounter = 0;
    
    public static void register(QuantaProcessingBE be) {
        if (!pendingTicks.contains(be)) {
            pendingTicks.add(be);
        }
    }
    
    public static void unregister(QuantaProcessingBE be) {
        pendingTicks.remove(be);
    }
    
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        Level level = event.getLevel();
        if (level.isClientSide || !(level instanceof ServerLevel)) return;
        
        tickCounter++;
        
        int size = pendingTicks.size();
        if (size == 0) return;
        
        int start = (tickCounter * BATCH_SIZE) % size;
        
        for (int i = 0; i < BATCH_SIZE && i < size; i++) {
            int index = (start + i) % size;
            QuantaProcessingBE be = pendingTicks.get(index);
            if (be != null && !be.isRemoved() && be.getLevel() == level) {
                be.tickServer();
            }
        }
    }
}