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

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.block.CodeBlock;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MethodDeclarationJdomTest
{

    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return this.getClass();" +
                    "  }" +
                    "}";
    private Element methodDecl;

    @Test
    public void shouldParseMethodImplementation() throws Exception
    {
        MethodDeclarationJdom methodDeclarationJdom = new MethodDeclarationJdom(methodDecl, null);

        assertEquals("toString", methodDeclarationJdom.getName());
        assertEquals("String", methodDeclarationJdom.getReturnType());

        final CodeBlock impl = methodDeclarationJdom.getImplementation();
        assertNotNull(impl);
        assertEquals(1, impl.getMethodCallListByMethodName("getClass").size());
    }

    @Before
    public void setUp() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

        methodDecl = (Element) javaSourceJdom.getDocument().
                getDescendants(new ElementFilter(JavaSourceTokenType.FUNCTION_METHOD_DECL.getName())).next();
    }

}
