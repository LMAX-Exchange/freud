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

package org.freud.java.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class FreudDsl {
    private FreudDsl() {
        // static utility
    }


    public static <T> Matcher<T> trueMatcher() {
        return new FreudExtendedMatcher<T>() {

            @Override
            protected boolean matchesSafely(final T item) {
                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("TRUE");
            }
        };
    }

    public static <T> FreudExtendedMatcher<T> no(final Matcher<T> matcher) {
        return new FreudExtendedMatcher<T>() {
            @Override
            public boolean matchesSafely(final T item) {
                return !matcher.matches(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("NO (");
                matcher.describeTo(description);
                description.appendText(")");
            }
        };
    }

    public static <T> FreudExtendedMatcher<T> and(final Matcher<T> matcher1, final Matcher<T> matcher2) {
        return new FreudExtendedMatcher<T>() {
            @Override
            public boolean matchesSafely(final T item) {
                return matcher1.matches(item) && matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("(");
                matcher1.describeTo(description);
                description.appendText(") AND (");
                matcher2.describeTo(description);
                description.appendText(")");
            }
        };
    }

    public static <T> FreudExtendedMatcher<T> or(final Matcher<T> matcher1, final Matcher<T> matcher2) {
        return new FreudExtendedMatcher<T>() {
            @Override
            public boolean matchesSafely(final T item) {
                return matcher1.matches(item) || matcher2.matches(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("(");
                matcher1.describeTo(description);
                description.appendText(") OR (");
                matcher2.describeTo(description);
                description.appendText(")");
            }
        };
    }
}
