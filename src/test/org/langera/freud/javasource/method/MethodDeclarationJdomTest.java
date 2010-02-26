package org.langera.freud.javasource.method;

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.javasource.block.CodeBlock;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;

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
    public void testShouldParseMethodImplementation() throws Exception 
    {
        MethodDeclarationJdom methodDeclarationJdom = new MethodDeclarationJdom(methodDecl, null);

        final CodeBlock impl = methodDeclarationJdom.getImplementation();
        Assert.assertNotNull(impl);
        Assert.assertEquals(1, impl.getMethodCallListByMethodName("getClass").size());        
    }

   @Before
    public void setUp() throws Exception
   {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

       methodDecl = (Element) javaSourceJdom.getDocument().
               getDescendants(new ElementFilter(JavaSourceTokenType.FUNCTION_METHOD_DECL.getName())).next();
    }

}
