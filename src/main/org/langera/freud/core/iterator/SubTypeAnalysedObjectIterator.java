package org.langera.freud.core.iterator;

import org.langera.freud.core.FreudException;

public final class SubTypeAnalysedObjectIterator<S,T> extends AbstractAnalysedObjectIterator<T>
{
    private final SubTypeIteratorBuilder<S,T> iteratorBuilder;
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
