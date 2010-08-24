package org.langera.freud;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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

public final class NestedTypeFilterAnalysisAssertion<Type, NestedType> extends TypeSafeMatcher<Type>
{
    private final Matcher<NestedType> assertion;
    private final NestedTypeAnalysisAdapter<Type, NestedType> nestedTypeAnalysisAssertionAdapter;

    public NestedTypeFilterAnalysisAssertion(final NestedTypeAnalysisAdapter<Type, NestedType> nestedTypeAnalysisAssertionAdapter,
                                             final Matcher<NestedType> assertion
    )
    {
        this.assertion = assertion;
        this.nestedTypeAnalysisAssertionAdapter = nestedTypeAnalysisAssertionAdapter;
    }

    public final boolean matchesSafely(final Type toBeAnalysed)
    {
        boolean returnValue = true;
        for (NestedType analysed : nestedTypeAnalysisAssertionAdapter.getNestedObjectsToAnalyse(toBeAnalysed))
        {
            if (!assertion.matches(analysed))
            {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
