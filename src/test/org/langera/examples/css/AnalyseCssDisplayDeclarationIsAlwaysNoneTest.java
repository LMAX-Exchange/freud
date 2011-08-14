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
