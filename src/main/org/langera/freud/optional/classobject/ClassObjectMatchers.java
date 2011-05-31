package org.langera.freud.optional.classobject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public final class ClassObjectMatchers
{
    private ClassObjectMatchers()
    {
        // static utility
    }

    public static FreudMatcher<Class> classSimpleNameMatches(final String regex)
    {
        return new RegexMatcher<Class>(regex, true, new RegexMatcherAdapter<Class>()
        {
            @Override
            public String getStringToMatch(final Class toBeAnalysed)
            {
                return toBeAnalysed.getSimpleName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "ClassObjectName";
            }
        });
    }

    public static FreudMatcher<Class> subTypeOf(final Class superType)
    {
        return new FreudMatcher<Class>()
        {
            @Override
            protected boolean matchesSafely(final Class item)
            {
                return superType.isAssignableFrom(item);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("subTypeOf(" + superType.getName() + ")");
            }
        };
    }

    public static FreudMatcher<Class> hasDeclaredMethod(final String methodName, final Class... parameterTypes)
    {
        return new FreudMatcher<Class>()
        {
            @Override
            protected boolean matchesSafely(final Class item)
            {
                try
                {
                    item.getDeclaredMethod(methodName, parameterTypes);
                }
                catch (NoSuchMethodException e)
                {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("hasDeclaredMethod(" + methodName + "(" + Arrays.toString(parameterTypes) + ")" + ")");
            }
        };
    }

    public static FreudMatcher<Class> hasDeclaredFieldOfType(final Class fieldType)
    {
        return new FreudMatcher<Class>()
        {
            @Override
            protected boolean matchesSafely(final Class item)
            {
                for (Field field : item.getDeclaredFields())
                {
                    if (fieldType.isAssignableFrom(field.getType()))
                    {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("hasDeclaredFieldOfType(" + fieldType.getName() + ")");
            }
        };
    }

    public static FreudMatcher<Class> classAnnotation(final Class<? extends Annotation> annotationType)
    {
        return classAnnotation(annotationType, (Matcher) null);
    }

    public static FreudMatcher<Class> classAnnotation(final Class<? extends Annotation> annotationType, final Object value)
    {
        return classAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudMatcher<Class> classAnnotation(final Class<? extends Annotation>  annotationType, final Matcher valueMatcher)
    {
        return new AnnotationFreudMatcher<Class>(valueMatcher, annotationType);
    }
}
