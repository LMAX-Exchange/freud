package org.langera.freud.optional.javasource.classdecl;

import org.antlr.runtime.RecognitionException;
import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.IOException;
import java.io.StringReader;

public final class ClassDeclarationJdomTest
{
    private static final String CLASS_EXAMPLE_WITH_SUPERCLASS =
                    "package org.langera.examples;" +
                    " " +
                    "public class SimpleClass extends SuperClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                            "}";

    private static final String CLASS_EXAMPLE_WITH_NO_SUPERCLASS =
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
    public void shouldReturnSuperClassName() throws Exception
    {
        ClassDeclarationJdom classDeclarationJdom = getClassDeclaration(CLASS_EXAMPLE_WITH_SUPERCLASS);

        Assert.assertEquals("SuperClass", classDeclarationJdom.getSuperClassName());
    }

    @Test
    public void shouldReturnNullIfNoSuperClassExists() throws Exception
    {
        ClassDeclarationJdom classDeclarationJdom = getClassDeclaration(CLASS_EXAMPLE_WITH_NO_SUPERCLASS);

        Assert.assertNull(classDeclarationJdom.getSuperClassName());
    }


    private ClassDeclarationJdom getClassDeclaration(final String sourceCode) throws IOException, RecognitionException
    {
        final JavaSource javaSource = new JavaSourceJdom(new StringReader(sourceCode), "Name");

//        System.out.println(javaSource);

        return (ClassDeclarationJdom) javaSource.getClassDeclaration();
    }
}
