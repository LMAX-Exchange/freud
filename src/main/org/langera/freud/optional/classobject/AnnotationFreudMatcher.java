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
