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

package org.langera.freud.optional.javasource.methoddecl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.methoddecl.MethodDeclarationDsl.hasDeclaredAnnotation;
import static org.langera.freud.optional.javasource.methoddecl.MethodDeclarationDsl.methodName;

public final class MethodDeclarationDslTest
{
    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    "  " +
                    "  @MyAnnotation(\"my-value\")" +
                    "  @MyAnnotation2(key = \"value\")" +
                    "  @Override" +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                    "}";
    private MethodDeclaration toStringDecl;

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(toStringDecl, methodName().matches("toS\\w+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(toStringDecl, no(methodName().matches("Other")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(toStringDecl, methodName().contains("Str"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(toStringDecl, no(methodName().contains("Other")));
    }

    @Test
    public void shouldReturnTrueWhenMethodHasAnnotation()
    {
        Assert.assertThat(toStringDecl, hasDeclaredAnnotation("Override"));
    }

    @Test
    public void shouldReturnFalseWhenMethodDoesNotHaveAnnotation()
    {
        Assert.assertThat(toStringDecl, no(hasDeclaredAnnotation("Other")));
    }

    @Test
    public void shouldReturnTrueWhenMethodHasAnnotationWithDefaultValue()
    {
        Assert.assertThat(toStringDecl, hasDeclaredAnnotation("MyAnnotation", "\"my-value\""));
    }

    @Test
    public void shouldReturnFalseWhenMethodDoesNotHaveAnnotationWithDefaultValue()
    {
        Assert.assertThat(toStringDecl, no(hasDeclaredAnnotation("Other", "\"my-value\"")));
    }


    @Test
    public void shouldReturnTrueWhenMethodHasAnnotationWithKeyAndValue()
    {
        Assert.assertThat(toStringDecl, hasDeclaredAnnotation("MyAnnotation2", "key", "\"value\""));
    }

    @Test
    public void shouldReturnFalseWhenMethodDoesNotHaveAnnotationWithKeyAndValue()
    {
        Assert.assertThat(toStringDecl, no(hasDeclaredAnnotation("MyAnnotation", "key", "value")));
    }

    @Before
    public void setUp() throws Exception
    {
        toStringDecl = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name").
                getClassDeclaration().getMethodDeclarationListByNameMap().get("toString").get(0);
    }
}
