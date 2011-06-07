package org.langera.freud.core;

import org.hamcrest.Matcher;

public interface FreudAssertionAndFilter<T> extends FreudAssertion<T>
{
     <S> FreudAssertionAndFilter<T> of(Matcher<S> superTypeMatcher, Class<S> superType);
}
