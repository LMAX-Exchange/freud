package org.langera.freud.optional.javasource.methoddecl;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class MethodDeclarationFreudConfig implements FreudConfig<MethodDeclaration>
{

    @Override
    public Class<?> supports()
    {
        return ClassDeclaration.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<MethodDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<ClassDeclaration, MethodDeclaration>((AnalysedObjectIterator<ClassDeclaration>) superTypeIterator,
                new SubTypeIteratorBuilder<ClassDeclaration, MethodDeclaration>()
                {
                    @Override
                    public Iterable<MethodDeclaration> buildIterable(final ClassDeclaration superTypeItem)
                    {
                        List<MethodDeclaration> collector = new ArrayList<MethodDeclaration>();
                        collectMethodDeclarationsInsideClassDeclaration(superTypeItem, collector);
                        for (ClassDeclaration innerClassDecl : superTypeItem.getInnerClassDeclarationByNameMap().values())
                        {
                            collectMethodDeclarationsInsideClassDeclaration(innerClassDecl, collector);
                        }
                        return collector;
                    }
                }, MethodDeclaration.class);
    }

    private void collectMethodDeclarationsInsideClassDeclaration(final ClassDeclaration classDecl, final List<MethodDeclaration> collector)
    {
        for (List<MethodDeclaration> methodDeclarationList : classDecl.getMethodDeclarationListByNameMap().values())
        {
            collector.addAll(methodDeclarationList);
        }
    }
}
