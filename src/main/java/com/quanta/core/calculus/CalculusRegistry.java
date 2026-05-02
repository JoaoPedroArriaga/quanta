package com.quanta.core.calculus;

import com.quanta.api.calculus.*;

import java.util.*;

public class CalculusRegistry {

    private static final List<Calculus> ALL = new ArrayList<>();
    private static final Set<Calculus> BASE = new HashSet<>();
    private static final Map<Calculus, String> CALCULUS_TO_EQUATION = new HashMap<>();

    static {
        registerBase(new Calculus(CalculusForm.SELF, CalculusElement.NEUTRAL, CalculusEffect.INFUSION, 0, true));
        registerBase(new Calculus(CalculusForm.SELF, CalculusElement.ORDER, CalculusEffect.REVELATION, 0, true));
        registerBase(new Calculus(CalculusForm.TOUCH, CalculusElement.NEUTRAL, CalculusEffect.ACCELERATION, 0, true));
        registerBase(new Calculus(CalculusForm.TOUCH, CalculusElement.ORDER, CalculusEffect.STABILIZATION, 0, true));

        register(new Calculus(CalculusForm.SELF, CalculusElement.CHAOS, CalculusEffect.TELEPORT, 100, false, KnowledgeType.ENTANGLEMENT), "teleport");
        register(new Calculus(CalculusForm.SELF, CalculusElement.CHAOS, CalculusEffect.LINK, 150, false, KnowledgeType.ENTANGLEMENT), "link");
        register(new Calculus(CalculusForm.PROJECTILE, CalculusElement.CHAOS, CalculusEffect.DAMAGE, 200, false, KnowledgeType.REALITY_WARPING), "chaos_bolt");
        register(new Calculus(CalculusForm.AREA, CalculusElement.ORDER, CalculusEffect.HEAL, 250, false, KnowledgeType.QUANTUM_MECHANICS), "heal_aura");
    }

    public static void registerBase(Calculus calculus) {
        ALL.add(calculus);
        BASE.add(calculus);
    }

    public static void register(Calculus calculus, String equationId) {
        ALL.add(calculus);
        CALCULUS_TO_EQUATION.put(calculus, equationId);
    }

    public static Optional<Calculus> find(CalculusForm form, CalculusElement element, CalculusEffect effect) {
        return ALL.stream()
            .filter(c -> c.getForm() == form && c.getElement() == element && c.getEffect() == effect)
            .findFirst();
    }

    public static String getEquationId(Calculus calculus) {
        return CALCULUS_TO_EQUATION.get(calculus);
    }

    public static List<Calculus> getUnlockable(Map<KnowledgeType, Integer> knowledge) {
        List<Calculus> unlockable = new ArrayList<>();
        for (Calculus calculus : ALL) {
            if (calculus.isBaseCalculus()) continue;
            if (meetsRequirements(calculus, knowledge)) {
                unlockable.add(calculus);
            }
        }
        return unlockable;
    }

    private static boolean meetsRequirements(Calculus calculus, Map<KnowledgeType, Integer> knowledge) {
        int totalKnowledge = knowledge.values().stream().mapToInt(Integer::intValue).sum();
        return totalKnowledge >= calculus.getComplexity();
    }

    public static boolean isBase(Calculus calculus) {
        return BASE.contains(calculus);
    }
}