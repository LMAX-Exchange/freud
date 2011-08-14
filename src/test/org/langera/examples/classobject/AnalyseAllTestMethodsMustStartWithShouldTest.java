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

package org.langera.examples.classobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.examples.classobject.method.MethodExamples;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

public class AnalyseAllTestMethodsMustStartWithShouldTest
{
    private AnalysisListenerStub listener;

    @Test
    @SuppressWarnings("unchecked")
    public void shouldAnalyseProperClassAndPass() throws Exception
    {
        FreudAnalyser analyser = MethodExamples.allTestMethodsMustStartWithShould(
                ResourceIterators.<Class>selfResourceIterator(ProperJunitTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(10, listener.getTotalObjectsAnalysed());
        listener.assertPassed(ProperJunitTest.class.getMethod("shouldBehaveInSomeWay"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldAnalyseImproperClassAndFail() throws Exception
    {
        FreudAnalyser analyser = MethodExamples.allTestMethodsMustStartWithShould(
                ResourceIterators.<Class>selfResourceIterator(ImproperJunit4JTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(10, listener.getTotalObjectsAnalysed());
        listener.assertFailed(ImproperJunit4JTest.class.getMethod("justTestItDammit"));
    }
    
    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }

    ///////////////////////////////////////////////////////////////

    private static class ProperJunitTest
    {
        @Test
        public void shouldBehaveInSomeWay()
        {
            // no op
        }
    }

    private static class ImproperJunit4JTest
    {
        @Test
        public void justTestItDammit()
        {
            // no op
        }
    }
}

