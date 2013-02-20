package org.freud.analysed.classobject;

import org.freud.core.Creator;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.freud.analysed.classobject.FieldsOfClassCreator.Type.ALL;

public final class FieldsOfClassCreator implements Creator<Class, Iterable<Field>> {

    public enum Type implements Creator<Class, Iterable<Field>> {
        DECLARED {
            @Override
            public Iterable<Field> create(final Class source) {
                return asList(source.getDeclaredFields());
            }
        },
        PUBLIC {
            @Override
            public Iterable<Field> create(final Class source) {
                return asList(source.getFields());
            }
        },
        ALL {
            @Override
            public Iterable<Field> create(final Class source) {
                Set<Field> returnValue = new HashSet<Field>();
                for (Field field : DECLARED.create(source)) {
                    returnValue.add(field);
                }
                for (Field field : PUBLIC.create(source)) {
                    returnValue.add(field);
                }
                return returnValue;
            }
        };
    }

    private final Type type;

    public FieldsOfClassCreator() {
        this(ALL);
    }

    public FieldsOfClassCreator(final Type type) {
        this.type = type;
    }

    @Override
    public Iterable<Field> create(final Class source) {
        return type.create(source);
    }
}
