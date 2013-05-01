package org.freud

import spock.lang.Specification
import spock.lang.Unroll

import static org.freud.analysed.javasource.JavaSourceDsl.javaSourceOf
import static org.freud.analysed.javasource.JavaSourceDsl.packageDeclarationsWithin
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.resourcesOf

class JavaSourceExamplesSpock extends Specification {

    @Unroll
    def 'package depth must be between 3 and 7'() {
    expect:
        analyse(analysed) { it.packagePath.length >= 3 && it.packagePath.length <= 7 }
    where:
        analysed << packageDeclarationsWithin(javaSourceOf(resourcesOf(['javasource/second/third/ExampleClass.javasrc'])))
    }

/*
    public static FreudAnalyser packageDepthMustBeBetween3And7(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(PackageDeclaration.class).
                assertThat(packageDepth().lessThanOrEqualTo(7).and(
                        packageDepth().greaterThanOrEqualTo(3))).within(iterator);
    }

    public static FreudAnalyser noSystemOutPrintInCode(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println")))).within(iterator);
    }

    public static FreudAnalyser codeBlockSizeIsLimitedTo30Lines(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                assertThat(codeBlockLines().lessThanOrEqualTo(30)).within(iterator);
    }

    public static FreudAnalyser codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("\"freud:"))))).
                assertThat(codeBlockLines().lessThanOrEqualTo(1)).within(iterator);
    }
  */
}
