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

package org.langera.freud.analysis;

import org.langera.freud.core.Freud;
import org.langera.freud.core.SingleFreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.ClassObjectDsl.abstractClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.className;

public final class StyleAnalysers
{
    public static SingleFreudAnalyser<Class> abstractClassNamePattern(final AnalysedObjectIterator<Class> iterator, final String regexPattern)
    {
        return Freud.iterateOver(Class.class).within(iterator).
                singleAssertThat(no(abstractClass()).or(className().matches(regexPattern)));
    }
}
