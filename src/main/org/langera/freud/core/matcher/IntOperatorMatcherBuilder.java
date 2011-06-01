package org.langera.freud.core.matcher;

import org.hamcrest.Description;

public final class IntOperatorMatcherBuilder<T>
{
    private final IntOperatorMatcherAdapter<T> adapter;

    public IntOperatorMatcherBuilder(final IntOperatorMatcherAdapter<T> adapter)
    {
        this.adapter = adapter;
    }

    public FreudMatcher<T> equalTo(final IntOperatorMatcherBuilder<T> param)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) == param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " == " + param.getAdapter().matcherDisplayName());
            }
        };

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

    public FreudMatcher<T> lessThan(final IntOperatorMatcherBuilder<T> param)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) < param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " < " + param.getAdapter().matcherDisplayName());
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

    public FreudMatcher<T> lessThanOrEqualTo(final IntOperatorMatcherBuilder<T> param)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) <= param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " < " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudMatcher<T> lessThanOrEqualTo(final int value)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) <= value;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " < " + value);
            }
        };
    }

    public FreudMatcher<T> greaterThan(final IntOperatorMatcherBuilder<T> param)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) > param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " > " + param.getAdapter().matcherDisplayName());
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

    public FreudMatcher<T> greaterThanOrEqualTo(final IntOperatorMatcherBuilder<T> param)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) >= param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " > " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudMatcher<T> greaterThanOrEqualTo(final int value)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                return adapter.valueOf(item) >= value;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(adapter.matcherDisplayName() + " > " + value);
            }
        };
    }

    IntOperatorMatcherAdapter<T> getAdapter()
    {
        return adapter;
    }
}
