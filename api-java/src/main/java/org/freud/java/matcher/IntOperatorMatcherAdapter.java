package org.freud.java.matcher;

public interface IntOperatorMatcherAdapter<T> {
    int valueOf(T matched);

    String matcherDisplayName();
}
