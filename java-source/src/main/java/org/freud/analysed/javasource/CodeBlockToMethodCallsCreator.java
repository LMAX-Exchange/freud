package org.freud.analysed.javasource;

import org.freud.core.Creator;

public final class CodeBlockToMethodCallsCreator implements Creator<CodeBlock,Iterable<MethodCall>> {
    @Override
    public Iterable<MethodCall> create(final CodeBlock source) {
        return null;
    }
}
