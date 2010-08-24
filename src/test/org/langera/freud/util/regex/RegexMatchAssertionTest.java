package org.langera.freud.util.regex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.javasource.JavaSource;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.javasource.assertion.JavaSourceFullNameMatchAssertionAdapter;

import java.io.StringReader;

public class RegexMatchAssertionTest
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

    private static final String OTHER_CLASS_EXAMPLE =
            "package org.langera.examples;" +
                    " " +
                    "public class AnotherSimpleClass " +
                    "{ " +
                    " " +
                    "  public String toString()" +
                    "  {" +
                    "       return \"\";" +
                    "  }" +
                    "}";

    private RegexMatchAnalysisAssertion<JavaSource> assertion;

    @Test
    public void testShouldMatchClassName() throws Exception
    {
        Assert.assertTrue(assertion.matches(new JavaSourceJdom(new StringReader(CLASS_EXAMPLE), "Name")));
    }


    @Test
    public void testShouldNotMatchClassName() throws Exception
    {
        Assert.assertFalse(assertion.matches(new JavaSourceJdom(new StringReader(OTHER_CLASS_EXAMPLE), "Name")));
    }

    @Before
    public void setUp() throws Exception
    {
        assertion = new RegexMatchAnalysisAssertion<JavaSource>("org\\.langera\\.examples\\.S.*", true,
                                                                new JavaSourceFullNameMatchAssertionAdapter());
    }
}
