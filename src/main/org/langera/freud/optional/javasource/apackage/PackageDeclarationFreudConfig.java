package org.langera.freud.optional.javasource.apackage;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.javasource.JavaSource;

import java.util.Collections;

public final class PackageDeclarationFreudConfig implements FreudConfig<PackageDeclaration>
{


    @Override
    public Class<?> supports()
    {
        return JavaSource.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<PackageDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<JavaSource, PackageDeclaration>((AnalysedObjectIterator<JavaSource>) superTypeIterator,
                new SubTypeIteratorBuilder<JavaSource, PackageDeclaration>()
                {
                    @Override
                    public Iterable<PackageDeclaration> buildIterable(final JavaSource superTypeItem)
                    {
                        return Collections.singleton(superTypeItem.getPackageDeclaration());
                    }
                }, PackageDeclaration.class);

    }

}
