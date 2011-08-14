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

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.text.textline.TextLineDsl.line;

public final class TextLineDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), line().matches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(line().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), line().contains("\\d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(line().contains("a")));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), TextLineDsl.lineLength().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineLength()
    {
        Assert.assertThat(new TextLine("12345678901234567", 19, ""), no(TextLineDsl.lineLength().equalTo(13)));
    }

    @Test
    public void shouldReturnTrueToAMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), TextLineDsl.lineNumber().equalTo(17));
    }

    @Test
    public void shouldReturnFalseToANonMatchedLineNumber()
    {
        Assert.assertThat(new TextLine("12345678901234567", 17, ""), no(TextLineDsl.lineNumber().equalTo(13)));
    }

}
