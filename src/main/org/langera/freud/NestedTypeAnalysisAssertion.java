package org.langera.freud;

import org.langera.freud.util.collection.GenericIterableAnalysedObjectIterator;

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

public final class NestedTypeAnalysisAssertion<Type, NestedType> implements AnalysisAssertion<Type>
{
    private final Class<NestedType> nestedType;
    private final AnalysisListener analysisListener;
    private final AnalysisAssertion<NestedType> filter;
    private final AnalysisAssertion<Type> hierarchicalFilter;
    private final AnalysisAssertion<NestedType>[] assertions;
    private final NestedTypeAnalysisAdapter<Type, NestedType> nestedTypeAnalysisAssertionAdapter;

    public NestedTypeAnalysisAssertion(final NestedTypeAnalysisAdapter<Type, NestedType> adapter,
                                       final AnalysisAssertion<NestedType> filter,
                                       final AnalysisAssertion<Type> hierarchicalFilter,
                                       final AnalysisAssertion<NestedType>[] assertions,
                                       Class<NestedType> nestedType, final AnalysisListener analysisListener
    )
    {
        this.filter = filter;
        this.hierarchicalFilter = hierarchicalFilter;
        this.assertions = assertions;
        this.nestedType = nestedType;
        this.analysisListener = analysisListener;
        this.nestedTypeAnalysisAssertionAdapter = adapter;
    }

    public boolean analyse(Type toBeAnalysed)
    {
        boolean returnValue = true;
        if (hierarchicalFilter.analyse(toBeAnalysed))
        {
            final Iterable<NestedType> objectsToAnalyse = nestedTypeAnalysisAssertionAdapter.getNestedObjectsToAnalyse(toBeAnalysed);
            AbstractAnalysis.register(nestedType,
                    new GenericIterableAnalysedObjectIterator<NestedType>(objectsToAnalyse, false));
            for (NestedType analysed : objectsToAnalyse)
            {
                if (filter.analyse(analysed))
                {
                    for (AnalysisAssertion<NestedType> assertion : assertions)
                    {
                        if (assertion.analyse(analysed))
                        {
                            analysisListener.passed(analysed, assertion);
                        }
                        else
                        {
                            analysisListener.failed(analysed, assertion);
                            returnValue = false;
                        }
                    }
                }
                else
                {
                    analysisListener.filtered(analysed, filter);
                }
            }
        }
        else
        {
            analysisListener.filtered(toBeAnalysed, hierarchicalFilter);
        }
        return returnValue;
    }
}
