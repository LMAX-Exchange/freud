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
