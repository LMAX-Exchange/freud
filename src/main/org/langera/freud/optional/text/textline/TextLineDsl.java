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

package org.langera.freud.optional.text.textline;

import org.langera.freud.core.matcher.IntOperatorMatcherAdapter;
import org.langera.freud.core.matcher.IntOperatorMatcherBuilder;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;

public final class TextLineDsl
{
    private TextLineDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<TextLine> line()
    {
        return new RegexMatcherBuilder<TextLine>(new RegexMatcherAdapter<TextLine>()
        {
            @Override
            public String getStringToMatch(final TextLine toBeAnalysed)
            {
                return toBeAnalysed.getLine();
            }

            @Override
            public String matcherDisplayName()
            {
                return "TextLine";
            }
        });
    }

    public static IntOperatorMatcherBuilder<TextLine> lineLength()
    {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>()
        {
            @Override
            public int valueOf(final TextLine matched)
            {
                return matched.getLine().length();
            }

            @Override
            public String matcherDisplayName()
            {
                return "LineLength()";
            }
        });
    }

    public static IntOperatorMatcherBuilder<TextLine> lineNumber()
    {
        return new IntOperatorMatcherBuilder<TextLine>(new IntOperatorMatcherAdapter<TextLine>()
        {
            @Override
            public int valueOf(final TextLine matched)
            {
                return matched.getLineNumber();
            }

            @Override
            public String matcherDisplayName()
            {
                return "LineNumber()";
            }
        });
    }
}
