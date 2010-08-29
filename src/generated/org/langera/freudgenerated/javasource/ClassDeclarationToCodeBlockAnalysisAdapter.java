// Freud generated code 2010-08-29 18:09:33
package org.langera.freudgenerated.javasource;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.javasource.classdecl.ClassDeclaration;
import org.langera.freud.javasource.block.CodeBlock;
import org.langera.freud.util.collection.MultiCollection;    
import java.util.ArrayList;    
import java.util.Collection;    
import org.langera.freud.javasource.methoddecl.MethodDeclaration;    

public final class ClassDeclarationToCodeBlockAnalysisAdapter implements NestedTypeAnalysisAdapter<ClassDeclaration, CodeBlock>
{
    private static final ClassDeclarationToCodeBlockAnalysisAdapter SINGLETON = new ClassDeclarationToCodeBlockAnalysisAdapter();

    private ClassDeclarationToCodeBlockAnalysisAdapter()
    {
        // singleton
    }

    public static ClassDeclarationToCodeBlockAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<CodeBlock> getNestedObjectsToAnalyse(ClassDeclaration toBeAnalysed)
    {
        Collection<MethodDeclaration> methodDeclarationCollection =new MultiCollection<MethodDeclaration>(toBeAnalysed.getMethodDeclarationListByNameMap().values());Collection<CodeBlock> codeBlockCollection = new ArrayList<CodeBlock>(methodDeclarationCollection.size());for (MethodDeclaration methodDeclaration : methodDeclarationCollection){final CodeBlock implementation = methodDeclaration.getImplementation();if (implementation != null){codeBlockCollection.add(implementation);}}return codeBlockCollection;
    }
}