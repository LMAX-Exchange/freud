package org.freud.java.matcher;

import org.hamcrest.Description;

public final class IntOperatorMatcherBuilder<T> {
    private final IntOperatorMatcherAdapter<T> adapter;

    public IntOperatorMatcherBuilder(final IntOperatorMatcherAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public FreudExtendedMatcher<T> equalTo(final IntOperatorMatcherBuilder<T> param) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) == param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " == " + param.getAdapter().matcherDisplayName());
            }
        };

    }

    public FreudExtendedMatcher<T> equalTo(final int value) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) == value;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " == " + value);
            }
        };
    }

    public FreudExtendedMatcher<T> lessThan(final IntOperatorMatcherBuilder<T> param) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) < param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " < " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudExtendedMatcher<T> lessThan(final int value) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) < value;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " < " + value);
            }
        };
    }

    public FreudExtendedMatcher<T> lessThanOrEqualTo(final IntOperatorMatcherBuilder<T> param) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) <= param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " < " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudExtendedMatcher<T> lessThanOrEqualTo(final int value) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) <= value;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " < " + value);
            }
        };
    }

    public FreudExtendedMatcher<T> greaterThan(final IntOperatorMatcherBuilder<T> param) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) > param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " > " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudExtendedMatcher<T> greaterThan(final int value) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) > value;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " > " + value);
            }
        };
    }

    public FreudExtendedMatcher<T> greaterThanOrEqualTo(final IntOperatorMatcherBuilder<T> param) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) >= param.getAdapter().valueOf(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " > " + param.getAdapter().matcherDisplayName());
            }
        };
    }

    public FreudExtendedMatcher<T> greaterThanOrEqualTo(final int value) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
                return adapter.valueOf(item) >= value;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(adapter.matcherDisplayName() + " > " + value);
            }
        };
    }

    IntOperatorMatcherAdapter<T> getAdapter() {
        return adapter;
    }
}
