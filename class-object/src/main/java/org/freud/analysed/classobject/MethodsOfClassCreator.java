package org.freud.analysed.classobject;

import org.freud.core.Creator;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.freud.analysed.classobject.MethodsOfClassCreator.Type.ALL;

public final class MethodsOfClassCreator implements Creator<Class, Iterable<Method>> {

    public enum Type implements Creator<Class, Iterable<Method>> {
        DECLARED {
            @Override
            public Iterable<Method> create(final Class source) {
                return asList(source.getDeclaredMethods());
            }
        },
        PUBLIC {
            @Override
            public Iterable<Method> create(final Class source) {
                return asList(source.getMethods());
            }
        },
        ALL {
            @Override
            public Iterable<Method> create(final Class source) {
                Set<Method> returnValue = new HashSet<Method>();
                for (Method method : DECLARED.create(source)) {
                    returnValue.add(method);
                }
                for (Method method : PUBLIC.create(source)) {
                    returnValue.add(method);
                }
                return returnValue;
            }
        }
    }

    private final Type type;

    public MethodsOfClassCreator() {
        this(ALL);
    }

    public MethodsOfClassCreator(final Type type) {
        this.type = type;
    }

    @Override
    public Iterable<Method> create(final Class source) {
        return type.create(source);
    }
}
