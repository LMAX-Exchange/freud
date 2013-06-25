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
        }
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
