/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

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
