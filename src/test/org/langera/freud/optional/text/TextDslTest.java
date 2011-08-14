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

package org.langera.freud.optional.text;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.text.TextDsl.text;

public final class TextDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), text().matches("\\d+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), no(text().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), text().contains("\\d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(new Text("12345678901234567", ""), no(text().contains("a")));
    }
}
