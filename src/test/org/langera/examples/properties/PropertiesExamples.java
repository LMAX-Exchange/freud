package org.langera.examples.properties;

import org.langera.freud.Analysis;
import org.langera.freud.property.Property;
import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freudgenerated.property.PropertyAnalysis;

public final class PropertiesExamples
{
    private PropertiesExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static Analysis propertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValue(final AnalysedObjectIterator<Property> iterator)
    {
        return new PropertyAnalysis(iterator)
        {
            {
                forEach(propertyKey().contains("\\.min").or(propertyKey().contains("\\.max")));
                assertThat(propertyValue().matches("\\d+"));
            }
        };
    }
}