// Freud generated code [TextAnalysis] [2010-08-29 18:09:34]
package org.langera.freudgenerated.text;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.text.line.*;
import org.langera.freud.text.*;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;

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

            public CommonDsl<LineAnalysisBuilder, TextLine> line()
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
        
                public CommonDsl<TextAnalysisBuilder, Text> text()
        {
            return new TextAnalysisBuilder().text(
                        );
        }
        
    
}
