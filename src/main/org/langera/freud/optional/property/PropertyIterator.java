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

package org.langera.freud.optional.property;

import org.langera.freud.core.iterator.AbstractAnalysedObjectIterator;

import java.util.Enumeration;
import java.util.Properties;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class PropertyIterator extends AbstractAnalysedObjectIterator<Property>
{
    private final Enumeration propertyNamesEnumeration;
    private final Properties properties;

    public PropertyIterator(final Properties properties, final boolean alertOnEmptyIterator)
    {
        super(Property.class, alertOnEmptyIterator);
        this.properties = properties;
        propertyNamesEnumeration = properties.propertyNames();
    }

    @Override
    protected Property generateNextItem()
    {
        return (propertyNamesEnumeration.hasMoreElements()) ?
                createProperty((String) propertyNamesEnumeration.nextElement()) :
                null;
    }

    private Property createProperty(final String key)
    {
        return new Property(key, properties.getProperty(key));
    }
}

