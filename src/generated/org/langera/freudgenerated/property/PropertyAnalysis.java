// Freud generated code [PropertyAnalysis] [2010-02-14 19:03:24]
package org.langera.freudgenerated.property;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.property.*;
import org.langera.freud.dsl.ReadableDsl;

public class PropertyAnalysis extends AbstractAnalysis<Property>
        implements
                    PropertyDsl,
                    Analysis
{
    public PropertyAnalysis(AnalysedObjectIterator<Property> propertyAnalysedObjectIterator)
    {
        super(propertyAnalysedObjectIterator, Property.class);
    }

    protected NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, Class nestedType)
    {
                return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /// DSL

            public ReadableDsl<PropertyAnalysisBuilder> property()
        {

            return new PropertyAnalysisBuilder().property(
                        );
        }
        
            public ReadableDsl<PropertyAnalysisBuilder> propertyKey()
        {

            return new PropertyAnalysisBuilder().propertyKey(
                        );
        }
        
            public ReadableDsl<PropertyAnalysisBuilder> propertyValue()
        {

            return new PropertyAnalysisBuilder().propertyValue(
                        );
        }
        
    
}
