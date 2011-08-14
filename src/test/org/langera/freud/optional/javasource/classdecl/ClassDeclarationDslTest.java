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

package org.langera.freud.optional.javasource.classdecl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.classdecl.ClassDeclarationDsl.className;
import static org.langera.freud.optional.javasource.classdecl.ClassDeclarationDsl.hasDeclaredMethodNamed;

public final class ClassDeclarationDslTest
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
    private ClassDeclaration classDeclaration;

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(classDeclaration, className().matches("SimpleCla[s]+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(classDeclaration, no(className().matches("Other")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(classDeclaration, className().contains("Simple"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(classDeclaration, no(className().contains("Other")));
    }

    @Test
    public void shouldReturnTrueWhenHasAMethodOfTheInputName()
    {
        Assert.assertThat(classDeclaration, hasDeclaredMethodNamed("toString"));
    }

    @Test
    public void shouldReturnFalseWhenDoesNotHaveAMethodOfTheInputName()
    {
        Assert.assertThat(classDeclaration, no(hasDeclaredMethodNamed("Other")));
    }


    @Before
    public void setUp() throws Exception
    {
        classDeclaration = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name").getClassDeclaration();
    }
}
