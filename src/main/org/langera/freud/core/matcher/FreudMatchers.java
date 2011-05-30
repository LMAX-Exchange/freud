package org.langera.freud.core.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public final class FreudMatchers
{
    private FreudMatchers()
    {
        // static utility
    }

    public static <T> FreudMatcher<T> no(final Matcher<T> matcher)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return !matcher.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("no(");
                matcher.describeTo(description);
                description.appendText(")");
            }
        };
    }

    public static <T> FreudMatcher<T> and(final Matcher<T> matcher1, final Matcher<T> matcher2)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return matcher1.matches(item) && matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                matcher1.describeTo(description);
                description.appendText("AND ");
                matcher2.describeTo(description);
            }
        };
    }

    public static <T> FreudMatcher<T> or(final Matcher<T> matcher1, final Matcher<T> matcher2)
    {
        return new FreudMatcher<T>()
        {
            @Override
            public boolean matchesSafely(final T item)
            {
                return matcher1.matches(item) || matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                matcher1.describeTo(description);
                description.appendText("OR ");
                matcher2.describeTo(description);
            }
        };
    }
}
