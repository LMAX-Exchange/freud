package org.langera.freud.optional.classfile.method;

import org.langera.freud.optional.classfile.method.instruction.Label;

public final class LocalVariable
{
    private final String name;
    private final String desc;
    private final String signature;
    private final Label scopeStart;
    private final Label scopeEnd;

    public LocalVariable(final String name, final String desc, final String signature, final Label scopeStart, final Label scopeEnd)
    {
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.scopeStart = scopeStart;
        this.scopeEnd = scopeEnd;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getName()
    {
        return name;
    }

    public Label getScopeEnd()
    {
        return scopeEnd;
    }

    public Label getScopeStart()
    {
        return scopeStart;
    }

    public String getSignature()
    {
        return signature;
    }
}
