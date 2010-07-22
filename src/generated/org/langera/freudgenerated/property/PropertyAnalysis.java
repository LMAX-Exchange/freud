// Freud generated code [PropertyAnalysis] [2010-07-22 08:46:00]
package org.langera.freudgenerated.property;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.property.Property;
import org.langera.freud.property.PropertyAnalysisBuilder;
import org.langera.freud.property.PropertyDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

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
