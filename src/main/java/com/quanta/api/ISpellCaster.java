package com.quanta.api;

public interface ISpellCaster {
    boolean castSpell(String spellId);
    int getMana();
    void consumeMana(int amount);
}
