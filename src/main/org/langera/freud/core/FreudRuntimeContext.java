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

package org.langera.freud.core;

import org.langera.freud.core.iterator.AnalysedObjectIterator;

import java.util.HashMap;
import java.util.Map;

public final class FreudRuntimeContext
{
    private FreudRuntimeContext()
    {
        // static utility
    }

    private static final ThreadLocal<Map<Class, AnalysedObjectIterator>> CONTEXT =
            new ThreadLocal<Map<Class, AnalysedObjectIterator>>()
            {
                @Override
                protected Map<Class, AnalysedObjectIterator> initialValue()
                {
                    return new HashMap<Class, AnalysedObjectIterator>();
                }
            };

    public static void clear()
    {
        CONTEXT.get().clear();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getCurrentlyAnalysed(Class<T> type)
    {
        final AnalysedObjectIterator analysedObjectIterator = CONTEXT.get().get(type);
        return (T) ((analysedObjectIterator == null) ? null : analysedObjectIterator.current());
    }

    static <T> void register(AnalysedObjectIterator<T> iterator)
    {
        CONTEXT.get().put(iterator.analysedObjectType(), iterator);
    }
}
