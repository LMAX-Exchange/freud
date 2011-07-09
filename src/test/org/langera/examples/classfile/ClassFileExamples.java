package org.langera.examples.classfile;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classfile.ClassFile;

import java.math.BigDecimal;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classfile.ClassFileDsl.hasMethodInvocation;

public final class ClassFileExamples
{
    private ClassFileExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser doNotUseBigDecimalEquals(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFile.class).in(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class)));
    }
}
