package org.freud.java.rule;

import org.freud.core.listener.AnalysisListener;
import org.hamcrest.Matcher;

import static org.freud.java.matcher.FreudDsl.trueMatcher;

public class FreudRuleBuilder<T> implements FreudRule<T>,
        FreudAssertionBuilder<T>, FreudAnalyser {

    private final Class<T> type;
    private Iterable<T> iterable;
    private Matcher<T> filter;
    private Matcher<T> assertion;

    public FreudRuleBuilder(final Class<T> type) {
        this.type = type;
    }

    public FreudAssertionBuilder<T> forEach() {
        filter = trueMatcher();
        return this;
    }

    public FreudAssertionBuilder<T> forEach(final Matcher<T> matcher) {
        filter = matcher;
        return this;
    }

    @Override
    public void analyse(final AnalysisListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener cannot be null");
        }
        for (T currentlyAnalysed : iterable) {
            analyse(listener, currentlyAnalysed);
        }
        listener.done();
    }

    private void analyse(final AnalysisListener listener, final T currentlyAnalysed) {
        try {
            if (filter == null || filter.matches(currentlyAnalysed)) {
                if (assertion.matches(currentlyAnalysed)) {
                    listener.passed(currentlyAnalysed);
                }
                else {
                    listener.failed(currentlyAnalysed, assertion.toString());
                }
            }
            else {
                listener.filtered(currentlyAnalysed, filter.toString());
            }
        }
        catch (Exception e) {
            listener.unexpected(currentlyAnalysed, e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public FreudRule<T> assertThat(final Matcher<T> matcher) {
        assertion = matcher;
        return this;
    }

    @Override
    public FreudAnalyser in(final Iterable<T> iterable) {
        this.iterable = iterable;
        return this;
    }

    public Class<T> getType() {
        return type;
    }
}
