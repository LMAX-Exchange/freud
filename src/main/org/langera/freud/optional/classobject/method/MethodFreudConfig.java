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

package org.langera.freud.optional.classobject.method;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public final class MethodFreudConfig implements FreudConfig<Method>
{

    @Override
    public Class<?> supports()
    {
        return Class.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<Method> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<Class, Method>((AnalysedObjectIterator<Class>) superTypeIterator,
                new SubTypeIteratorBuilder<Class, Method>()
                {
                    @Override
                    public Iterable<Method> buildIterable(final Class superTypeItem)
                    {
                        final Method[] methods = superTypeItem.getMethods();
                        final Method[] declaredMethods = superTypeItem.getDeclaredMethods();
                        final Set<Method> methodSet = new HashSet<Method>(methods.length + declaredMethods.length);
                        for (int i = 0; i < methods.length; i++)
                        {
                            methodSet.add(methods[i]);
                        }
                        for (int i = 0; i < declaredMethods.length; i++)
                        {
                            methodSet.add(declaredMethods[i]);
                        }
                        return methodSet;
                    }
                }, Method.class);
    }
}
