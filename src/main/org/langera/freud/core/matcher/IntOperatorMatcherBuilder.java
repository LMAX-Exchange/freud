/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

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
