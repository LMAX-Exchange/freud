package org.langera.examples.aclass;

import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.util.resource.AnalysisResource;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 03-Dec-2008
 * Time: 12:50:35
 */
public class AnalyseNoSubTypeOfObjectHasPropertyOfTypeIntegerTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldPass()
    {
        Analysis analysis = ClassExamples.noSubTypeOfObjectHasPropertyOfTypeString(
                AnalysisResource.<Class>selfResourceIterator(Character.class));

        analysis.analyse(listener);
        listener.assertPassed(Character.class);
    }

    @Test
    public void testShouldFailBecauseConstructorHasString()
    {
        Analysis analysis = ClassExamples.noSubTypeOfObjectHasPropertyOfTypeString(
                AnalysisResource.<Class>selfResourceIterator(Integer.class));

        analysis.analyse(listener);
        listener.assertFailed(Integer.class);
    }

    @Test
    public void testShouldFailBecauseSetterOfString()
    {
        Analysis analysis = ClassExamples.noSubTypeOfObjectHasPropertyOfTypeString(
                AnalysisResource.<Class>selfResourceIterator(DummyWithAStringSetter.class));

        analysis.analyse(listener);
        listener.assertFailed(DummyWithAStringSetter.class);
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

    }

    private static class DummyWithAStringSetter
    {
        public void setString(String str)
        {

        }
    }
}
