package org.langera.examples.css;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.css.CssJdom;
import org.langera.freud.css.CssMatchers;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.util.resource.AnalysisResource;

public final class AnalyseCssSelectorsNameMustNotContainUpperCaseCharactersTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseCssSelectors() throws Exception
    {
        Analysis analysis = CssExamples.classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
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
        listener.assertFailed(CssMatchers.cssSelector(CssSelector.Type.CLASS, "shouldFail"));
        listener.assertPassed(CssMatchers.cssSelector(CssSelector.Type.ID, "my-link-id"));
        listener.assertFailed(CssMatchers.cssSelector(CssSelector.Type.ID, "myOtherLinkId"));
        listener.assertFiltered(CssMatchers.cssSelector(CssSelector.Type.TAG, "a"));
    }

    @Test
    public void test() throws Exception
    {
        Analysis analysis = CssExamples.classOrIdCssSelectorsNameMustNotContainUpperCaseCharacters(
                AnalysisResource.selfResourceIterator(CssJdom.PARSER,
                                                      "ul#main-navigation {  }  "));

        analysis.analyse(listener);
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();
    }
}