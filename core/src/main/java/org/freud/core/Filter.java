package org.freud.core;

public interface Filter<A> {

    boolean accept(A analysedObject);
}
