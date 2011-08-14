package org.langera.freud.optional.classobject.method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.langera.freud.core.FreudRuntimeContext;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;
import org.langera.freud.optional.classobject.AnnotationFreudMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class MethodDsl
{
    private MethodDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<Method> methodName()
    {
        return new RegexMatcherBuilder<Method>(new RegexMatcherAdapter<Method>()
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

    @SuppressWarnings("unchecked")
    public static FreudMatcher<Method> withParams(final Class... params)
    {
        final Matcher<Class>[] matchers = new Matcher[params.length];
        for (int i = 0, size = params.length; i < size; i++)
        {
            matchers[i] = (Matcher<Class>) Matchers.equalTo(params[i]);

        }
        return withParams(matchers);

    }

    public static FreudMatcher<Method> withParams(final Matcher<Class>... params)
    {
        return new FreudMatcher<Method>()
        {
            @Override
            protected boolean matchesSafely(final Method item)
            {
                final Class[] itemParameterTypes = item.getParameterTypes();
                if (params.length != itemParameterTypes.length)
                {
                    return false;
                }
                for (int i = 0, size = params.length; i < size; i++)
                {
                    if (!params[i].matches(itemParameterTypes[i]))
                    {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("withParams(");
                for (int i = 0, size = params.length; i < size; i++)
                {
                    if (i > 0)
                    {
                        description.appendText(", ");
                    }
                    description.appendDescriptionOf(params[i]);
                }
                description.appendText(")");
            }
        };
    }

    public static FreudMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType)
    {
        return methodAnnotation(annotationType, null);
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

    public static FreudMatcher<Method> privateMethod()
    {
        return definedWithModifier(Modifier.PRIVATE);
    }

    public static FreudMatcher<Method> protectedMethod()
    {
        return definedWithModifier(Modifier.PROTECTED);
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
}
