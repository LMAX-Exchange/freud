package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.css.CssJdom;
import org.langera.freud.optional.css.CssTestMatchers;

public final class AnalyseCssDisplayDeclarationIsAlwaysNoneTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldPassAnalysis()
    {
        FreudAnalyser analysis = CssExamples.cssDisplayDeclarationIsAlwaysNone(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                        ".shouldPass \n" +
                        "{ \n" +
                        " display: none; \n" +
                        "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(CssTestMatchers.cssDeclaration("display", "none"));
    }

    @Test
    public void shouldFailAnalysis()
    {
        FreudAnalyser analysis = CssExamples.cssDisplayDeclarationIsAlwaysNone(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
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
        listener.assertPassed(CssTestMatchers.cssDeclaration("display", "none"));
        listener.assertFailed(CssTestMatchers.cssDeclaration("display", "block"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
    }
}
