package org.langera.freud.util.regex;

import org.langera.freud.AnalysisAssertion;

import java.util.regex.Pattern;

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

public final class RegexMatchAnalysisAssertion<Type> implements AnalysisAssertion<Type>
{
    private final Pattern regex;
    private final boolean completeMatch;
    private final RegexMatchAnalysisAssertionAdapter<Type> adapter;

    public RegexMatchAnalysisAssertion(final String regex, final boolean completeMatch,
                                          final RegexMatchAnalysisAssertionAdapter<Type> adapter)
    {
        this.adapter = adapter;
        this.completeMatch = completeMatch;
        this.regex = Pattern.compile(regex);
    }

    public final boolean analyse(final Type toBeAnalysed)
    {
        final String stringToMatch = adapter.getStringToMatch(toBeAnalysed);
        return (completeMatch) ? regex.matcher(stringToMatch).matches() :
                regex.matcher(stringToMatch).find();
    }

    @Override
    public String toString()
    {
        return adapter.assertionDisplayName() + ((completeMatch) ? "Match" : "Contains") +
                "(" + regex.pattern() + ")";
    }
}
