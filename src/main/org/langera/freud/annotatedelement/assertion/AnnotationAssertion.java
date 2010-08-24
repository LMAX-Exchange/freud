package org.langera.freud.annotatedelement.assertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.AnalysisInitialisationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class AnnotationAssertion<T extends AnnotatedElement> extends TypeSafeMatcher<T>
{
    private final Class<? extends Annotation> annotationType;
    private final Matcher annotationValueMatcher;
    private final Method valueGetter;

    public AnnotationAssertion(Class<? extends Annotation> annotationType)
    {
        this(annotationType, null);
    }

    public AnnotationAssertion(Class<? extends Annotation> annotationType, Object annotationValue)
    {
        this(annotationType, Matchers.equalTo(annotationValue));
    }

    public AnnotationAssertion(Class<? extends Annotation> annotationType, Matcher annotationValueMatcher)
    {
        this.annotationType = annotationType;
        this.annotationValueMatcher = annotationValueMatcher;
        if (annotationValueMatcher != null)
        {
            try
            {
                valueGetter = annotationType.getMethod("value");
            }
            catch (NoSuchMethodException e)
            {
                throw new AnalysisInitialisationException("Did not find 'value' in Annotation " + annotationType, e);
            }
        }
        else
        {
            valueGetter = null;
        }
    }

    public final boolean matchesSafely(final T toBeAnalysed)
    {
        Annotation annotation = toBeAnalysed.getAnnotation(annotationType);
        return annotation != null &&
                (annotationValueMatcher == null || annotationValueMatcher.matches(getValue(annotation)));
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

    @Override
    public String toString()
    {
        return "Annotation(" + annotationType +
                (annotationValueMatcher == null ? ")" : "(" + annotationValueMatcher.toString() + "))");
    }

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
