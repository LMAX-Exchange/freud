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

package org.langera.freud.core.iterator;

import org.langera.freud.core.FreudException;
import org.langera.freud.core.listener.AnalysisListener;

public final class SubTypeAnalysedObjectIterator<S, T> extends AbstractAnalysedObjectIterator<T>
{
    private final SubTypeIteratorBuilder<S, T> iteratorBuilder;
    private final AnalysedObjectIterator<S> superTypeIterator;
    private final boolean alertOnEmptyIterator;
    private boolean empty = true;
    private AnalysedObjectIterator<T> currentSubTypeIterator;

    public SubTypeAnalysedObjectIterator(final AnalysedObjectIterator<S> superTypeIterator,
                                         final SubTypeIteratorBuilder<S, T> iteratorBuilder,
                                         final Class<T> type)
    {
        super(type, superTypeIterator.isAlertOnEmptyIterator());
        this.superTypeIterator = superTypeIterator;
        this.iteratorBuilder = iteratorBuilder;
        this.alertOnEmptyIterator = superTypeIterator.isAlertOnEmptyIterator();
    }

    @Override
    public void setListener(final AnalysisListener listener)
    {
        super.setListener(listener);
        superTypeIterator.setListener(listener);
    }

    @Override
    protected T generateNextItem()
    {
        if (currentSubTypeIterator == null)
        {
            return generateNextItemFromNextIterator();
        }
        else
        {
            return generateNextItemFromCurrentIterator();
        }
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    private T generateNextItemFromNextIterator()
    {
        if (superTypeIterator.hasNext())
        {
            currentSubTypeIterator = new IteratorWrapperAnalysedObjectIterator<T>(
                    iteratorBuilder.buildIterable(superTypeIterator.next()), analysedObjectType(), false);
            return generateNextItemFromCurrentIterator();
        }
        else
        {
            if (empty && alertOnEmptyIterator)
            {
                getListener().unexpected(
                        null, new FreudException(
                        "Empty set of analysed objects alert. Make sure the configured iterator does actually iterates over elements."));
            }
            return null;
        }
    }

    private T generateNextItemFromCurrentIterator()
    {
        if (currentSubTypeIterator.hasNext())
        {
            empty = false;
            return currentSubTypeIterator.next();
        }
        else
        {
            currentSubTypeIterator = null;
            return generateNextItemFromNextIterator();
        }
    }
}
