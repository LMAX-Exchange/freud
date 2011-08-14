/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

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
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.TAG;

public final class AnalyseDescendantSelectorsAreTheWorstTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldPassAnalysis()
    {
        FreudAnalyser analysis = CssExamples.descendantSelectorsAreTheWorst(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
                        "a .shouldPass \n" +
                                "{ \n" +
                                " display: none; \n" +
                                "} \n"));

        analysis.analyse(listener);


        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(cssRule(cssSelector(TAG, "a"), cssSelector(CLASS, "shouldPass")));
    }

    @Test
    public void shouldFailAnalysis()
    {
        FreudAnalyser analysis = CssExamples.descendantSelectorsAreTheWorst(
                ResourceIterators.selfResourceIterator(CssJdom.PARSER,
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

        listener.assertPassed(cssRule(cssSelector(TAG, "a"), cssSelector(CLASS, "shouldPass")));
        listener.assertFailed(cssRule(cssSelector(TAG, "table"),
                                      cssSelector(TAG, "tr"),
                                      cssSelector(TAG, "td"),
                                      cssSelector(TAG, "a"), cssSelector(CLASS, "shouldFail")));
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
    }
}