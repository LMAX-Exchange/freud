// Freud generated code [TextAnalysis] [2010-03-21 00:07:44]
package org.langera.freudgenerated.text;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.dsl.CountableDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.text.Text;
import org.langera.freud.text.TextAnalysisBuilder;
import org.langera.freud.text.TextDsl;
import org.langera.freud.text.line.LineAnalysisBuilder;
import org.langera.freud.text.line.LineDsl;
import org.langera.freud.text.line.TextLine;
import org.langera.freud.util.collection.AnalysedObjectIterator;

public class TextAnalysis extends AbstractAnalysis<Text>
        implements
        LineDsl,
        TextDsl,
        Analysis
{
    public TextAnalysis(AnalysedObjectIterator<Text> textAnalysedObjectIterator)
    {
        super(textAnalysedObjectIterator, Text.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
        if (type == Text.class)
        {
            if (nestedType == TextLine.class)
            {
                return TextToTextLineAnalysisAdapter.getInstance();
            }
            return null;
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

    public ReadableDsl<LineAnalysisBuilder> line()
    {

        return new LineAnalysisBuilder().line(
        );
    }

    public CountableDsl<LineAnalysisBuilder> lineLength()
    {

        return new LineAnalysisBuilder().lineLength(
        );
    }

    public CountableDsl<LineAnalysisBuilder> lineNumber()
    {

        return new LineAnalysisBuilder().lineNumber(
        );
    }

    public ReadableDsl<TextAnalysisBuilder> text()
    {

        return new TextAnalysisBuilder().text(
        );
    }


}
