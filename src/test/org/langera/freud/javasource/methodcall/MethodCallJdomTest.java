package org.langera.freud.javasource.methodcall;

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freudgenerated.javasource.parser.JavaSourceTokenType;

import java.io.StringReader;
import java.util.Iterator;

public class MethodCallJdomTest
{

    private static final String CLASS_EXAMPLE =
            "package org.langera.examples;" +
            " " +
            "public class SimpleClass " +
            "{ " +
            "\n" +
            "  public String MyMethod()" +
            "  {" +
            "       Object o = toString();\n" +
            "       System.out.println();\n" +
            "       int i = o.hashCode();\n" +
            "  }" +
            "\n" +
            "}";
    private Element localMethodCall;
    private Element staticMethodCall;
    private Element varMethodCall;


    @Test
    public void testShouldParseLocalMethodCall() throws Exception
    {
        MethodCallJdom methodCall = new MethodCallJdom(localMethodCall);

        Assert.assertEquals("toString", methodCall.getMethodName());
        Assert.assertEquals(0, methodCall.getInstanceReferences().length);
    }

    @Test
    public void testShouldParseRemoteStaticVarMethodCall() throws Exception
    {
        MethodCallJdom methodCall = new MethodCallJdom(staticMethodCall);

        Assert.assertEquals("println", methodCall.getMethodName());
        Assert.assertEquals(2, methodCall.getInstanceReferences().length);
        Assert.assertEquals("System", methodCall.getInstanceReferences()[0]);
        Assert.assertEquals("out", methodCall.getInstanceReferences()[1]);
    }

    @Test
    public void testShouldParseVarMethodCall() throws Exception
    {
        MethodCallJdom methodCall = new MethodCallJdom(varMethodCall);

        Assert.assertEquals("hashCode", methodCall.getMethodName());
        Assert.assertEquals(1, methodCall.getInstanceReferences().length);
        Assert.assertEquals("o", methodCall.getInstanceReferences()[0]);
    }


    @Before
    public void setUp() throws Exception
    {
        JavaSourceJdom javaSourceJdom = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");

        Iterator iterator = javaSourceJdom.getDocument().
                getDescendants(new ElementFilter(JavaSourceTokenType.METHOD_CALL.getName()));

        localMethodCall = (Element) iterator.next();
        staticMethodCall = (Element) iterator.next();
        varMethodCall = (Element) iterator.next();

    }

}
