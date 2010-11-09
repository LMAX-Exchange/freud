// Freud generated code [BinaryContentAnalysis] [2010-11-09 14:03:56]
package org.langera.freudgenerated.binary;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.binary.BinaryContent;
import org.langera.freud.binary.BinaryContentAnalysisBuilder;
import org.langera.freud.binary.BinaryContentDsl;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

public class BinaryContentAnalysis extends AbstractAnalysis<BinaryContent, BinaryContentAnalysis>
        implements
                    BinaryContentDsl,
                    Analysis
{
    public BinaryContentAnalysis(AnalysedObjectIterator<BinaryContent> binarycontentAnalysedObjectIterator)
    {
        super(binarycontentAnalysedObjectIterator, BinaryContent.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
                return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

            public CommonDsl<BinaryContentAnalysisBuilder, BinaryContent> binaryContent()
        {
            return new BinaryContentAnalysisBuilder().binaryContent(
                        );
        }
        
    
}
