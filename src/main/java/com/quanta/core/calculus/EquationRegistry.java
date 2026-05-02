package com.quanta.core.calculus;

import com.quanta.api.calculus.IEquation;

import java.util.*;

public class EquationRegistry {

    private static final Map<String, IEquation> EQUATIONS = new HashMap<>();

    public static void register(IEquation equation) {
        EQUATIONS.put(equation.getId(), equation);
    }

    public static Optional<IEquation> get(String id) {
        return Optional.ofNullable(EQUATIONS.get(id));
    }

    public static Collection<IEquation> getAll() {
        return Collections.unmodifiableCollection(EQUATIONS.values());
    }
}