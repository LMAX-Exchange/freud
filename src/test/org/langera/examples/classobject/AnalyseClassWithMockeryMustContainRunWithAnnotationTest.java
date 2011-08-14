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

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

public class AnalyseClassWithMockeryMustContainRunWithAnnotationTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldAnalyseMockeryDeclaredProperly() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(ProperJunit4JmockClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(ProperJunit4JmockClassTest.class);
    }

    @Test
    public void shouldAnalyseWhenMockeryDeclaredWithoutCustomRunner() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(MockeryButNoRunWithRunnerClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButNoRunWithRunnerClassTest.class);        
    }

    @Test
    public void shouldAnalyseWhenMockeryDeclaredWithDifferentRunner() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(MockeryButDifferentRunWithRunnerClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButDifferentRunWithRunnerClassTest.class);
    }

    @Test
    public void shouldClassesThatDoNotEndWithTestAreFiltered() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(String.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFiltered(String.class);         
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }

    ///////////////////////////////////////////////////////////////

    @RunWith(JMock.class)
    private static class ProperJunit4JmockClassTest
    {
        private Mockery mockery;
    }

    private static class MockeryButNoRunWithRunnerClassTest
    {
        private Mockery mockery;
    }

    @RunWith(Parameterized.class)
    private static class MockeryButDifferentRunWithRunnerClassTest
    {
        private Mockery mockery;
    }
}

