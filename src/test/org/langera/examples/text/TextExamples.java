package org.langera.examples.text;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.text.Text;
import org.langera.freud.optional.text.textline.TextLine;
import org.langera.freud.optional.text.textline.TextLineMatchers;

import static org.langera.freud.optional.text.textline.TextLineMatchers.*;

public final class TextExamples
{
    private TextExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser lineLengthDoesNotExceed(final int value,
                                                   final AnalysedObjectIterator<Text> iterator)
    {
        return Freud.iterateOver(TextLine.class).within(iterator).
                assertThat(lineLength().lessThan(value));
    }

}