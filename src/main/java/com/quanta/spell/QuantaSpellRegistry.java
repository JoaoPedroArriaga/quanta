package com.quanta.spell;

import java.util.HashMap;
import java.util.Map;

public class QuantaSpellRegistry {
    private static final Map<String, SpellDefinition> SPELLS = new HashMap<>();
    
    public static void register(SpellDefinition spell) {
        SPELLS.put(spell.getId(), spell);
    }
    
    public static SpellDefinition get(String id) {
        return SPELLS.get(id);
    }
}
