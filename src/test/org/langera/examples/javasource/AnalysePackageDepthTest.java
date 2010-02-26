package org.langera.examples.javasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.javasource.JavaSourceJdom;
import org.langera.freud.util.resource.AnalysisResource;

import static org.langera.freud.javasource.JavaSourceMatchers.packagePath;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 11-Nov-2008
 * Time: 00:30:13
 */
public class AnalysePackageDepthTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldFindPackageDepthBetween3And7() throws Exception
    {

        Analysis analysis = JavaSourceExamples.packageDepthMustBeBetween3And7(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples.a;" +
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
        listener.assertPassed(packagePath("org.langera.examples.a"));

    }


    @Test
    public void testShouldNotFindPackageDepthBetween3And7() throws Exception
    {

        Analysis analysis = JavaSourceExamples.packageDepthMustBeBetween3And7(
                AnalysisResource.selfResourceIterator(JavaSourceJdom.PARSER,
                "package org.langera.examples.a.b.c.d.e;" +
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
        listener.assertFailed(packagePath("org.langera.examples.a.b.c.d.e"));

    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}