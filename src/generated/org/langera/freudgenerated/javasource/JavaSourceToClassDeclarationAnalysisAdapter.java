// Freud generated code 2010-08-25 22:33:46
package org.langera.freudgenerated.javasource;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.javasource.JavaSource;
import org.langera.freud.javasource.classdecl.ClassDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class JavaSourceToClassDeclarationAnalysisAdapter implements NestedTypeAnalysisAdapter<JavaSource, ClassDeclaration>
{
    private static final JavaSourceToClassDeclarationAnalysisAdapter SINGLETON = new JavaSourceToClassDeclarationAnalysisAdapter();

    private JavaSourceToClassDeclarationAnalysisAdapter()
    {
        // singleton
    }

    public static JavaSourceToClassDeclarationAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<ClassDeclaration> getNestedObjectsToAnalyse(JavaSource toBeAnalysed)
    {
        final ClassDeclaration classDeclaration = toBeAnalysed.getClassDeclaration();
        final Collection<ClassDeclaration> innerClassDeclarations = classDeclaration.getInnerClassDeclarationByNameMap().values();
        List<ClassDeclaration> iterable = new ArrayList<ClassDeclaration>(innerClassDeclarations.size() + 1);
        iterable.add(classDeclaration);
        iterable.addAll(innerClassDeclarations);
        return (innerClassDeclarations.isEmpty()) ? Collections.singleton(classDeclaration) : iterable;
    }
}