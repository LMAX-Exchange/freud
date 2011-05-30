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
    public void testShouldAnalyseMockeryDeclaredProperly() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(ProperJunit4JmockClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(ProperJunit4JmockClassTest.class);
    }

    @Test
    public void testShouldAnalyseWhenMockeryDeclaredWithoutCustomRunner() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(MockeryButNoRunWithRunnerClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButNoRunWithRunnerClassTest.class);        
    }

    @Test
    public void testShouldAnalyseWhenMockeryDeclaredWithDifferentRunner() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                ResourceIterators.<Class>selfResourceIterator(MockeryButDifferentRunWithRunnerClassTest.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButDifferentRunWithRunnerClassTest.class);
    }

    @Test
    public void testShouldClassesThatDoNotEndWithTestAreFiltered() throws Exception
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

