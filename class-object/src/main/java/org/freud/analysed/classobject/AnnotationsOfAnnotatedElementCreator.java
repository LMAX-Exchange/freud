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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import static java.util.Arrays.asList;
import static org.freud.analysed.classobject.AnnotationsOfAnnotatedElementCreator.Type.ALL;

public final class AnnotationsOfAnnotatedElementCreator implements Creator<AnnotatedElement, Iterable<Annotation>> {

    public enum Type implements Creator<AnnotatedElement, Iterable<Annotation>> {
        DECLARED {
            @Override
            public Iterable<Annotation> create(final AnnotatedElement source) {
                return asList(source.getDeclaredAnnotations());
            }
        },
        ALL {
            @Override
            public Iterable<Annotation> create(final AnnotatedElement source) {
                return asList(source.getAnnotations());
            }
        }
    }

    private final Type type;

    public AnnotationsOfAnnotatedElementCreator() {
        this(ALL);
    }

    public AnnotationsOfAnnotatedElementCreator(final Type type) {
        this.type = type;
    }

    @Override
    public Iterable<Annotation> create(final AnnotatedElement source) {
        return type.create(source);
    }
}
