package org.langera.examples.classfile;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.method.ClassFileMethod;

import java.math.BigDecimal;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classfile.method.MethodDsl.hasMethodInvocation;
import static org.langera.freud.optional.classfile.method.MethodDsl.methodInvokedWithParams;
import static org.langera.freud.optional.classfile.method.MethodDsl.variableOfType;

public final class ClassFileExamples
{
    private ClassFileExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser doNotUseBigDecimalToString(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "toString")).
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", variableOfType(BigDecimal.class)))));
    }

    public static FreudAnalyser doNotUseBigDecimalEquals(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class)));
    }

}

