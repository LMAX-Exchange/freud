// Freud generated code 2010-08-24 21:26:16
package org.langera.freudgenerated.javasource;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.javasource.classdecl.ClassDeclaration;
import org.langera.freud.javasource.methoddecl.MethodDeclaration;
import org.langera.freud.util.collection.MultiCollection;

public final class ClassDeclarationToMethodDeclarationAnalysisAdapter implements NestedTypeAnalysisAdapter<ClassDeclaration, MethodDeclaration>
{
    private static final ClassDeclarationToMethodDeclarationAnalysisAdapter SINGLETON = new ClassDeclarationToMethodDeclarationAnalysisAdapter();

    private ClassDeclarationToMethodDeclarationAnalysisAdapter()
    {
        // singleton
    }

    public static ClassDeclarationToMethodDeclarationAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<MethodDeclaration> getNestedObjectsToAnalyse(ClassDeclaration toBeAnalysed)
    {
        return new MultiCollection<MethodDeclaration>(toBeAnalysed.getMethodDeclarationListByNameMap().values());
    }
}