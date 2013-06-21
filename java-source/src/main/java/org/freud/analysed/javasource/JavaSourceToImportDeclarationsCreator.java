package org.freud.analysed.javasource;

import org.freud.core.Creator;

public final class JavaSourceToImportDeclarationsCreator implements Creator<JavaSource,Iterable<ImportDeclaration>> {
    @Override
    public Iterable<ImportDeclaration> create(final JavaSource source) {
        return source.getImportDeclarations();
    }
}
