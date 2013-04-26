package org.freud.analysed.javasource;


public interface MethodDeclaration extends AnnotatedElementDeclaration {
    String getReturnType();

    String getName();

    CodeBlock getImplementation();

//    long getModifierMask();

    ParamDeclaration[] getParametersDeclarations();
}
