package org.langera.examples.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.javasource.JavaSourceMatchers;
import org.langera.freud.util.resource.AnalysisResource;

public class AnalyseSuprressingAnalysisTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldFailAnalysis() throws Exception
    {
        Analysis analysis = JavaSourceExamples.codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples;\n" +
                        " \n" +
                        "public class SimpleClass \n" +
                        "{ \n" +
                        " \n" +
                        "  public String toFail()\n" +
                        "  {\n" +
                        "       return \"\" \n" +
                        "              + getClass();\n" +
                        "  }\n" +
                        " \n" +
                        "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(JavaSourceMatchers.codeBlockIn("SimpleClass"));
    }


    @Test
    public void testShouldSuppressAnalysis() throws Exception
    {
        Analysis analysis = JavaSourceExamples.codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples;\n" +
                        " \n" +
                        "public class SimpleClass \n" +
                        "{ \n" +
                        " \n" +
                        "  @SuppressWarnings(\"freud:NumberOfLinesOfCodeBlock() <= 1\")\n" +
                        "  public String toBeSuppressed()\n" +
                        "  {\n" +
                        "       int i = 1;\n" +
                        "       int i = 2;\n" +
                        "       int i = 3;\n" +
                        "       return \"\" + getClass();\n" +
                        "  }\n" +
                        " \n" +
                        "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFiltered(JavaSourceMatchers.codeBlockIn("SimpleClass"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

    }

}
