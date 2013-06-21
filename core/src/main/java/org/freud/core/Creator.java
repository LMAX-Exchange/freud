package org.freud.core;

public interface Creator<S, A> {

    A create(S source);
}
