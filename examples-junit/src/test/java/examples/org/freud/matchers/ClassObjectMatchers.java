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
import org.freud.java.matcher.IntOperatorMatcherAdapter;
import org.freud.java.matcher.IntOperatorMatcherBuilder;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public final class ClassObjectMatchers {
    private ClassObjectMatchers() {
        // static utility
    }


    public static StringMatcherBuilder<Class> className() {
        return new StringMatcherBuilder<Class>(new RegexMatcherAdapter<Class>() {
            @Override
            public String getStringToMatch(final Class toBeAnalysed) {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName() {
                return "ClassObjectName";
            }
        });
    }

    public static StringMatcherBuilder<Class> classSimpleName() {
        return new StringMatcherBuilder<Class>(new RegexMatcherAdapter<Class>() {
            @Override
            public String getStringToMatch(final Class toBeAnalysed) {
                return toBeAnalysed.getSimpleName();
            }

            @Override
            public String matcherDisplayName() {
                return "ClassObjectSimpleName";
            }
        });

    }

    public static StringMatcherBuilder<Class> packageName() {
        return new StringMatcherBuilder<Class>(new RegexMatcherAdapter<Class>() {
            @Override
            public String getStringToMatch(final Class toBeAnalysed) {
                return toBeAnalysed.getPackage().getName();
            }

            @Override
            public String matcherDisplayName() {
                return "PackageName";
            }
        });
    }

    public static FreudExtendedMatcher<Class> array() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isArray();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("array");
            }
        };
    }


    public static FreudExtendedMatcher<Class> anEnum() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isEnum();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("enum");
            }
        };
    }

    public static FreudExtendedMatcher<Class> anInterface() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isInterface();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("interface");
            }
        };
    }

    public static FreudExtendedMatcher<Class> anonymous() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isAnonymousClass();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("anonymous");
            }
        };
    }

    public static FreudExtendedMatcher<Class> localClass() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isLocalClass();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("localClass");
            }
        };
    }

    public static FreudExtendedMatcher<Class> memberClass() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isMemberClass();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("memberClass");
            }
        };
    }

    public static FreudExtendedMatcher<Class> primitive() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isPrimitive();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("primitive");
            }
        };
    }

    public static FreudExtendedMatcher<Class> synthetic() {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return item.isSynthetic();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("synthetic");
            }
        };
    }

    public static FreudExtendedMatcher<Class> abstractClass() {
        return byModifierMatcher(Modifier.ABSTRACT);
    }

    public static FreudExtendedMatcher<Class> finalClass() {
        return byModifierMatcher(Modifier.FINAL);
    }

    public static FreudExtendedMatcher<Class> privateClass() {
        return byModifierMatcher(Modifier.PRIVATE);
    }

    public static FreudExtendedMatcher<Class> protectedClass() {
        return byModifierMatcher(Modifier.PROTECTED);
    }

    public static FreudExtendedMatcher<Class> publicClass() {
        return byModifierMatcher(Modifier.PUBLIC);
    }

    public static FreudExtendedMatcher<Class> staticClass() {
        return byModifierMatcher(Modifier.STATIC);
    }

    public static FreudExtendedMatcher<Class> subTypeOf(final Class superType) {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return superType.isAssignableFrom(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("subTypeOf(" + superType.getName() + ")");
            }
        };
    }

    public static IntOperatorMatcherBuilder<Class> numberOfConstructors() {
        return new IntOperatorMatcherBuilder<Class>(new IntOperatorMatcherAdapter<Class>() {
            @Override
            public int valueOf(final Class matched) {
                return matched.getConstructors().length;
            }

            @Override
            public String matcherDisplayName() {
                return "numberOfConstructors()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<Class> numberOfConstructorWithModifier(final int modifierMask) {
        return new IntOperatorMatcherBuilder<Class>(new IntOperatorMatcherAdapter<Class>() {
            @Override
            public int valueOf(final Class item) {
                int counter = 0;
                for (Constructor constructor : item.getConstructors()) {
                    if ((constructor.getModifiers() & modifierMask) != 0) {
                        counter++;
                    }
                }
                return counter;
            }

            @Override
            public String matcherDisplayName() {
                return "numberOfConstructorsWithModifier(" + modifierMask + ")";
            }
        });
    }

    public static IntOperatorMatcherBuilder<Class> numberOfDeclaredMethods() {
        return new IntOperatorMatcherBuilder<Class>(new IntOperatorMatcherAdapter<Class>() {
            @Override
            public int valueOf(final Class matched) {
                return matched.getDeclaredMethods().length;
            }

            @Override
            public String matcherDisplayName() {
                return "numbderOfDeclaredMethods()";
            }
        });
    }

    public static FreudExtendedMatcher<Class> hasDefaultConstructor() {
        return hasConstructor();
    }


    public static FreudExtendedMatcher<Class> hasConstructor(final Class... parameterTypes) {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                try {
                    item.getConstructor(parameterTypes);
                }
                catch (NoSuchMethodException e) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("hasConstructor(" + Arrays.toString(parameterTypes) + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Class> hasConstructorWithModifier(final int modifierMask) {
        return numberOfConstructorWithModifier(modifierMask).greaterThanOrEqualTo(1);
    }

    public static FreudExtendedMatcher<Class> hasDeclaredMethod(final String methodName, final Class... parameterTypes) {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                try {
                    item.getDeclaredMethod(methodName, parameterTypes);
                }
                catch (NoSuchMethodException e) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("hasDeclaredMethod(" + methodName + "(" + Arrays.toString(parameterTypes) + ")" + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Class> hasDeclaredFieldOfType(final Class fieldType) {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                for (Field field : item.getDeclaredFields()) {
                    if (fieldType.isAssignableFrom(field.getType())) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("hasDeclaredFieldOfType(" + fieldType.getName() + ")");
            }
        };
    }

    public static FreudExtendedMatcher<Class> classAnnotation(final Class<? extends Annotation> annotationType) {
        return classAnnotation(annotationType, null);
    }

    public static FreudExtendedMatcher<Class> classAnnotation(final Class<? extends Annotation> annotationType, final Object value) {
        return classAnnotation(annotationType, Matchers.equalTo(value));
    }

    public static FreudExtendedMatcher<Class> classAnnotation(final Class<? extends Annotation> annotationType, final Matcher valueMatcher) {
        return new AnnotationFreudExtendedMatcher<Class>(valueMatcher, annotationType);
    }

    private static FreudExtendedMatcher<Class> byModifierMatcher(final int modifierMask) {
        return new FreudExtendedMatcher<Class>() {
            @Override
            protected boolean matchesSafely(final Class item) {
                return (item.getModifiers() & modifierMask) != 0;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("byModifierMask(" + modifierMask + ")");
            }
        };
    }
}
