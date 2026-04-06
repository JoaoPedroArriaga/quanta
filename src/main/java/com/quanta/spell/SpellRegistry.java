package com.quanta.spell;

import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {
    private static final Map<String, QuantumSpell> SPELLS = new HashMap<>();
    
    public static void register(QuantumSpell spell) {
        SPELLS.put(spell.getId(), spell);
    }
    
    public static QuantumSpell get(String id) {
        return SPELLS.get(id);
    }
}
