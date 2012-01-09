package org.langera.freud.optional.javasource.vardecl;

import org.langera.freud.optional.javasource.annotation.AnnotatedElementDeclaration;

public interface VarDeclaration extends AnnotatedElementDeclaration
{
    String getType();

    boolean isFinalVariable();

    String getName();
}
