package org.langera.freud.core.iterator;

import org.langera.freud.core.FreudException;
import org.langera.freud.core.listener.AnalysisListener;

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

@SuppressWarnings("unchecked")
public abstract class AbstractAnalysedObjectIterator<T> implements AnalysedObjectIterator<T>
{
    private final Class<T> type;
    private final boolean alertOnEmptyIterator;
    private boolean empty = true;
    private AnalysisListener listener;
    // can't guarentee generateNextItem will work in construction time
    // so must have a flag and fill lazy
    private boolean nextItemFilled = false;
    private T nextItem;


    protected AbstractAnalysedObjectIterator(final Class<T> type, final boolean alertOnEmptyIterator)
    {
        this.type = type;
        this.alertOnEmptyIterator = alertOnEmptyIterator;
    }

    @Override
    public Class<T> analysedObjectType()
    {
        return type;
    }

    @Override
    public void setListener(AnalysisListener listener)
    {
        this.listener = listener;
    }

    @Override
    public boolean isAlertOnEmptyIterator()
    {
        return alertOnEmptyIterator;
    }

    @Override
    public T current()
    {
        return nextItem;
    }

    @Override
    public final Iterator<T> iterator()
    {
        return this;
    }

    @Override
    public final boolean hasNext()
    {
        nextItem = generateNextItem();
        nextItemFilled = true;
        final boolean hasNext = nextItem != null;
        checkForEmptyIteratorAlert(!hasNext);
        return hasNext;
    }

    protected abstract T generateNextItem();

    @Override
    public final T next()
    {
        if (!nextItemFilled)
        {
            hasNext();
        }
        nextItemFilled = false;
        checkForEmptyIteratorAlert(nextItem == null);
        return nextItem;
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    private void checkForEmptyIteratorAlert(boolean noNextItem)
    {
        if (empty && noNextItem)
        {
            listener.unexpected(
                    null, new FreudException(
                        "Empty set of analysed objects alert. Make sure the configured iterator does actually iterates over elements."));
        }
        empty = false;
    }

    @Override
    public final void remove()
    {
        throw new UnsupportedOperationException("remove not supported");
    }

    protected final AnalysisListener getListener()
    {
        return listener;
    }
}
