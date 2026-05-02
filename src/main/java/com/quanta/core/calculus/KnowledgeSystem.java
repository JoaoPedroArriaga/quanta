package com.quanta.core.calculus;

import com.quanta.api.calculus.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class KnowledgeSystem {

    private static final Map<UUID, Map<KnowledgeType, Integer>> PLAYER_KNOWLEDGE = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, Set<Calculus>> KNOWN_CALCULUS = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, Set<String>> DISCOVERED_EQUATIONS = new Object2ObjectOpenHashMap<>();

    public static void gainKnowledge(Player player, IKnowledgeProvider provider) {
        var knowledge = PLAYER_KNOWLEDGE.computeIfAbsent(
            player.getUUID(), k -> new EnumMap<>(KnowledgeType.class));
        knowledge.merge(provider.getKnowledgeType(), provider.getKnowledgeValue(), Integer::sum);

        if (Math.random() < provider.getDiscoveryChance()) {
            tryDiscover(player);
        }
    }

    public static int getKnowledge(Player player, KnowledgeType type) {
        var knowledge = PLAYER_KNOWLEDGE.get(player.getUUID());
        return knowledge != null ? knowledge.getOrDefault(type, 0) : 0;
    }

    public static int getTotalKnowledge(Player player) {
        var knowledge = PLAYER_KNOWLEDGE.get(player.getUUID());
        if (knowledge == null) return 0;
        return knowledge.values().stream().mapToInt(Integer::intValue).sum();
    }

    public static boolean knowsCalculus(Player player, Calculus calculus) {
        var known = KNOWN_CALCULUS.get(player.getUUID());
        return known != null && known.contains(calculus);
    }

    public static boolean knowsEquation(Player player, String equationId) {
        var discovered = DISCOVERED_EQUATIONS.get(player.getUUID());
        return discovered != null && discovered.contains(equationId);
    }

    public static void discoverEquation(Player player, String equationId) {
        DISCOVERED_EQUATIONS.computeIfAbsent(player.getUUID(), k -> new ObjectOpenHashSet<>())
            .add(equationId);
    }

    public static Set<Calculus> getKnownCalculus(Player player) {
        return KNOWN_CALCULUS.getOrDefault(player.getUUID(), Collections.emptySet());
    }

    public static Set<String> getDiscoveredEquations(Player player) {
        return DISCOVERED_EQUATIONS.getOrDefault(player.getUUID(), Collections.emptySet());
    }

    private static void tryDiscover(Player player) {
        Map<KnowledgeType, Integer> knowledge = PLAYER_KNOWLEDGE.getOrDefault(
            player.getUUID(), Collections.emptyMap());
        var known = KNOWN_CALCULUS.computeIfAbsent(player.getUUID(), k -> new ObjectOpenHashSet<>());

        CalculusRegistry.getUnlockable(knowledge).stream()
            .filter(c -> !known.contains(c))
            .findFirst()
            .ifPresent(known::add);
    }

    public static void reset(Player player) {
        PLAYER_KNOWLEDGE.remove(player.getUUID());
        KNOWN_CALCULUS.remove(player.getUUID());
        DISCOVERED_EQUATIONS.remove(player.getUUID());
    }

    public static void clearAll() {
        PLAYER_KNOWLEDGE.clear();
        KNOWN_CALCULUS.clear();
        DISCOVERED_EQUATIONS.clear();
    }
}