package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.css.CssJdom;
import org.langera.freud.util.resource.AnalysisResource;

import static org.langera.freud.css.CssMatchers.cssRule;
import static org.langera.freud.css.CssMatchers.cssSelector;
import static org.langera.freud.css.cssrule.selector.CssSelector.Type;

public final class AnalyseDescendantSelectorsAreTheWorstTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void shouldPassAnalysis()
    {
        Analysis analysis = CssExamples.descendantSelectorsAreTheWorst(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
                                                      "a .shouldPass \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(cssRule(cssSelector(Type.TAG, "a"), cssSelector(Type.CLASS, "shouldPass")));
    }

    @Test
    public void shouldFailAnalysis()
    {
        Analysis analysis = CssExamples.descendantSelectorsAreTheWorst(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
                                                      "a .shouldPass \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n" +
                                                              "table tr td a .shouldFail \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());

        listener.assertPassed(cssRule(cssSelector(Type.TAG, "a"), cssSelector(Type.CLASS, "shouldPass")));
        listener.assertFailed(cssRule(cssSelector(Type.TAG, "table"),
                                      cssSelector(Type.TAG, "tr"),
                                      cssSelector(Type.TAG, "td"),
                                      cssSelector(Type.TAG, "a"), cssSelector(Type.CLASS, "shouldFail")));
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}