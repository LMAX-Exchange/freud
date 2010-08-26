package org.langera.examples.javasource;

import org.hamcrest.Matchers;
import org.langera.freud.Analysis;
import org.langera.freud.javasource.JavaSource;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.javasource.JavaSourceAnalysis;

public final class JavaSourceExamples
{
    private JavaSourceExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static Analysis packageDepthMustBeBetween3And7(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return new JavaSourceAnalysis(iterator)
        {
            {
                assertThat(packageDepth().lessThanOrEqualTo(7).and(
                        packageDepth().greaterThanOrEqualTo(3)));
            }
        };
    }

    public static Analysis noSystemOutPrintInCode(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return new JavaSourceAnalysis(iterator)
        {
            {
                assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println"))));
            }
        };
    }

    public static Analysis codeBlockSizeIsLimitedTo30Lines(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return new JavaSourceAnalysis(iterator)
        {
            {
                assertThat(codeBlockLines().lessThanOrEqualTo(30));
            }
        };
    }

    public static Analysis codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return new JavaSourceAnalysis(iterator)
        {
            {
                forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("\"freud:")))));
                assertThat(numberOf(codeBlockLines()).lessThanOrEqualTo(1));
            }
        };
    }
}
