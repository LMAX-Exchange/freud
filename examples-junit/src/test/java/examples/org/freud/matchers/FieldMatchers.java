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
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS;
import static org.freud.java.matcher.FreudDsl.no;

public final class FieldMatchers {
    private FieldMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<Field> fieldName() {
        return new StringMatcherBuilder<Field>(new RegexMatcherAdapter<Field>() {
            @Override
            public String getStringToMatch(final Field toBeAnalysed) {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName() {
                return "FieldName";
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static FreudExtendedMatcher<Field> fieldType(final Class type) {
        return fieldTypeMatches(Matchers.<Class>equalTo(type));
    }

    public static FreudExtendedMatcher<Field> fieldTypeIsSubTypeOf(final Class superType) {
        return new FreudExtendedMatcher<Field>() {
            @Override
            protected boolean matchesSafely(final Field item) {
                return superType.isAssignableFrom(item.getType());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("fieldTypeIsSubTypeOf(" + superType.getName() + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Field> fieldTypeMatches(final Matcher<? super Class> classMatcher) {
        return new FreudExtendedMatcher<Field>() {
            @Override
            protected boolean matchesSafely(final Field item) {
                return classMatcher.matches(item.getType());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("fieldType(" + classMatcher.toString() + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType) {
        return fieldAnnotation(annotationType, null);
    }

    public static FreudExtendedMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType, final Object value) {
        return fieldAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudExtendedMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType, final Matcher valueMatcher) {
        return new AnnotationFreudExtendedMatcher<Field>(valueMatcher, annotationType);
    }

    public static FreudExtendedMatcher<Field> definedWithModifier(final int modifierMask) {
        return new FreudExtendedMatcher<Field>() {
            @Override
            public final boolean matchesSafely(final Field toBeAnalysed) {
                return (toBeAnalysed.getModifiers() & modifierMask) != 0;
            }

            @Override
            public void describeTo(Description description) {
                StringBuilder sb = new StringBuilder(Modifier.toString(modifierMask));
                sb.append("Field()");
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                description.appendText(sb.toString());
            }
        };

    }

    public static FreudExtendedMatcher<Field> finalField() {
        return definedWithModifier(Modifier.FINAL);
    }

    public static FreudExtendedMatcher<Field> transientField() {
        return definedWithModifier(Modifier.TRANSIENT);
    }

    public static FreudExtendedMatcher<Field> volatileField() {
        return definedWithModifier(Modifier.VOLATILE);
    }


    public static FreudExtendedMatcher<Field> publicField() {
        return definedWithModifier(Modifier.PUBLIC);
    }

    public static FreudExtendedMatcher<Field> privateField() {
        return definedWithModifier(Modifier.PRIVATE);
    }

    public static FreudExtendedMatcher<Field> protectedField() {
        return definedWithModifier(Modifier.PROTECTED);
    }

    public static FreudExtendedMatcher<Field> packageProtectedField() {
        return no(privateField().or(publicField().or(protectedField())));
    }

    public static FreudExtendedMatcher<Field> staticField() {
        return definedWithModifier(Modifier.STATIC);

    }

    public static FreudExtendedMatcher<Field> declaredField() {
        return new FreudExtendedMatcher<Field>() {
            @Override
            protected boolean matchesSafely(final Field item) {
                final Class declaringClass = BREADCRUMBS.get(Class.class);
                if (declaringClass == null) {
                    throw new IllegalStateException("Cannot execute 'declaredMethod()' matcher - iterator does not support breadcrumbs");
                }
                return item.getDeclaringClass().equals(declaringClass);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("DeclaredField()");
            }
        };
    }
}
