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
import org.junit.Test;

import java.io.StringReader;

public class JavaSourceJdomTest
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

    @Test
    public void shouldCreateJavaSourceJdom() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

        Assert.assertEquals("Name", javaSourceJdom.getFileName());
        Assert.assertEquals("SimpleClass", javaSourceJdom.getClassDeclaration().getName());
    }

    @Test
    public void shouldReturnFullClassName() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

        Assert.assertEquals("org.langera.examples.SimpleClass", javaSourceJdom.getFullClassName());
    }

    @Test
    public void shouldReturnClassNameWhenNoPackageExists() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(
                "public class SimpleClass " +
                        "{ " +
                        " " +
                        "  public String toString()" +
                        "  {" +
                        "       return \"\";" +
                        "  }" +
                        "}"), "Name");

        Assert.assertEquals("SimpleClass", javaSourceJdom.getFullClassName());
    }


    @Test
    public void shouldParseJavaSource() throws Exception
    {
        JavaSourceJdom javaSource = new JavaSourceJdom(new StringReader(
                "package org.langera.test;" +
                        " " +
                        "public class TestShouldParseJavaSource " +
                        "{ " +
                        "    public boolean equals(Object o) " +
                        "    { " +
                        " System.out.print(17);" +
                        "        if (this == o) return true; " +
                        "        if (o == null || getClass() != o.getClass()) return false; " +
                        " " +
                        "        return true; " +
                        "    } " +
                        " " +
                        "    public int hashCode() " +
                        "    { " +
                        "        return 17; " +
                        "    } " +
                        "}"), "org.langera.test.TestShouldParseJavaSource");

        Assert.assertEquals("org.langera.test.TestShouldParseJavaSource", javaSource.getFileName());

    }
}
