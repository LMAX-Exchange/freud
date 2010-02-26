package org.langera.freud.javasource.method;

import org.langera.freud.javasource.annotation.Annotation;
import org.langera.freud.javasource.block.CodeBlock;

public class MethodDeclarationStub implements MethodDeclaration
{
    private final Annotation[] annotations;
    private final String name;
    private final CodeBlock codeBlock;

    public MethodDeclarationStub(String name, Annotation[] annotations, CodeBlock codeBlock)
    {
        this.name = name;
        this.annotations = annotations;
        this.codeBlock = codeBlock;
    }

    public Annotation[] getDeclaredMethodAnnotations()
    {
        return annotations;
    }

    public String getName()
    {
        return name;
    }

    public CodeBlock getImplementation()
    {
        return codeBlock;
    }
}