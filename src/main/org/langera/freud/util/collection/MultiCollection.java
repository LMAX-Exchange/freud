package org.langera.freud.util.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class MultiCollection<E> extends AbstractCollection<E>
{
    private Collection<? extends Collection<E>> collectionCollection;

    public MultiCollection(Collection<? extends Collection<E>> collectionCollection)
    {
        this.collectionCollection = collectionCollection;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new MultiIterator<E>(collectionCollection);
    }

    @Override
    public int size()
    {
        int sz = 0;
        for (Collection<E> collection : collectionCollection)
        {
            sz += collection.size();
        }
        return sz;
    }
}
