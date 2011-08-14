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

package org.langera.freud.core.matcher;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.and;
import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.core.matcher.FreudDsl.or;

public final class FreudDslTest
{
    @Test
    public void shouldReturnTrueWhenOriginalMatchesFailed()
    {
        Assert.assertTrue(no(Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenOriginalMatchesSucceeded()
    {
        Assert.assertFalse(no(Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenFirstMatchSucceeded()
    {
        Assert.assertTrue(or(Matchers.startsWith("a"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenOnlyFirstMatchSucceeded()
    {
        Assert.assertFalse(and(Matchers.startsWith("a"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenBothMatchesSucceededOnOr()
    {
        Assert.assertTrue(or(Matchers.startsWith("a"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenBothMatchesSucceededOnAnd()
    {
        Assert.assertTrue(and(Matchers.startsWith("a"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenBothMatchesFailedOnOr()
    {
        Assert.assertFalse(or(Matchers.startsWith("b"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseeWhenBothMatchesFailedOnAnd()
    {
        Assert.assertFalse(and(Matchers.startsWith("b"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenSecondMatchesSucceeded()
    {
        Assert.assertTrue(or(Matchers.startsWith("b"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenSecondMatchesSucceeded()
    {
        Assert.assertFalse(and(Matchers.startsWith("b"), Matchers.startsWith("a")).matches("aa"));
    }
}
