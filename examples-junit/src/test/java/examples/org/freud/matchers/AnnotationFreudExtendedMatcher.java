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

import org.freud.java.matcher.FreudExtendedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AnnotationFreudExtendedMatcher<T extends AnnotatedElement> extends FreudExtendedMatcher<T> {
    private final Method valueGetter;
    private final Matcher valueMatcher;
    private final Class<? extends Annotation> annotationType;

    public AnnotationFreudExtendedMatcher(final Matcher valueMatcher, final Class<? extends Annotation> annotationType) {
        this.valueMatcher = valueMatcher;
        this.annotationType = annotationType;
        if (valueMatcher != null) {
            try {
                valueGetter = annotationType.getMethod("value");
            }
            catch (NoSuchMethodException e) {
                throw new IllegalStateException("Did not find 'value' in Annotation " + annotationType, e);
            }
        }
        else {
            valueGetter = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean matchesSafely(final AnnotatedElement item) {
        Annotation annotation = item.getAnnotation(annotationType);
        return annotation != null &&
                (valueMatcher == null || valueMatcher.matches(getValue(annotation)));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("Annotation(" + annotationType +
                                       (valueMatcher == null ? ")" : "(value = " + valueMatcher.toString() + "))"));
    }

    private Object getValue(Annotation annotation) {
        try {
            return valueGetter.invoke(annotation);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get value for annotation " + annotation, e);
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to get value for annotation " + annotation, e.getTargetException());
        }
    }
}
