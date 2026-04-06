package com.quanta.spell;

public class QuantumSpell {
    private final String id;
    private final String name;
    private final int cost;
    
    public QuantumSpell(String id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public int getCost() { return cost; }
    
    public boolean cast() {
        // Lógica do spell
        return true;
    }
}
