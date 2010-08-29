// Freud generated code [PropertyAnalysis] [2010-08-29 18:09:34]
package org.langera.freudgenerated.property;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.property.*;
import org.langera.freud.dsl.CommonDsl;
import org.langera.freud.dsl.ReadableDsl;

public class PropertyAnalysis extends AbstractAnalysis<Property, PropertyAnalysis>
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

            public CommonDsl<PropertyAnalysisBuilder, Property> property()
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
