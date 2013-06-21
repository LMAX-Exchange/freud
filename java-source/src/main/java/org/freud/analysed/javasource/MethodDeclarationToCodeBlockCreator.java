package org.freud.analysed.javasource;

import org.freud.core.Creator;

public final class MethodDeclarationToCodeBlockCreator implements Creator<MethodDeclaration,CodeBlock> {
    @Override
    public CodeBlock create(final MethodDeclaration source) {
        return source.getImplementation();
    }
}
