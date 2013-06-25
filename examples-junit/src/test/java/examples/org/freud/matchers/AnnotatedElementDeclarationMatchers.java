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

package examples.org.freud.matchers;

import org.freud.analysed.javasource.AnnotatedElementDeclaration;
import org.freud.analysed.javasource.Annotation;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public final class AnnotatedElementDeclarationMatchers<T extends AnnotatedElementDeclaration> {

    public static <T extends AnnotatedElementDeclaration> AnnotatedElementDeclarationMatchers<T> getInstance() {
        return new AnnotatedElementDeclarationMatchers<T>();
    }

    private AnnotatedElementDeclarationMatchers() {
        // static utility - internal to all annotated element declarations
    }

    public FreudExtendedMatcher<T> hasDeclaredAnnotation(final String annotationName) {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) null, (Matcher<String>) null);
    }

    @SuppressWarnings("unchecked")
    public FreudExtendedMatcher<T> hasDeclaredAnnotation(final String annotationName, final String defaultValue) {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) equalTo(defaultValue));
    }

    @SuppressWarnings("unchecked")
    public FreudExtendedMatcher<T> hasDeclaredAnnotation(final String annotationName, final String key, final String value) {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) equalTo(key), (Matcher<String>) equalTo(value));
    }

    public FreudExtendedMatcher<T> hasDeclaredAnnotation(final String annotationName,
                                                         final Matcher<String> defaultValueMatcher) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final AnnotatedElementDeclaration toBeAnalysed) {
                for (Annotation declaredAnnotation : toBeAnalysed.getDeclaredAnnotations()) {
                    if (annotationName.equals(declaredAnnotation.getName())) {
                        return defaultValueMatcher.matches(declaredAnnotation.getDefaultParameter());
                    }
                }
                return false;

            }

            @Override
            public String toString() {
                Description description = new StringDescription();
                description.appendText("HasDeclaredAnnotation(").appendText(annotationName);
                defaultValueMatcher.describeTo(description);
                description.appendText(")");
                return description.toString();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(toString());
            }
        };
    }

    public FreudExtendedMatcher<T> hasDeclaredAnnotation(final String annotationName,
                                                         final Matcher<String> keyMatcher,
                                                         final Matcher<String> valueMatcher) {
        return new FreudExtendedMatcher<T>() {
            @Override
            protected boolean matchesSafely(final AnnotatedElementDeclaration toBeAnalysed) {
                for (Annotation declaredAnnotation : toBeAnalysed.getDeclaredAnnotations()) {
                    if (annotationName.equals(declaredAnnotation.getName())) {
                        if (keyMatcher != null || valueMatcher != null) {
                            for (Map.Entry<String, String> entry : declaredAnnotation.getParameterMap().entrySet()) {
                                if (keyMatcher == null || keyMatcher.matches(entry.getKey())) {
                                    if (valueMatcher == null || valueMatcher.matches(entry.getValue())) {
                                        return true;
                                    }
                                }
                            }
                            return false;
                        }
                        return true;
                    }
                }
                return false;

            }

            @Override
            public String toString() {
                Description description = new StringDescription();
                description.appendText("HasDeclaredAnnotation(").appendText(annotationName);
                description.appendText(")");
                return description.toString();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(toString());
            }
        };
    }

}
