package org.langera.freud.optional.javasource;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 13-Nov-2008
 * Time: 21:29:01
 */
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
