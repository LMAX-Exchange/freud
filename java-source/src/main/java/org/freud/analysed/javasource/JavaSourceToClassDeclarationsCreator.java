package org.freud.analysed.javasource;

import org.freud.core.Creator;

import static java.util.Collections.singleton;

public final class JavaSourceToClassDeclarationsCreator implements Creator<JavaSource,Iterable<ClassDeclaration>> {
    @Override
    public Iterable<ClassDeclaration> create(final JavaSource source) {
        // TODO add internal classes
        return singleton(source.getClassDeclaration());
    }
}
