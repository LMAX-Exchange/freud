package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.css.CssJdom;
import org.langera.freud.css.CssMatchers;
import org.langera.freud.util.resource.AnalysisResource;

public final class AnalyseCssDisplayDeclarationIsAlwaysNoneTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void shouldPassAnalysis()
    {
        Analysis analysis = CssExamples.cssDisplayDeclarationIsAlwaysNone(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
                        ".shouldPass \n" +
                        "{ \n" +
                        " display: none; \n" +
                        "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(CssMatchers.cssDeclaration("display", "none"));
    }

    @Test
    public void shouldFailAnalysis()
    {
        Analysis analysis = CssExamples.cssDisplayDeclarationIsAlwaysNone(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
                        ".shouldPass \n" +
                        "{ \n" +
                        " display: none; \n" +
                        "} \n" +
                        ".shouldFail \n" +
                        "{ \n" +
                        " display: block; \n" +
                        "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertPassed(CssMatchers.cssDeclaration("display", "none"));
        listener.assertFailed(CssMatchers.cssDeclaration("display", "block"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}
