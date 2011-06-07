package org.langera.freud.core;

import org.hamcrest.Matcher;

public interface FreudRule<T> extends FreudAssertion<T>
{
    FreudAssertionAndFilter<T> forEach();

    FreudAssertionAndFilter<T> forEach(Matcher<T> matcher);
}
