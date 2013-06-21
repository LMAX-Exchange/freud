package org.freud.java.rule;

public interface FreudRule<T> {
    FreudAnalyser in(Iterable<T> iterable);
}
