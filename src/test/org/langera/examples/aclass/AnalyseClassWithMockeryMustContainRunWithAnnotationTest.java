package org.langera.examples.aclass;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.util.resource.AnalysisResource;

public class AnalyseClassWithMockeryMustContainRunWithAnnotationTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseMockeryDeclaredProperly() throws Exception
    {
        Analysis analysis = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                AnalysisResource.<Class>selfResourceIterator(ProperJunit4JmockClassTest.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(ProperJunit4JmockClassTest.class);
    }

    @Test
    public void testShouldAnalyseWhenMockeryDeclaredWithoutCustomRunner() throws Exception
    {
        Analysis analysis = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                AnalysisResource.<Class>selfResourceIterator(MockeryButNoRunWithRunnerClassTest.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButNoRunWithRunnerClassTest.class);        
    }

    @Test
    public void testShouldAnalyseWhenMockeryDeclaredWithDifferentRunner() throws Exception
    {
        Analysis analysis = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                AnalysisResource.<Class>selfResourceIterator(MockeryButDifferentRunWithRunnerClassTest.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(MockeryButDifferentRunWithRunnerClassTest.class);
    }

    @Test
    public void testShouldClassesThatDoNotEndWithTestAreFiltered() throws Exception
    {
        Analysis analysis = ClassExamples.testClassWithMockeryMustContainRunWithAnnotation(
                AnalysisResource.<Class>selfResourceIterator(String.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFiltered(String.class);         
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

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

