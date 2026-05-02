package com.quanta.api.calculus;

public enum KnowledgeType {
    QUANTUM_MECHANICS("quantum_mechanics"),
    ENERGY_MANIPULATION("energy_manipulation"),
    REALITY_WARPING("reality_warping"),
    ENTANGLEMENT("entanglement"),
    SINGULARITY("singularity");
    
    private final String name;
    
    KnowledgeType(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
