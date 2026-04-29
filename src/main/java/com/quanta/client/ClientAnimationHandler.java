package com.quanta.client;

import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = com.quanta.Quanta.MOD_ID, value = Dist.CLIENT)
public class ClientAnimationHandler {
    
    private static final Long2IntOpenHashMap animatedBlocks = new Long2IntOpenHashMap();
    
    // Ângulo contínuo - nunca reseta, apenas aumenta infinitamente
    private static float orbitAngle = 0;
    private static float floatOffset = 0;
    
    private static final float ANGLE_INCREMENT = 0.025f;
    private static final float OFFSET_INCREMENT = 0.015f;
    
    static {
        animatedBlocks.defaultReturnValue(-1);
    }
    
    public static void registerAnimatedBlock(BlockPos pos) {
        animatedBlocks.put(pos.asLong(), 0);
    }
    
    public static void unregisterAnimatedBlock(BlockPos pos) {
        animatedBlocks.remove(pos.asLong());
    }
    
    public static float getOrbitAngle() {
        return orbitAngle;
    }
    
    public static float getFloatOffset() {
        return floatOffset;
    }
    
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;
        
        // Incremento contínuo - SEM RESET
        orbitAngle += ANGLE_INCREMENT;
        floatOffset += OFFSET_INCREMENT;
        
        // NÃO reseta o ângulo
        // O ângulo vai crescer infinitamente (float suporta valores grandes)
        
        if (mc.player == null) return;
        
        if (animatedBlocks.isEmpty()) return;
        
        int renderDistance = mc.options.renderDistance().get();
        long playerPos = mc.player.blockPosition().asLong();
        int playerX = BlockPos.getX(playerPos);
        int playerZ = BlockPos.getZ(playerPos);
        
        long[] keys = animatedBlocks.keySet().toLongArray();
        
        for (long pos : keys) {
            int x = BlockPos.getX(pos);
            int z = BlockPos.getZ(pos);
            
            if (Math.abs(x - playerX) <= renderDistance && Math.abs(z - playerZ) <= renderDistance) {
                BlockPos blockPos = BlockPos.containing(x, BlockPos.getY(pos), z);
                level.sendBlockUpdated(blockPos, level.getBlockState(blockPos), level.getBlockState(blockPos), 2);
            }
        }
    }
    
    public static void clear() {
        animatedBlocks.clear();
    }
}