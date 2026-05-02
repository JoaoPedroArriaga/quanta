package com.quanta.core.calculus;

import com.quanta.api.calculus.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.*;

public class DiscoveryTree {

    private static final Map<Calculus, Set<Calculus>> PREREQUISITES = new Object2ObjectOpenHashMap<>();
    private static final Map<Calculus, Set<Calculus>> UNLOCKS = new Object2ObjectOpenHashMap<>();

    public static void addPrerequisite(Calculus calculus, Calculus prerequisite) {
        PREREQUISITES.computeIfAbsent(calculus, k -> new ObjectOpenHashSet<>()).add(prerequisite);
        UNLOCKS.computeIfAbsent(prerequisite, k -> new ObjectOpenHashSet<>()).add(calculus);
    }

    public static Set<Calculus> getPrerequisites(Calculus calculus) {
        return PREREQUISITES.getOrDefault(calculus, Collections.emptySet());
    }

    public static Set<Calculus> getUnlocks(Calculus calculus) {
        return UNLOCKS.getOrDefault(calculus, Collections.emptySet());
    }

    public static boolean canLearn(Calculus calculus, Set<Calculus> known) {
        Set<Calculus> prereqs = getPrerequisites(calculus);
        return known.containsAll(prereqs);
    }

    public static List<Calculus> getAvailableToLearn(Set<Calculus> known, Map<KnowledgeType, Integer> knowledge) {
        List<Calculus> available = new ArrayList<>();
        for (Calculus calculus : CalculusRegistry.getUnlockable(knowledge)) {
            if (!known.contains(calculus) && canLearn(calculus, known)) {
                available.add(calculus);
            }
        }
        return available;
    }
}