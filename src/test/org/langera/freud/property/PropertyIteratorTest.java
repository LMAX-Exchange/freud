package org.langera.freud.property;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisException;
import org.langera.freud.TestAnalysisListenerStub;

import java.util.Properties;

public final class PropertyIteratorTest
{
    private TestAnalysisListenerStub listener;

    @Test
    public void shouldIterateOverClasses()
    {
        final Properties properties = new Properties();
        properties.setProperty("a", "1");
        properties.setProperty("b", "2");
        PropertyIterator iterator = new PropertyIterator(properties, false);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next().getKey());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("1", iterator.next().getValue());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldIterateOverEmptyList()
    {
        PropertyIterator iterator = new PropertyIterator(new Properties(), false);

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldAlertOnEmptyList()
    {
        PropertyIterator iterator = new PropertyIterator(new Properties(), true);
        iterator.setListener(listener);

        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(AnalysisException.class, listener.getUnexpected().getFirst().getAnalysed().getClass());
    }

    @Before
    public void setUp()
    {
        listener = new TestAnalysisListenerStub();    
    }
}
