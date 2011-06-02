package org.langera.freud.optional.javasource.block;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;
import org.langera.freud.optional.javasource.methoddecl.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class CodeBlockFreudConfig implements FreudConfig<CodeBlock>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CodeBlock> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (JavaSource.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<JavaSource, CodeBlock>((AnalysedObjectIterator<JavaSource>) superTypeIterator,
                    new SubTypeIteratorBuilder<JavaSource, CodeBlock>()
                    {
                        @Override
                        public Iterable<CodeBlock> buildIterable(final JavaSource superTypeItem)
                        {
                            List<CodeBlock> collector = new ArrayList<CodeBlock>();
                            final ClassDeclaration classDecl = superTypeItem.getClassDeclaration();
                            collectCodeBlocksInsideClassDeclaration(classDecl, collector);
                            for (ClassDeclaration innerClassDecl : classDecl.getInnerClassDeclarationByNameMap().values())
                            {
                                collectCodeBlocksInsideClassDeclaration(innerClassDecl, collector);
                            }
                            return collector;
                        }
                    }, CodeBlock.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over CodeBlock objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }

    private void collectCodeBlocksInsideClassDeclaration(final ClassDeclaration classDecl, final List<CodeBlock> collector)
    {
        for (List<MethodDeclaration> methodDeclarationList : classDecl.getMethodDeclarationListByNameMap().values())
        {
            for (MethodDeclaration methodDeclaration : methodDeclarationList)
            {
                collector.add(methodDeclaration.getImplementation());
            }
        }
    }
}
