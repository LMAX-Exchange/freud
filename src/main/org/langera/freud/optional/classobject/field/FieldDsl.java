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

package org.langera.freud.optional.classobject.field;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.langera.freud.core.FreudRuntimeContext;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.StringMatcherBuilder;
import org.langera.freud.optional.classobject.AnnotationFreudMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.langera.freud.core.matcher.FreudDsl.no;

public final class FieldDsl
{
    private FieldDsl()
    {
        // static utility
    }

    public static StringMatcherBuilder<Field> fieldName()
    {
        return new StringMatcherBuilder<Field>(new RegexMatcherAdapter<Field>()
        {
            @Override
            public String getStringToMatch(final Field toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "FieldName";
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static FreudMatcher<Field> fieldType(final Class type)
    {
        return fieldTypeMatches(Matchers.<Class>equalTo(type));
    }

    public static FreudMatcher<Field> fieldTypeIsSubTypeOf(final Class superType)
    {
        return new FreudMatcher<Field>()
        {
            @Override
            protected boolean matchesSafely(final Field item)
            {
                return superType.isAssignableFrom(item.getType());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("fieldTypeIsSubTypeOf(" + superType.getName() + ")");
            }
        };
    }

    public static FreudMatcher<Field> fieldTypeMatches(final Matcher<? super Class> classMatcher)
    {
        return new FreudMatcher<Field>()
        {
            @Override
            protected boolean matchesSafely(final Field item)
            {
                return classMatcher.matches(item.getType());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("fieldType(" + classMatcher.toString() + ")");
            }
        };
    }

    public static FreudMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType)
    {
        return fieldAnnotation(annotationType, null);
    }

    public static FreudMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType, final Object value)
    {
        return fieldAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudMatcher<Field> fieldAnnotation(final Class<? extends Annotation> annotationType, final Matcher valueMatcher)
    {
        return new AnnotationFreudMatcher<Field>(valueMatcher, annotationType);
    }

    public static FreudMatcher<Field> definedWithModifier(final int modifierMask)
    {
        return new FreudMatcher<Field>()
        {
            @Override
            public final boolean matchesSafely(final Field toBeAnalysed)
            {
                return (toBeAnalysed.getModifiers() & modifierMask) != 0;
            }

            @Override
            public void describeTo(Description description)
            {
                StringBuilder sb = new StringBuilder(Modifier.toString(modifierMask));
                sb.append("Field()");
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                description.appendText(sb.toString());
            }
        };

    }

    public static FreudMatcher<Field> finalField()
    {
        return definedWithModifier(Modifier.FINAL);
    }

    public static FreudMatcher<Field> transientField()
    {
        return definedWithModifier(Modifier.TRANSIENT);
    }

    public static FreudMatcher<Field> volatileField()
    {
        return definedWithModifier(Modifier.VOLATILE);
    }


    public static FreudMatcher<Field> publicField()
    {
        return definedWithModifier(Modifier.PUBLIC);
    }

    public static FreudMatcher<Field> privateField()
    {
        return definedWithModifier(Modifier.PRIVATE);
    }

    public static FreudMatcher<Field> protectedField()
    {
        return definedWithModifier(Modifier.PROTECTED);
    }

    public static FreudMatcher<Field> packageProtectedField()
    {
        return no(privateField().or(publicField().or(protectedField())));
    }

    public static FreudMatcher<Field> staticField()
    {
        return definedWithModifier(Modifier.STATIC);

    }

    public static FreudMatcher<Field> declaredField()
    {
        return new FreudMatcher<Field>()
        {
            @Override
            protected boolean matchesSafely(final Field item)
            {
                return item.getDeclaringClass().equals(FreudRuntimeContext.getCurrentlyAnalysed(Class.class));
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("DeclaredField()");
            }
        };
    }
}
