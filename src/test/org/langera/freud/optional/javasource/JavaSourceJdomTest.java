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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.importdecl.ImportDeclaration;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JavaSourceJdomTest
{

    private static final String CLASS_EXAMPLE =
                    "package org.langera.examples;" +
                    "import org.langera.something.*;" +
                    "import static org.langera.something.Or.Other;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                            "}";

    private JavaSourceJdom javaSourceJdom;

    @Before
    public void setUp() throws Exception
    {
        javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

//        System.out.println(javaSourceJdom);
    }

    @Test
    public void shouldCreateJavaSourceJdom() throws Exception
    {

        assertEquals("Name", javaSourceJdom.getFileName());
        assertEquals("SimpleClass", javaSourceJdom.getClassDeclaration().getName());
    }

    @Test
    public void shouldReturnFullClassName() throws Exception
    {
        assertEquals("org.langera.examples.SimpleClass", javaSourceJdom.getFullClassName());
    }

    @Test
    public void shouldReturnPackageDeclaration() throws Exception
    {
        assertEquals("org.langera.examples", javaSourceJdom.getPackageDeclaration().getPackagePathAsString());
    }

    @Test
    public void shouldReturnImportDeclarations() throws Exception
    {
        final ImportDeclaration[] importDeclarations = javaSourceJdom.getImportDeclarations();
        assertEquals(2, importDeclarations.length);
        assertThat(importDeclarations[0], importOf("org.langera.something.*"));
        assertThat(importDeclarations[1], staticImportOf("org.langera.something.Or.Other"));
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

        assertEquals("SimpleClass", javaSourceJdom.getFullClassName());
    }


    @Test
    public void shouldParseJavaSource() throws Exception
    {
        JavaSourceJdom javaSource = new JavaSourceJdom(new StringReader(
                "package org.langera.test;" +
                        " " +
                        "public class TestShouldParseJavaSource " +
                        "{ " +
                        "    public boolean equals(final Object o) " +
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

        System.out.println(javaSource);

        assertEquals("org.langera.test.TestShouldParseJavaSource", javaSource.getFileName());

    }


    private Matcher<ImportDeclaration> staticImportOf(final String importStmt)
    {
        return new ImportDeclarationMatcher(importStmt, true);
    }

    private Matcher<ImportDeclaration> importOf(final String importStmt)
    {
        return new ImportDeclarationMatcher(importStmt, false);
    }

    private static class ImportDeclarationMatcher extends TypeSafeMatcher<ImportDeclaration>
    {
        private final String expectedImportAsString;
        private final boolean expectStaticImport;

        private ImportDeclarationMatcher(final String expectedImportAsString, final boolean expectStaticImport)
        {
            this.expectedImportAsString = expectedImportAsString;
            this.expectStaticImport = expectStaticImport;
        }

        @Override
        protected boolean matchesSafely(final ImportDeclaration importDeclaration)
        {
            return importDeclaration.isStaticDeclaration() == expectStaticImport &&
                    expectedImportAsString.equals(importDeclaration.getImportDeclarationPathAsString());
        }

        @Override
        public void describeTo(final Description description)
        {
            description.appendText("import ");
            if (expectStaticImport)
            {
                description.appendText("static ");
            }
            description.appendText(expectedImportAsString);
        }
    }
}
