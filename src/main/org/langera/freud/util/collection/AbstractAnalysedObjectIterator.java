package org.langera.freud.util.collection;

import org.langera.freud.AnalysisException;
import org.langera.freud.AnalysisListener;
import org.langera.freud.AnalysisUtils;

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
    private boolean alertOnEmptyIterator;
    private AnalysisListener listener = AnalysisUtils.defaultAnalysisListener();

    // can't guarentee generateNextItem will work in construction time
    // so must have a flag and fill lazy
    private transient boolean nextItemFilled = false;
    private transient T nextItem;

    protected AbstractAnalysedObjectIterator(final boolean alertOnEmptyIterator)
    {
        this.alertOnEmptyIterator = alertOnEmptyIterator;
    }

    protected final AnalysisListener getListener()
    {
        return listener;
    }

    public void setListener(AnalysisListener listener)
    {
        this.listener = listener;
    }

    public T current()
    {
        return nextItem;
    }

    public final Iterator<T> iterator()
    {
        return this;
    }

    public final boolean hasNext()
    {
        nextItem = generateNextItem();
        nextItemFilled = true;
        final boolean hasNext = nextItem != null;
        checkForEmptyIteratorAlert(!hasNext);
        return hasNext;
    }

    protected abstract T generateNextItem();

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
    private void checkForEmptyIteratorAlert(boolean empty)
    {
        if (alertOnEmptyIterator && empty)
        {
            listener.unexpected(
                    null, new AnalysisException(
                        "Empty set of analysed objects alert. Make sure the configured iterator does actually iterates over elements."));
        }
        alertOnEmptyIterator = false;        
    }

    public final void remove()
    {
        throw new UnsupportedOperationException("remove not supported");
    }
}
