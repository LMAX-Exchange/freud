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

package org.langera.freud.optional.classobject.field;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public final class FieldFreudConfig implements FreudConfig<Field>
{

    @Override
    public Class<?> supports()
    {
        return Class.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Field> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<Class, Field>((AnalysedObjectIterator<Class>) superTypeIterator,
                new SubTypeIteratorBuilder<Class, Field>()
                {
                    @Override
                    public Iterable<Field> buildIterable(final Class superTypeItem)
                    {
                        final Field[] fields = superTypeItem.getFields();
                        final Field[] declaredFields = superTypeItem.getDeclaredFields();
                        final Set<Field> fieldSet = new HashSet<Field>(fields.length + declaredFields.length);
                        for (int i = 0; i < fields.length; i++)
                        {
                            fieldSet.add(fields[i]);
                        }
                        for (int i = 0; i < declaredFields.length; i++)
                        {
                            fieldSet.add(declaredFields[i]);
                        }
                        return fieldSet;
                    }
                }, Field.class);
    }
}
