package examples.org.freud.matchers;

import org.freud.core.iterator.AnalysedObjectBreadcrumbs;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS;

public final class MethodMatchers {
    private MethodMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<Method> methodName() {
        return new StringMatcherBuilder<Method>(new RegexMatcherAdapter<Method>() {
            @Override
            public String getStringToMatch(final Method toBeAnalysed) {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName() {
                return "MethodName";
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static FreudExtendedMatcher<Method> withParams(final Class... params) {
        final Matcher<Class>[] matchers = new Matcher[params.length];
        for (int i = 0, size = params.length; i < size; i++) {
            matchers[i] = (Matcher<Class>) Matchers.equalTo(params[i]);

        }
        return withParams(matchers);

    }

    public static FreudExtendedMatcher<Method> withParams(final Matcher<Class>... params) {
        return new FreudExtendedMatcher<Method>() {
            @Override
            protected boolean matchesSafely(final Method item) {
                final Class[] itemParameterTypes = item.getParameterTypes();
                if (params.length != itemParameterTypes.length) {
                    return false;
                }
                for (int i = 0, size = params.length; i < size; i++) {
                    if (!params[i].matches(itemParameterTypes[i])) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("withParams(");
                for (int i = 0, size = params.length; i < size; i++) {
                    if (i > 0) {
                        description.appendText(", ");
                    }
                    description.appendDescriptionOf(params[i]);
                }
                description.appendText(")");
            }
        };
    }

    public static IntOperatorMatcherBuilder<Method> numberOfParams() {
        return new IntOperatorMatcherBuilder<Method>(new IntOperatorMatcherAdapter<Method>() {
            @Override
            public int valueOf(final Method matched) {
                return matched.getParameterTypes().length;
            }

            @Override
            public String matcherDisplayName() {
                return "numberOfParams()";
            }
        });
    }

    public static FreudExtendedMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType) {
        return methodAnnotation(annotationType, null);
    }

    public static FreudExtendedMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType, final Object value) {
        return methodAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudExtendedMatcher<Method> methodAnnotation(final Class<? extends Annotation> annotationType, final Matcher valueMatcher) {
        return new AnnotationFreudExtendedMatcher<Method>(valueMatcher, annotationType);
    }

    public static FreudExtendedMatcher<Method> throwsException(final Class<? extends Exception> exceptionType) {
        return new FreudExtendedMatcher<Method>() {
            @Override
            protected boolean matchesSafely(final Method item) {
                for (Class<?> declaredExceptionType : item.getExceptionTypes()) {
                    if (declaredExceptionType.isAssignableFrom(exceptionType)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("throwsException(" + exceptionType + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Method> definedWithModifier(final int modifierMask) {
        return new FreudExtendedMatcher<Method>() {
            @Override
            public final boolean matchesSafely(final Method toBeAnalysed) {
                return (toBeAnalysed.getModifiers() & modifierMask) != 0;
            }

            @Override
            public void describeTo(Description description) {
                StringBuilder sb = new StringBuilder(Modifier.toString(modifierMask));
                sb.append("Method()");
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                description.appendText(sb.toString());
            }
        };

    }


    public static FreudExtendedMatcher<Method> publicMethod() {
        return definedWithModifier(Modifier.PUBLIC);
    }

    public static FreudExtendedMatcher<Method> privateMethod() {
        return definedWithModifier(Modifier.PRIVATE);
    }

    public static FreudExtendedMatcher<Method> protectedMethod() {
        return definedWithModifier(Modifier.PROTECTED);
    }

    public static FreudExtendedMatcher<Method> staticMethod() {
        return definedWithModifier(Modifier.STATIC);

    }

    public static FreudExtendedMatcher<Method> abstractMethod() {
        return definedWithModifier(Modifier.ABSTRACT);

    }


    public static FreudExtendedMatcher<Method> declaredMethod() {
        return new FreudExtendedMatcher<Method>() {
            @Override
            protected boolean matchesSafely(final Method item) {
                final Class declaringClass = BREADCRUMBS.get(Class.class);
                if (declaringClass == null) {
                    throw new IllegalStateException("Cannot execute 'declaredMethod()' matcher - iterator does not support breadcrumbs");
                }
                return item.getDeclaringClass().equals(declaringClass);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("DeclaredMethod()");
            }
        };
    }

}
