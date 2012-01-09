package org.langera.freud.optional.javasource.vardecl;

import org.langera.freud.optional.javasource.annotation.Annotation;

import javax.xml.bind.Element;

public final class VarDeclarationJdom implements VarDeclaration
{
    private final String type;
    private final boolean finalVariable;
    private final String name;

    public VarDeclarationJdom(Element varDeclElement)
    {
        type = "";
        name = "";
        finalVariable = false;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public boolean isFinalVariable()
    {
        return finalVariable;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Annotation[] getDeclaredAnnotations()
    {

 // TODO
       return new Annotation[0];
    }
}
