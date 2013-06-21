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
