package org.langera.freud.optional.classobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.listener.AnalysisListenerStub;

public final class ClassByNameIteratorTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldIterateOverClasses()
    {
        ClassByNameIterator iterator = new ClassByNameIterator(false, "java.lang.Integer", "java.lang.String");
        iterator.setListener(listener);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.class, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(String.class, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldIterateOverEmptyList()
    {
        ClassByNameIterator iterator = new ClassByNameIterator(false);
        iterator.setListener(listener);

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldSkipUnknownClasses()
    {
        ClassByNameIterator iterator =
                new ClassByNameIterator(false, "java.lang.Integer", "foo.Bar", "java.lang.String");
        iterator.setListener(listener);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.class, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(String.class, iterator.next());
        Assert.assertFalse(iterator.hasNext());

        Assert.assertEquals(1, listener.getUnexpected().size());
    }

    @Before
    public void setUp()
    {
        listener = new AnalysisListenerStub();
    }
}
