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
