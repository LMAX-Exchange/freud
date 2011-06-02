package org.langera.examples.property;

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.property.Property;

import static org.langera.freud.optional.property.PropertyDsl.propertyKey;
import static org.langera.freud.optional.property.PropertyDsl.propertyValue;

public final class PropertyExamples
{
    private PropertyExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser propertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValue(final AnalysedObjectIterator<Property> iterator)
    {
        return Freud.iterateOver(Property.class).in(iterator).
                forEach(propertyKey().contains("\\.min").or(propertyKey().contains("\\.max"))).
                assertThat(propertyValue().matches("\\d+"));
    }
}
