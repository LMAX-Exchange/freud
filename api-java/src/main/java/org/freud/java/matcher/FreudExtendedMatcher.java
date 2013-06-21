package org.freud.java.matcher;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public abstract class FreudExtendedMatcher<T> extends TypeSafeMatcher<T> {
    public FreudExtendedMatcher<T> and(Matcher<T> matcher) {
        return FreudDsl.and(this, matcher);
    }

    public FreudExtendedMatcher<T> or(Matcher<T> matcher) {
        return FreudDsl.or(this, matcher);
    }
}
