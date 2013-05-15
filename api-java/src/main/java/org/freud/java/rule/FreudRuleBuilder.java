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

package org.freud.java.rule;

import org.freud.core.listener.AnalysisListener;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.freud.java.matcher.FreudDsl.trueMatcher;

public class FreudRuleBuilder<T> implements FreudRule<T>,
        FreudAssertionAndFilterBuilder<T>, FreudAnalyser {

    private final Class<T> type;
    private Iterable<T> iterable;
    private Matcher<T> filter;
    private Matcher<T> assertion;

    public FreudRuleBuilder(final Class<T> type) {
        this.type = type;
    }

    public FreudAssertionAndFilterBuilder<T> forEach() {
        filter = trueMatcher();
        return this;
    }

    public FreudAssertionAndFilterBuilder<T> forEach(final Matcher<T> matcher) {
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

    @Override
    public <S> FreudAssertionAndFilterBuilder<T> of(final Matcher<S> superTypeMatcher, final Class<S> superType) {
        FreudExtendedMatcher<T> superTypeFilterWrapper = new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final T item) {
return false;
// TODO               S currentlyAnalysedSuperType = FreudRuntimeContext.getCurrentlyAnalysed(superType);
//                return (currentlyAnalysedSuperType != null) &&
//                        superTypeMatcher.matches(currentlyAnalysedSuperType);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(superType.getCanonicalName());
                description.appendText(" Of (");
                superTypeMatcher.describeTo(description);
                description.appendText(")");
            }
        };
        filter = superTypeFilterWrapper.and(filter);
        return this;
    }

    public Class<T> getType() {
        return type;
    }
}
