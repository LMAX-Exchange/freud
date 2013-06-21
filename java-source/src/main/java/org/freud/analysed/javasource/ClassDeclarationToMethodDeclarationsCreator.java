package org.freud.analysed.javasource;

import org.freud.core.Creator;
import org.freud.core.iterator.FlattenedCollection;

public final class ClassDeclarationToMethodDeclarationsCreator implements Creator<ClassDeclaration,Iterable<MethodDeclaration>> {
    @Override
    public Iterable<MethodDeclaration> create(final ClassDeclaration source) {
        return new FlattenedCollection<MethodDeclaration>(
                source.getMethodDeclarationListByNameMap().values());
    }
}
