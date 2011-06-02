package org.langera.freud.optional.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.JavaSourceDsl.fullClassName;
import static org.langera.freud.optional.javasource.JavaSourceDsl.simpleClassName;

public final class JavaSourceDslTest
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
    private JavaSource javaSource;

    @Test
    public void shouldReturnTrueToAMatchedRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, fullClassName().matches("org.+SimpleClass"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, no(fullClassName().matches("com.+SimpleClass")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, fullClassName().contains("org"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOfFullClassName()
    {
        Assert.assertThat(javaSource, no(fullClassName().contains("com")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, simpleClassName().matches("SimpleClass"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, no(simpleClassName().matches("SimpleClassX")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, simpleClassName().contains("Simple"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexOfSimpleClassName()
    {
        Assert.assertThat(javaSource, no(simpleClassName().contains("Other")));
    }

    @Before
    public void setUp() throws Exception
    {
        javaSource = new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name");
    }
}
