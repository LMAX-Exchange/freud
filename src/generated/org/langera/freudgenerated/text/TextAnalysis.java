// Freud generated code [TextAnalysis] [2010-08-25 22:33:47]
package org.langera.freudgenerated.text;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.text.Text;
import org.langera.freud.text.TextAnalysisBuilder;
import org.langera.freud.text.TextDsl;
import org.langera.freud.text.line.LineAnalysisBuilder;
import org.langera.freud.text.line.LineDsl;
import org.langera.freud.text.line.TextLine;
import org.langera.freud.util.collection.AnalysedObjectIterator;

public class TextAnalysis extends AbstractAnalysis<Text, TextAnalysis>
        implements
        TextDsl,
        LineDsl,
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

    public ReadableDsl<TextAnalysisBuilder> text()
    {
        return new TextAnalysisBuilder().text(
        );
    }

    public ReadableDsl<LineAnalysisBuilder> line()
    {
        return new LineAnalysisBuilder().line(
        );
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineLength()
    {
        return new LineAnalysisBuilder().lineLength(
        );
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineNumber()
    {
        return new LineAnalysisBuilder().lineNumber(
        );
    }


}
