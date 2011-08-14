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

package org.langera.freud.optional.javasource.annotation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.langera.freud.core.matcher.FreudMatcher;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public final class AnnotatedElementDeclarationMatchers<T extends AnnotatedElementDeclaration>
{

    public static <T extends AnnotatedElementDeclaration> AnnotatedElementDeclarationMatchers<T> getInstance()
    {
        return new AnnotatedElementDeclarationMatchers<T>();
    }

    private AnnotatedElementDeclarationMatchers()
    {
        // static utility - internal to all annotated element declarations
    }

    public FreudMatcher<T> hasDeclaredAnnotation(final String annotationName)
    {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) null, (Matcher<String>) null);
    }

    @SuppressWarnings("unchecked")
    public FreudMatcher<T> hasDeclaredAnnotation(final String annotationName, final String defaultValue)
    {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) equalTo(defaultValue));
    }

    @SuppressWarnings("unchecked")
    public FreudMatcher<T> hasDeclaredAnnotation(final String annotationName, final String key, final String value)
    {
        return hasDeclaredAnnotation(annotationName, (Matcher<String>) equalTo(key), (Matcher<String>) equalTo(value));
    }

    public FreudMatcher<T> hasDeclaredAnnotation(final String annotationName,
                                                 final Matcher<String> defaultValueMatcher)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final AnnotatedElementDeclaration toBeAnalysed)
            {
                for (Annotation declaredAnnotation : toBeAnalysed.getDeclaredAnnotations())
                {
                    if (annotationName.equals(declaredAnnotation.getName()))
                    {
                        return defaultValueMatcher.matches(declaredAnnotation.getDefaultParameter());
                    }
                }
                return false;

            }

            @Override
            public String toString()
            {
                Description description = new StringDescription();
                description.appendText("HasDeclaredAnnotation(").appendText(annotationName);
                defaultValueMatcher.describeTo(description);
                description.appendText(")");
                return description.toString();
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText(toString());
            }
        };
    }

    public FreudMatcher<T> hasDeclaredAnnotation(final String annotationName,
                                                 final Matcher<String> keyMatcher,
                                                 final Matcher<String> valueMatcher)
    {
        return new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final AnnotatedElementDeclaration toBeAnalysed)
            {
                for (Annotation declaredAnnotation : toBeAnalysed.getDeclaredAnnotations())
                {
                    if (annotationName.equals(declaredAnnotation.getName()))
                    {
                        if (keyMatcher != null || valueMatcher != null)
                        {
                            for (Map.Entry<String, String> entry : declaredAnnotation.getParameterMap().entrySet())
                            {
                                if (keyMatcher == null || keyMatcher.matches(entry.getKey()))
                                {
                                    if (valueMatcher == null || valueMatcher.matches(entry.getValue()))
                                    {
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
            public String toString()
            {
                Description description = new StringDescription();
                description.appendText("HasDeclaredAnnotation(").appendText(annotationName);
                description.appendText(")");
                return description.toString();
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText(toString());
            }
        };
    }

}
