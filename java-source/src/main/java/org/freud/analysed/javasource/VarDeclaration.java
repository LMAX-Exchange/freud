package org.freud.analysed.javasource;


public interface VarDeclaration extends AnnotatedElementDeclaration {
    String getType();

    boolean isFinalVariable();

    String getName();
}
