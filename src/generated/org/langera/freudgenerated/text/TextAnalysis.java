// Freud generated code [TextAnalysis] [2010-08-24 21:26:16]
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
        ReadableDsl<LineAnalysisBuilder> builder = new LineAnalysisBuilder().line(
        );
        return builder;
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineLength()
    {
        NumericOperatorDsl<LineAnalysisBuilder> builder = new LineAnalysisBuilder().lineLength(
        );
        return builder;
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineNumber()
    {
        NumericOperatorDsl<LineAnalysisBuilder> builder = new LineAnalysisBuilder().lineNumber(
        );
        return builder;
    }

    public ReadableDsl<TextAnalysisBuilder> text()
    {
        ReadableDsl<TextAnalysisBuilder> builder = new TextAnalysisBuilder().text(
        );
        return builder;
    }


}
