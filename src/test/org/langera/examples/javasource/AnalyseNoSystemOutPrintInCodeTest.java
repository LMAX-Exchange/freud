package org.langera.examples.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.util.resource.AnalysisResource;

import static org.langera.freud.javasource.JavaSourceMatchers.codeBlockIn;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 11-Nov-2008
 * Time: 00:30:13
 */
public class AnalyseNoSystemOutPrintInCodeTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldNotFindSystemOutPrintCalls() throws Exception
    {

        Analysis analysis = JavaSourceExamples.noSystemOutPrintInCode(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples;" +
                        " " +
                        "public class SimpleClass " +
                        "{ " +
                        " " +
                        "  public String toString()" +
                        "  {" +
                        "       return \"\" + getClass();" +
                        "  }" +
                        "}"));

        analysis.analyse(listener);
        
        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(codeBlockIn("SimpleClass"));

    }


    @Test
    public void testShouldFindSystemOutPrintCalls() throws Exception
    {

        Analysis analysis = JavaSourceExamples.noSystemOutPrintInCode(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples;" +
                        " " +
                        "public class SimpleClass " +
                        "{ " +
                        " " +
                        "  public String toString()" +
                        "  {" +
                        "       System.out.println(\"print something\");"+
                        "       return \"\";" +
                        "  }" +
                        "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(codeBlockIn("SimpleClass"));

    }


    @Test
    public void testShouldNotFindSystemOutPrintCallsIfAreInsideComment() throws Exception
    {

        Analysis analysis = JavaSourceExamples.noSystemOutPrintInCode(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples;" +
                        " " +
                        "public class SimpleClass " +
                        "{ " +
                        " " +
                        "  public String toString()" +
                        "  {" +
                        "       //System.out.println(\"print something\");\n"+
                        "       return \"\";" +
                        "  }" +
                        "}"));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(codeBlockIn("SimpleClass"));        
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}
