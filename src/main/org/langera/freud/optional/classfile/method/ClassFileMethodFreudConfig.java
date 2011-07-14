package org.langera.freud.optional.classfile.method;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.classfile.ClassFile;

public final class ClassFileMethodFreudConfig implements FreudConfig<ClassFileMethod>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<ClassFileMethod> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<ClassFile, ClassFileMethod>((AnalysedObjectIterator<ClassFile>) superTypeIterator,
                new SubTypeIteratorBuilder<ClassFile, ClassFileMethod>()
                {
                    @Override
                    public Iterable<ClassFileMethod> buildIterable(final ClassFile superTypeItem)
                    {
                        return superTypeItem.getMethodList();
                    }
                }, ClassFileMethod.class);

    }

    @Override
    public Class<?> supports()
    {
        return ClassFile.class;
    }
}
