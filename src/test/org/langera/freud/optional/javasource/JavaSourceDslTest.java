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

package org.langera.freud.optional.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.JavaSourceDsl.fullClassName;
import static org.langera.freud.optional.javasource.JavaSourceDsl.simpleClassName;

public final class JavaSourceDslTest
{
    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                    "}";
    private JavaSource javaSource;

    @Test
    public void shouldReturnTrueToAMatchedRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, fullClassName().matches("org.+SimpleClass"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, no(fullClassName().matches("com.+SimpleClass")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, fullClassName().contains("org"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, no(fullClassName().contains("com")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, simpleClassName().matches("SimpleClass"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, no(simpleClassName().matches("SimpleClassX")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, simpleClassName().contains("Simple"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, no(simpleClassName().contains("Other")));
    }

    @Before
    public void setUp() throws Exception
    {
        javaSource = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");
    }
}
