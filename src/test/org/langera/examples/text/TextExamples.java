package org.langera.examples.text;

import org.langera.freud.Analysis;
import org.langera.freud.text.Text;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.text.TextAnalysis;

public final class TextExamples
{
    private TextExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static Analysis lineLengthDoesNotExceed(final int value,
                                                   final AnalysedObjectIterator<Text> iterator)
    {
        return new TextAnalysis(iterator)
        {
            {
                assertThat(lineLength().lessThanOrEqualTo(value));
            }
        };
    }

}