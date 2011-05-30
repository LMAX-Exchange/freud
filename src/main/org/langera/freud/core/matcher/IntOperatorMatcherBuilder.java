package org.langera.freud.core.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class IntOperatorMatcherBuilder<T>
{
    private final IntOperatorMatcherAdapter<T> adapter;

    public IntOperatorMatcherBuilder(final IntOperatorMatcherAdapter<T> adapter)
    {
        this.adapter = adapter;
    }

    public FreudMatcher<T> equalTo(final int value)
    {
          return new FreudMatcher<T>()
          {
              @Override
              protected boolean matchesSafely(final T item)
              {
                  return adapter.valueOf(item) == value;
              }

              @Override
              public void describeTo(final Description description)
              {
                  description.appendText(adapter.matcherDisplayName() + " == " + value);
              }
          };
    }

    public FreudMatcher<T> lessThan(final int value)
    {
          return new FreudMatcher<T>()
          {
              @Override
              protected boolean matchesSafely(final T item)
              {
                  return adapter.valueOf(item) < value;
              }

              @Override
              public void describeTo(final Description description)
              {
                  description.appendText(adapter.matcherDisplayName() + " < " + value);
              }
          };
    }

    public FreudMatcher<T> greaterThan(final int value)
    {
          return new FreudMatcher<T>()
          {
              @Override
              protected boolean matchesSafely(final T item)
              {
                  return adapter.valueOf(item) > value;
              }

              @Override
              public void describeTo(final Description description)
              {
                  description.appendText(adapter.matcherDisplayName() + " > " + value);
              }
          };
    }
}
