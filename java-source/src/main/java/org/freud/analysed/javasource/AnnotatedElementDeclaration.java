package org.freud.analysed.javasource;

import java.util.List;

public interface AnnotatedElementDeclaration {
    List<Annotation> getDeclaredAnnotations();
}
