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

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSourceJdom;
import org.langera.freud.optional.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;
import java.util.Iterator;

public class CodeBlockJdomTest 
{

    private static final String CLASS_EXAMPLE =
                "package org.langera.examples;\n" +
                        " \n" +
                        "public class SimpleClass \n" +
                        "{ \n" +
                        " \n" +
                        "  public String toString()\n" +
                        "  {\n" +
                        "       int a1 = 1;\n" +
                        "       int a2 = 1;\n" +
                        "       int a3 = 1;\n" +
                        " //      int a4 = 1;\n" +
                        "       int a5 = 1;\n" +
                        " //      int a6 = 1;\n" +
                        "       int a7 = 1;\n" +
                        "       int a8 = 1;\n" +
                        "       int a9 = 1;\n" +
                    "  }" +
                    "\n" +
                    "}";
    private Element methodBlock;

    @Test
    public void shouldCountCodeBlockNumberOfLines() throws Exception
    {
        CodeBlockJdom codeBlockJdom = CodeBlockJdom.createMethodImplementation(methodBlock, null, null);
        // 9 lines - 2 (no statements on lines 4,6)
        Assert.assertEquals(7, codeBlockJdom.getNumberOfLines());
    }

   @Before
    public void setUp() throws Exception
   {
       JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

       Iterator iterator = javaSourceJdom.getDocument().
               getDescendants(new ElementFilter(JavaSourceTokenType.BLOCK_SCOPE.getName()));

       methodBlock = (Element) iterator.next();

    }
}
