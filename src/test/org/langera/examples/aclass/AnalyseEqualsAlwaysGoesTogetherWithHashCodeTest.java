package org.langera.examples.aclass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.Analysis;
import org.langera.freud.TestAnalysisListenerStub;
import org.langera.freud.util.resource.AnalysisResource;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 28-Oct-2008
 * Time: 00:04:55
 */
public class AnalyseEqualsAlwaysGoesTogetherWithHashCodeTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void testShouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereBothDeclared() throws Exception
    {
        Analysis analysis = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                AnalysisResource.<Class>selfResourceIterator(String.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(String.class);
    }

    @Test
    public void testShouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereNoneDeclared() throws Exception
    {
        Analysis analysis = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                AnalysisResource.<Class>selfResourceIterator(IllegalStateException.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertPassed(IllegalStateException.class);
    }

    @Test
    public void testShouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereOnlyHashCodeDeclared() throws Exception
    {
        Analysis analysis = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                AnalysisResource.<Class>selfResourceIterator(HashCodeButNoEquals.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(HashCodeButNoEquals.class);
    }

    @Test
    public void testShouldAnalyseEqualsAlwaysGoesTogetherWithHashCodeWhereOnlyEqualsDeclared() throws Exception
    {
        Analysis analysis = ClassExamples.equalsAlwaysGoesTogetherWithHashCode(
                AnalysisResource.<Class>selfResourceIterator(EqualsButNoHashCode.class));

        analysis.analyse(listener);

        Assert.assertEquals(1, listener.getTotalObjectsAnalysed());
        listener.assertFailed(EqualsButNoHashCode.class);
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new TestAnalysisListenerStub();

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
