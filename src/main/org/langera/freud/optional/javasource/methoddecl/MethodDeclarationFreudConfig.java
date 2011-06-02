package org.langera.freud.optional.javasource.methoddecl;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class MethodDeclarationFreudConfig implements FreudConfig<MethodDeclaration>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<MethodDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (JavaSource.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<JavaSource, MethodDeclaration>((AnalysedObjectIterator<JavaSource>) superTypeIterator,
                    new SubTypeIteratorBuilder<JavaSource, MethodDeclaration>()
                    {
                        @Override
                        public Iterable<MethodDeclaration> buildIterable(final JavaSource superTypeItem)
                        {
                            List<MethodDeclaration> collector = new ArrayList<MethodDeclaration>();
                            final ClassDeclaration classDecl = superTypeItem.getClassDeclaration();
                            collectMethodDeclarationsInsideClassDeclaration(classDecl, collector);
                            for (ClassDeclaration innerClassDecl : classDecl.getInnerClassDeclarationByNameMap().values())
                            {
                                collectMethodDeclarationsInsideClassDeclaration(innerClassDecl, collector);
                            }
                            return collector;
                        }
                    }, MethodDeclaration.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over MethodDeclaration objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }

    private void collectMethodDeclarationsInsideClassDeclaration(final ClassDeclaration classDecl, final List<MethodDeclaration> collector)
    {
        for (List<MethodDeclaration> methodDeclarationList : classDecl.getMethodDeclarationListByNameMap().values())
        {
            collector.addAll(methodDeclarationList);
        }
    }
}
