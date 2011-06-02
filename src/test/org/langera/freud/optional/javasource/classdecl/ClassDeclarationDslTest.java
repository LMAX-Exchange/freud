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
