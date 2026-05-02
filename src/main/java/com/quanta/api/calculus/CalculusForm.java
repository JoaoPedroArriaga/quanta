package com.quanta.api.calculus;

public enum CalculusForm {
    SELF("self", "ψ", 50),
    PROJECTILE("projectile", "→", 100),
    TOUCH("touch", "∮", 75),
    AREA("area", "∬", 200);

    private final String name;
    private final String symbol;
    private final int complexity;

    CalculusForm(String name, String symbol, int complexity) {
        this.name = name;
        this.symbol = symbol;
        this.complexity = complexity;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public int getComplexity() { return complexity; }
}