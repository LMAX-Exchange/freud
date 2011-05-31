package org.langera.freud.optional.classobject.method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.langera.freud.core.FreudRuntimeContext;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.optional.classobject.AnnotationFreudMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class MethodMatchers
{
    private MethodMatchers()
    {
        // static utility
    }

    public static FreudMatcher<Method> methodNameMatches(final String regex)
    {
        return regexMatcher(regex, true);
    }

    public static FreudMatcher<Method> methodNameContains(final String regex)
    {
        return regexMatcher(regex, false);
    }

    public static FreudMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType)
    {
        return methodAnnotation(annotationType, (Matcher) null);
    }

    public static FreudMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType, final Object value)
    {
        return methodAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType, final Matcher valueMatcher)
    {
        return new AnnotationFreudMatcher<Method>(valueMatcher, annotationType);
    }

    public static FreudMatcher<Method> throwsException(final Class<? extends Exception> exceptionType)
    {
        return new FreudMatcher<Method>()
        {
            @Override
            protected boolean matchesSafely(final Method item)
            {
                for (Class<?> declaredExceptionType : item.getExceptionTypes())
                {
                    if (declaredExceptionType.isAssignableFrom(exceptionType))
                    {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("throwsException(" + exceptionType + ")");
            }
        };
    }

    public static FreudMatcher<Method> definedWithModifier(final int modifierMask)
    {
        return new FreudMatcher<Method>()
        {
            @Override
            public final boolean matchesSafely(final Method toBeAnalysed)
            {
                return (toBeAnalysed.getModifiers() & modifierMask) != 0;
            }

            @Override
            public void describeTo(Description description)
            {
                StringBuilder sb = new StringBuilder(Modifier.toString(modifierMask));
                sb.append("Method()");
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                description.appendText(sb.toString());
            }
        };

    }


    public static FreudMatcher<Method> publicMethod()
    {
        return definedWithModifier(Modifier.PUBLIC);
    }

    public static FreudMatcher<Method> staticMethod()
    {
        return definedWithModifier(Modifier.STATIC);

    }

    public static FreudMatcher<Method> declaredMethod()
    {
        return new FreudMatcher<Method>()
        {
            @Override
            protected boolean matchesSafely(final Method item)
            {
                return item.getDeclaringClass().equals(FreudRuntimeContext.getCurrentlyAnalysed(Class.class));
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("DeclaredMethod()");
            }
        };
    }

    private static FreudMatcher<Method> regexMatcher(final String regex, final boolean completeMatch)
    {
        return new RegexMatcher<Method>(regex, completeMatch, new RegexMatcherAdapter<Method>()
        {
            @Override
            public String getStringToMatch(final Method toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "MethodName";
            }
        });
    }

}
