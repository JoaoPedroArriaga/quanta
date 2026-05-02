package com.quanta.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class MathHelper {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double distanceSq(BlockPos a, BlockPos b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        double dz = a.getZ() - b.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    public static int getRedstoneSignal(int value, int maxValue) {
        if (value <= 0 || maxValue <= 0) return 0;
        return clamp((value * 15) / maxValue, 0, 15);
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static Direction getDirectionTo(BlockPos from, BlockPos to) {
        int dx = Integer.compare(to.getX(), from.getX());
        int dy = Integer.compare(to.getY(), from.getY());
        int dz = Integer.compare(to.getZ(), from.getZ());

        for (Direction dir : Direction.values()) {
            if (dir.getStepX() == dx && dir.getStepY() == dy && dir.getStepZ() == dz) {
                return dir;
            }
        }
        return Direction.NORTH;
    }
}