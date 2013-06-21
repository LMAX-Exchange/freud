package org.freud.analysed.javasource;

import org.freud.core.Creator;
import org.freud.core.iterator.FlattenedCollection;

public final class CodeBlockToMethodCallsCreator implements Creator<CodeBlock,Iterable<MethodCall>> {
    @Override
    public Iterable<MethodCall> create(final CodeBlock source) {
        return new FlattenedCollection<MethodCall>(source.getMethodCallByMethodNameMap().values());
    }
}
