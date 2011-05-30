package org.langera.freud.core;

import org.hamcrest.Matcher;

public interface FreudRule<T> extends FreudAssertion<T>
{
    FreudAssertion<T> forEach(Matcher<T> matcher);
}
