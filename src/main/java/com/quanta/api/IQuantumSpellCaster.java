package com.quanta.api;

public interface IQuantumSpellCaster {
    boolean castSpell(String spellId);
    int getQuantaAvailable();
    void consumeQuanta(int amount);
}
