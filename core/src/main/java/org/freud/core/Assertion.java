package org.freud.core;

public interface Assertion<A> {

    boolean satisfies(A analysedObject);
}
