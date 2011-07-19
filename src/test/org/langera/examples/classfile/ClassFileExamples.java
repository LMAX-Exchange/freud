package org.langera.examples.classfile;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.iterator.resource.ResourceParserException;
import org.langera.freud.core.listener.PrintAnalysisListener;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.parser.InnerClassFileResourceIdentifierGetter;
import org.langera.freud.optional.classfile.parser.asm.AsmClassFileParser;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.a;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.hasMethodInvocation;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.methodInvokedWithParams;

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
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", a(BigDecimal.class)))));
    }

    public static FreudAnalyser doNotUseBigDecimalEquals(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class)));
    }


    //////// TEMP

    public static void main(String[] args) throws IOException, ResourceParserException
    {
        final AnalysedObjectIterator<ClassFile> iterator = ResourceIterators.filesByPathResourceIterator(
                new AsmClassFileParser(new InnerClassFileResourceIdentifierGetter()
                {
                    @Override
                    public String getResourceIdentifier(final String name, final ClassFile currentClassFile, final String currentResourceIdentifier)
                    {
                        int indexOfClassesDir = currentResourceIdentifier.indexOf("classes/");
                        return currentResourceIdentifier.substring(0, indexOfClassesDir + "classes/".length()) + name + ".class";
                    }
                }),
                new FilenameFilter()
                {
                    @Override
                    public boolean accept(final File dir, final String name)
                    {
                        return name.endsWith(".class");
                    }
//                }, true, "../../java/classes");
                }, true, "../trunk/build/classes/");
//                }, true, "build");

        final FreudAnalyser analyser = doNotUseBigDecimalEquals(iterator);

        analyser.analyse(new PrintAnalysisListener(new PrintWriter(System.out)));
    }

}

