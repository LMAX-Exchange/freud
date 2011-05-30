package org.langera.freud.core.matcher;

public interface IntOperatorMatcherAdapter<T>
{
    int valueOf(T matched);

    String matcherDisplayName();
}
