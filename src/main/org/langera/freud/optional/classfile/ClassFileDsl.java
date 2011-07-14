package org.langera.freud.optional.classfile;

import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;

public final class ClassFileDsl
{


    private ClassFileDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<ClassFile> classSimpleName()
    {
        return new RegexMatcherBuilder<ClassFile>(new RegexMatcherAdapter<ClassFile>()
        {
            @Override
            public String getStringToMatch(final ClassFile toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "ClassFileName";
            }
        });
    }
}
