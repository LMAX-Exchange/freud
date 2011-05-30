package org.langera.freud.core;

import org.hamcrest.Matcher;

public interface FreudAssertion<T>
{
    FreudAnalyser assertThat(Matcher<T> matcher);
}
