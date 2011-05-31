package org.langera.examples.classobject;

import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 03-Dec-2008
 * Time: 12:50:35
 */
public class AnalyseImplementorsOfComparatorMustNotHaveFieldsTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldPassBecauseComparatorHasNoFields()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(ComparatorWithNoFields.class));

        analyser.analyse(listener);
        listener.assertPassed(ComparatorWithNoFields.class);
    }

    @Test
    public void shouldFailBecauseImplementationHasAField()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(ComparatorWithFields.class));

        analyser.analyse(listener);
        listener.assertFailed(ComparatorWithFields.class);
    }

    @Test
    public void shouldPassBecauseNotAComparator()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(String.class));

        analyser.analyse(listener);
        listener.assertPassed(String.class);
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

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
