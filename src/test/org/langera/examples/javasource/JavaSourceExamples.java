package org.langera.examples.javasource;

import org.hamcrest.Matchers;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.apackage.PackageDeclaration;
import org.langera.freud.optional.javasource.block.CodeBlock;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.apackage.PackageDeclarationDsl.packageDepth;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.codeBlockLines;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.hasMethodCall;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.method;
import static org.langera.freud.optional.javasource.methoddecl.MethodDeclarationDsl.hasDeclaredAnnotation;

public final class JavaSourceExamples
{
    private JavaSourceExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser packageDepthMustBeBetween3And7(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(PackageDeclaration.class).within(iterator).
                assertThat(packageDepth().lessThanOrEqualTo(7).and(
                        packageDepth().greaterThanOrEqualTo(3)));
    }

    public static FreudAnalyser noSystemOutPrintInCode(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).within(iterator).
                assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println"))));
    }

    public static FreudAnalyser codeBlockSizeIsLimitedTo30Lines(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).within(iterator).
                assertThat(codeBlockLines().lessThanOrEqualTo(30));
    }

    public static FreudAnalyser codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).within(iterator).
                forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("\"freud:"))))).
                assertThat(codeBlockLines().lessThanOrEqualTo(1));
    }
}
