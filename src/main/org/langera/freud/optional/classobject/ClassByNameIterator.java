package org.langera.freud.optional.classobject;

import org.langera.freud.core.iterator.AbstractAnalysedObjectIterator;

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

public final class ClassByNameIterator extends AbstractAnalysedObjectIterator<Class>
{
    private final String[] classNames;
    private transient int ptr = 0;

    public ClassByNameIterator(final boolean alertOnEmptyIterator, final String... classNames)
    {
        super(Class.class, alertOnEmptyIterator);
        this.classNames = classNames;
    }


    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Override
    protected Class generateNextItem()
    {
        while (ptr < classNames.length)
        {
            try
            {
                return Class.forName(classNames[ptr++]);
            }
            catch (ClassNotFoundException e)
            {
                getListener().unexpected(null, new IllegalArgumentException("class name [" + classNames[ptr - 1] + "] not found", e));
            }
        }
        return null;
    }
}
