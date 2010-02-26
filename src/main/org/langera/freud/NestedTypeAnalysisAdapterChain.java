package org.langera.freud;

import org.langera.freud.util.collection.AnalysedObjectIterator;
import org.langera.freud.util.collection.GenericIterableAnalysedObjectIterator;

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

public final class NestedTypeAnalysisAdapterChain<Type, Intermediate, NestedType>
                implements NestedTypeAnalysisAdapter<Type, NestedType>
{
    private NestedTypeAnalysisAdapter<Type, Intermediate> firstAdapter;
    private NestedTypeAnalysisAdapter<Intermediate, NestedType> secondAdapter;

    @SuppressWarnings("unchecked")
    public static NestedTypeAnalysisAdapter createChain(NestedTypeAnalysisAdapter... adapters)
    {
        int ptr = 0;
        NestedTypeAnalysisAdapter adapter = adapters[ptr++];
        for (; ptr < adapters.length; ptr++)
        {
            adapter = new NestedTypeAnalysisAdapterChain(adapter, adapters[ptr]);
        }
        return adapter;
    }

    private NestedTypeAnalysisAdapterChain(final NestedTypeAnalysisAdapter<Type, Intermediate> firstAdapter, 
                                          final NestedTypeAnalysisAdapter<Intermediate, NestedType> secondAdapter)
    {
        this.firstAdapter = firstAdapter;
        this.secondAdapter = secondAdapter;
    }

    public Iterable<NestedType> getNestedObjectsToAnalyse(Type toBeAnalysed)
    {
        return new AdapterIterator<Type, Intermediate, NestedType>(toBeAnalysed, firstAdapter, secondAdapter);
    }

    private static final class AdapterIterator<Type, Intermediate, NestedType>
                    implements Iterable<NestedType>, Iterator<NestedType>
    {
        private final AnalysedObjectIterator<Intermediate> intermediatesToAnalyseIterator;
        private final NestedTypeAnalysisAdapter<Intermediate, NestedType> nestedTypeAnalysisAssertionAdapter;
        private Iterator<NestedType> nestedTypeToAnalyseIteraor;
        private NestedType nextItem;

        @SuppressWarnings("unchecked")
        public AdapterIterator(final Type toBeAnalysed,
                               final NestedTypeAnalysisAdapter<Type, Intermediate> firstAdapter,
                               final NestedTypeAnalysisAdapter<Intermediate, NestedType> secondAdapter)
        {
            this.nestedTypeAnalysisAssertionAdapter = secondAdapter;
            intermediatesToAnalyseIterator = new GenericIterableAnalysedObjectIterator(firstAdapter.getNestedObjectsToAnalyse(toBeAnalysed), false);
            if (intermediatesToAnalyseIterator.hasNext())
            {
                final Intermediate intermediate = intermediatesToAnalyseIterator.next();
                AbstractAnalysis.register((Class<Intermediate>)intermediate.getClass(), intermediatesToAnalyseIterator);
                nestedTypeToAnalyseIteraor = secondAdapter.getNestedObjectsToAnalyse(intermediate).iterator();
                nextItem = generateNextItem();
            }
        }

        public Iterator<NestedType> iterator()
        {
            return this;
        }

        public boolean hasNext()
        {
            return nextItem != null;
        }

        public NestedType next()
        {
            NestedType toReturn = nextItem;
            nextItem = generateNextItem();
            return toReturn;
        }

        private NestedType generateNextItem()
        {
            if (nestedTypeToAnalyseIteraor.hasNext())
            {
                return nestedTypeToAnalyseIteraor.next();
            }
            else if (intermediatesToAnalyseIterator.hasNext())
            {
                nestedTypeToAnalyseIteraor = nestedTypeAnalysisAssertionAdapter.getNestedObjectsToAnalyse(
                            intermediatesToAnalyseIterator.next()).iterator();
                return generateNextItem();
            }
            return null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove not supported");
        }
                
    }
}
