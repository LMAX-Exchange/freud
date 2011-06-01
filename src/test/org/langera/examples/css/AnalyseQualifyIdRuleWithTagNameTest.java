package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.css.CssJdom;

import static org.langera.freud.optional.css.CssTestMatchers.cssRule;
import static org.langera.freud.optional.css.CssTestMatchers.cssSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.CLASS;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.ID;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.TAG;

public final class AnalyseQualifyIdRuleWithTagNameTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldPassAnalysis()
    {
        FreudAnalyser freudAnalyser = CssExamples.doNotQualifyIdRuleWithTagName(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                        "a .shouldPass \n" +
                                "{ \n" +
                                " display: none; \n" +
                                "} \n" +
                                "#id shouldAlsoPass \n" +
                                "{ \n" +
                                " display: none;" +
                                "} \n" +
                                "#andWithNoTagAtAll \n" +
                                "{ \n" +
                                " display: none;" +
                                "} \n"));

        freudAnalyser.analyse(listener);


        Assert.assertEquals(3, listener.getTotalObjectsAnalysed());
        listener.assertPassed(cssRule(cssSelector(TAG, "a"), cssSelector(CLASS, "shouldPass")));
        listener.assertPassed(cssRule(cssSelector(ID, "id"), cssSelector(TAG, "shouldAlsoPass")));
        listener.assertPassed(cssRule(cssSelector(ID, "andWithNoTagAtAll")));
    }

    @Test
    public void shouldFailAnalysis()
    {
        FreudAnalyser freudAnalyser = CssExamples.doNotQualifyIdRuleWithTagName(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                                                      "a .shouldPass \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n" +
                                                              "a #shouldFail \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n"));

        freudAnalyser.analyse(listener);


        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());

        listener.assertPassed(cssRule(cssSelector(TAG, "a"), cssSelector(CLASS, "shouldPass")));
        listener.assertFailed(cssRule(cssSelector(TAG, "a"), cssSelector(ID, "shouldFail")));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
    }
}