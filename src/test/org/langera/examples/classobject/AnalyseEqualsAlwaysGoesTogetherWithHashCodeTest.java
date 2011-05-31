package org.langera.examples.classobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 28-Oct-2008
 * Time: 00:04:55
 */
public class AnalyseEqualsAlwaysGoesTogetherWithHashCodeTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereBothDeclared() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                ResourceIterators.<Class>selfResourceIterator(String.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(String.class);
    }

    @Test
    public void shouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereNoneDeclared() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                ResourceIterators.<Class>selfResourceIterator(IllegalStateException.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(IllegalStateException.class);
    }

    @Test
    public void shouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereOnlyHashCodeDeclared() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                ResourceIterators.<Class>selfResourceIterator(HashCodeButNoEquals.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(HashCodeButNoEquals.class);
    }

    @Test
    public void shouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereOnlyEqualsDeclared() throws Exception
    {
        FreudAnalyser analyser = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                ResourceIterators.<Class>selfResourceIterator(EqualsButNoHashCode.class));

        analyser.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(EqualsButNoHashCode.class);
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }

    ////////////////////////////////////////////////

    private static final class HashCodeButNoEquals
    {
        @Override
        public int hashCode()
        {
            return 17;
        }
    }

    private static final class EqualsButNoHashCode
    {
        @Override
        public boolean equals(Object o)
        {
            return this == o || !(o == null || getClass() != o.getClass());

        }
    }
}
