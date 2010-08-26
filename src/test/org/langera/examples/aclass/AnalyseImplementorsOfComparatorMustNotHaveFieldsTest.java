package org.langera.examples.aclass;

import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.util.resource.AnalysisResource;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 03-Dec-2008
 * Time: 12:50:35
 */
public class AnalyseImplementorsOfComparatorMustNotHaveFieldsTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldPassBecauseComparatorHasNoFields()
    {
        Analysis analysis = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                AnalysisResource.<Class>selfResourceIterator(ComparatorWithNoFields.class));

        analysis.analyse(listener);
        listener.assertPassed(ComparatorWithNoFields.class);
    }

    @Test
    public void testShouldFailBecauseImplementationHasAField()
    {
        Analysis analysis = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                AnalysisResource.<Class>selfResourceIterator(ComparatorWithFields.class));

        analysis.analyse(listener);
        listener.assertFailed(ComparatorWithFields.class);
    }

    @Test
    public void testShouldPassBecauseNotAComparator()
    {
        Analysis analysis = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                AnalysisResource.<Class>selfResourceIterator(String.class));

        analysis.analyse(listener);
        listener.assertPassed(String.class);
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

    }

    private static class ComparatorWithNoFields implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            return 0;
        }
    }

    private static class ComparatorWithFields implements Comparator
    {
        private String aFields = "";

        public int compare(Object o1, Object o2)
        {
            return 0;
        }
    }
}
