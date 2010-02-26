// Freud generated code [TextAnalysis] [2010-02-14 19:03:24]
package org.langera.freudgenerated.text;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.text.*;
import org.langera.freud.text.line.*;
import org.langera.freud.dsl.CountableDsl;
import org.langera.freud.dsl.ReadableDsl;

public class TextAnalysis extends AbstractAnalysis<Text>
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
        
    
}
