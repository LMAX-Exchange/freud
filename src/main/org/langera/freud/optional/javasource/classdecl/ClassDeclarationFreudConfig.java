package org.langera.freud.optional.javasource.classdecl;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.JavaSource;

import java.util.ArrayList;
import java.util.List;

public final class ClassDeclarationFreudConfig implements FreudConfig<ClassDeclaration>
{
    @Override
    public Class<?> supports()
    {
        return JavaSource.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<ClassDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<JavaSource, ClassDeclaration>((AnalysedObjectIterator<JavaSource>) superTypeIterator,
                new SubTypeIteratorBuilder<JavaSource, ClassDeclaration>()
                {
                    @Override
                    public Iterable<ClassDeclaration> buildIterable(final JavaSource superTypeItem)
                    {
                        List<ClassDeclaration> collector = new ArrayList<ClassDeclaration>();
                        final ClassDeclaration classDecl = superTypeItem.getClassDeclaration();
                        collector.add(classDecl);
                        collector.addAll(classDecl.getInnerClassDeclarationByNameMap().values());
                        return collector;
                    }
                }, ClassDeclaration.class);
    }
}
