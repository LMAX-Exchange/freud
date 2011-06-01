package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.css.CssJdom;
import org.langera.freud.optional.css.CssTestMatchers;
import org.langera.freud.optional.css.cssrule.selector.CssSelector;

public final class AnalyseCssSelectorsNameMustNotContainUpperCaseCharactersTest
{
    private AnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseCssSelectors() throws Exception
    {
        FreudAnalyser analysis = CssExamples.classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                                                      ".shouldFail \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n" +
                                                              " \n" +
                                                              "a #my-link-id #myOtherLinkId \n" +
                                                              "{ \n" +
                                                              " display: none; \n" +
                                                              "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(4, listener.getTotalObjectsAnalysed());
        listener.assertFailed(CssTestMatchers.cssSelector(CssSelector.Type.CLASS, "shouldFail"));
        listener.assertPassed(CssTestMatchers.cssSelector(CssSelector.Type.ID, "my-link-id"));
        listener.assertFailed(CssTestMatchers.cssSelector(CssSelector.Type.ID, "myOtherLinkId"));
        listener.assertFiltered(CssTestMatchers.cssSelector(CssSelector.Type.TAG, "a"));
    }

    @Test
    public void test() throws Exception
    {
        FreudAnalyser analysis = CssExamples.classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                        "ul#main-navigation {  }  "));

        analysis.analyse(listener);
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
    }
}