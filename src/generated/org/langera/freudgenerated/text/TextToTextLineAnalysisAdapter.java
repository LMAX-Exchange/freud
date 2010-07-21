// Freud generated code 2010-07-21 21:22:44
package org.langera.freudgenerated.text;

import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.text.Text;
import org.langera.freud.text.line.TextLine;

public final class TextToTextLineAnalysisAdapter implements NestedTypeAnalysisAdapter<Text, TextLine>
{
    private static final TextToTextLineAnalysisAdapter SINGLETON = new TextToTextLineAnalysisAdapter();

    private TextToTextLineAnalysisAdapter()
    {
        // singleton
    }

    public static TextToTextLineAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<TextLine> getNestedObjectsToAnalyse(Text toBeAnalysed)
    {
        return toBeAnalysed.getTextLines();
    }
}