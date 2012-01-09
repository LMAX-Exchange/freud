package org.langera.freud.optional.javasource.methoddecl;

import org.jdom.Element;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.annotation.Annotation;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

public final class ParamDeclarationJdom implements ParamDeclaration
{
    private final Element paramDecl;
    private final String type;
    private final boolean finalVariable;
    private final boolean varArgs;
    private final String name;
    private Annotation[] annotations;

    public ParamDeclarationJdom(final Element paramDecl)
    {
        this.paramDecl = paramDecl;
        this.type = JavaSourceJdom.parseType(paramDecl);
        this.name = JavaSourceJdom.parseName(paramDecl);
        this.varArgs = JavaSourceTokenType.FORMAL_PARAM_VARARG_DECL.getName().equals(paramDecl.getName());
        this.finalVariable = paramDecl.getChild(JavaSourceTokenType.LOCAL_MODIFIER_LIST.getName()).
                                        getChild(JavaSourceTokenType.FINAL.getName()) != null;
    }

    @Override
    public boolean isVarArgsParam()
    {
        return varArgs;
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
        if (annotations == null)
        {
            annotations = JavaSourceJdom.parseAnnotations(paramDecl);
        }
        return annotations;

    }
}
