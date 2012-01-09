package org.langera.freud.optional.javasource.methoddecl;

import org.langera.freud.optional.javasource.vardecl.VarDeclaration;

public interface ParamDeclaration extends VarDeclaration
{
    boolean isVarArgsParam();
}
