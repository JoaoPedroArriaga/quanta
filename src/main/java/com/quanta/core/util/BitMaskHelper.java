package com.quanta.core.util;

public class BitMaskHelper {

    public static boolean hasFlag(int mask, int flag) {
        return (mask & flag) != 0;
    }

    public static int setFlag(int mask, int flag) {
        return mask | flag;
    }

    public static int clearFlag(int mask, int flag) {
        return mask & ~flag;
    }

    public static int toggleFlag(int mask, int flag) {
        return mask ^ flag;
    }

    public static boolean hasAnyFlag(int mask, int flags) {
        return (mask & flags) != 0;
    }

    public static boolean hasAllFlags(int mask, int flags) {
        return (mask & flags) == flags;
    }

    public static int combine(int... flags) {
        int result = 0;
        for (int flag : flags) {
            result |= flag;
        }
        return result;
    }

    public static int countFlags(int mask) {
        return Integer.bitCount(mask);
    }
}