package org.langera.freud;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.util.collection.GenericIterableAnalysedObjectIterator;

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

public final class NestedTypeAnalysisAssertion<Type, NestedType> extends TypeSafeMatcher<Type>
{
    private final Class<NestedType> nestedType;
    private final AnalysisListener analysisListener;
    private final Matcher<NestedType> filter;
    private final Matcher<Type> hierarchicalFilter;
    private final Matcher<NestedType>[] assertions;
    private final NestedTypeAnalysisAdapter<Type, NestedType> nestedTypeAnalysisAssertionAdapter;

    public NestedTypeAnalysisAssertion(final NestedTypeAnalysisAdapter<Type, NestedType> adapter,
                                       final Matcher<NestedType> filter,
                                       final Matcher<Type> hierarchicalFilter,
                                       final Matcher<NestedType>[] assertions,
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

    public final boolean matchesSafely(final Type toBeAnalysed)
    {
        boolean returnValue = true;
        if (hierarchicalFilter.matches(toBeAnalysed))
        {
            final Iterable<NestedType> objectsToAnalyse = nestedTypeAnalysisAssertionAdapter.getNestedObjectsToAnalyse(toBeAnalysed);
            AbstractAnalysis.register(nestedType,
                                      new GenericIterableAnalysedObjectIterator<NestedType>(objectsToAnalyse, false));
            for (NestedType analysed : objectsToAnalyse)
            {
                if (filter.matches(analysed))
                {
                    for (Matcher<NestedType> assertion : assertions)
                    {
                        if (assertion.matches(analysed))
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

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
