package org.freud.java.rule;

import org.hamcrest.Matcher;

public interface FreudAssertionBuilder<T> {
    FreudRule<T> assertThat(Matcher<T> matcher);
}
