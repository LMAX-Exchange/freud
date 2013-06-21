package org.freud.analysed.javasource;


import java.util.List;

public interface MethodDeclaration extends AnnotatedElementDeclaration {
    String getReturnType();

    String getName();

    CodeBlock getImplementation();

//    long getModifierMask();

    List<ParamDeclaration> getParametersDeclarations();
}
