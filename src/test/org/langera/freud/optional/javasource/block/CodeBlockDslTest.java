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

package org.langera.freud.optional.javasource.block;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.hasMethodCall;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.method;
import static org.langera.freud.optional.javasource.methoddecl.MethodDeclarationDsl.methodName;

public final class CodeBlockDslTest
{
    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    "  " +
                    "  public String toString()" +
                    "  {" +
                    "        System.out.println(\"HERE\");\n "+
                    "       return \"\";" +
                    "  }" +
                    "}";
    private CodeBlock toStringMethodCodeBlock;

    @Test
    public void shouldDelegateToMethodDeclarationMatcher()
    {
        Assert.assertThat(toStringMethodCodeBlock, method(methodName().contains("Str")));
    }

    @Test
    public void shouldReturnTrueWhenItIncludesInputMethodCall()
    {
        Assert.assertThat(toStringMethodCodeBlock, hasMethodCall("System.out.println"));
    }

    @Test
    public void shouldReturnFalseWhenItDoesNotIncludeInputMethodCall()
    {
        Assert.assertThat(toStringMethodCodeBlock, no(hasMethodCall("System.out.print")));
    }

    @Test
    public void shouldReturnTrueWhenItReturnsTheRightNumberOfLines()
    {
        Assert.assertThat(toStringMethodCodeBlock, CodeBlockDsl.codeBlockLines().equalTo(2));
    }

    @Test
    public void shouldReturnFalseWhenItReturnsTheWrongNumberOfLines()
    {
        Assert.assertThat(toStringMethodCodeBlock, no(CodeBlockDsl.codeBlockLines().equalTo(17)));
    }

    @Before
    public void setUp() throws Exception
    {
        toStringMethodCodeBlock = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name").
                getClassDeclaration().getMethodDeclarationListByNameMap().get("toString").get(0).getImplementation();
    }
}
