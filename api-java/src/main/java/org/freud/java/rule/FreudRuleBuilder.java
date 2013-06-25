/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
