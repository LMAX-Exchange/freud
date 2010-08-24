// Freud generated code [PropertyAnalysis] [2010-08-24 21:26:16]
package org.langera.freudgenerated.property;

import org.langera.freud.AbstractAnalysis;
import org.langera.freud.Analysis;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.property.Property;
import org.langera.freud.property.PropertyAnalysisBuilder;
import org.langera.freud.property.PropertyDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

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

    public ReadableDsl<PropertyAnalysisBuilder> property()
    {
        ReadableDsl<PropertyAnalysisBuilder> builder = new PropertyAnalysisBuilder().property(
        );
        return builder;
    }

    public ReadableDsl<PropertyAnalysisBuilder> propertyKey()
    {
        ReadableDsl<PropertyAnalysisBuilder> builder = new PropertyAnalysisBuilder().propertyKey(
        );
        return builder;
    }

    public ReadableDsl<PropertyAnalysisBuilder> propertyValue()
    {
        ReadableDsl<PropertyAnalysisBuilder> builder = new PropertyAnalysisBuilder().propertyValue(
        );
        return builder;
    }


}
