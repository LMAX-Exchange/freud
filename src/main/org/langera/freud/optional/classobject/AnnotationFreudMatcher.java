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

package org.langera.freud.optional.classobject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.matcher.FreudMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationFreudMatcher<T extends AnnotatedElement> extends FreudMatcher<T>
{
    private final Method valueGetter;
    private final Matcher valueMatcher;
    private final Class<? extends Annotation> annotationType;

    public AnnotationFreudMatcher(final Matcher valueMatcher, final Class<? extends Annotation> annotationType)
    {
        this.valueMatcher = valueMatcher;
        this.annotationType = annotationType;
        if (valueMatcher != null)
        {
            try
            {
                valueGetter = annotationType.getMethod("value");
            }
            catch (NoSuchMethodException e)
            {
                throw new FreudBuilderException("Did not find 'value' in Annotation " + annotationType, e);
            }
        }
        else
        {
            valueGetter = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean matchesSafely(final AnnotatedElement item)
    {
        Annotation annotation = item.getAnnotation(annotationType);
        return annotation != null &&
                (valueMatcher == null || valueMatcher.matches(getValue(annotation)));
    }

    @Override
    public void describeTo(final Description description)
    {
        description.appendText("Annotation(" + annotationType +
                (valueMatcher == null ? ")" : "(value = " + valueMatcher.toString() + "))"));
    }

    private Object getValue(Annotation annotation)
    {
        try
        {
            return valueGetter.invoke(annotation);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException("Failed to get value for annotation " + annotation, e);
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException("Failed to get value for annotation " + annotation, e.getTargetException());
        }
    }
}
