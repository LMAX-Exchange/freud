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

package org.langera.freud.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

public final class MultiIterator<E> implements Iterator<E>
{
    private final Iterator<? extends Collection<E>> collectionIterator;
    private Iterator<E> tempIterator;

    public MultiIterator(Collection<? extends Collection<E>> collectionCollection)
    {
        this.collectionIterator = collectionCollection.iterator();
    }

    public boolean hasNext()
    {
        if (tempIterator == null || !tempIterator.hasNext())
        {
            if (collectionIterator.hasNext())
            {
                tempIterator = collectionIterator.next().iterator();
                return hasNext();
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public E next()
    {
        if (tempIterator == null)
        {
            throw new NoSuchElementException();
        }
        return tempIterator.next();
    }

    public void remove()
    {
        if (tempIterator == null)
        {
            throw new IllegalStateException();
        }
        tempIterator.remove();
    }
}
