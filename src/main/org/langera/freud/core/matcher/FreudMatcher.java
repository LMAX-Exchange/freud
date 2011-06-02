package org.langera.freud.core.matcher;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public abstract class FreudMatcher<T> extends TypeSafeMatcher<T>
{
    public FreudMatcher<T> and(Matcher<T> matcher)
    {
        return FreudDsl.and(this, matcher);
    }

    public FreudMatcher<T> or(Matcher<T> matcher)
    {
        return FreudDsl.or(this, matcher);
    }
}
