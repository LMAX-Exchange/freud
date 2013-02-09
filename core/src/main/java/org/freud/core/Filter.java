package org.freud.core;

public interface Filter<A> {

    boolean filter(A analysedObject);
}
