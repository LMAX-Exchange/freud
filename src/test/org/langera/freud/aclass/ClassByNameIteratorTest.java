package org.langera.freud.aclass;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.TestAnalysisListenerStub;

public final class ClassByNameIteratorTest
{
    @Test
    public void shouldIterateOverClasses()
    {
        ClassByNameIterator iterator = new ClassByNameIterator("java.lang.Integer", "java.lang.String");

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.class, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(String.class, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldIterateOverEmptyList()
    {
        ClassByNameIterator iterator = new ClassByNameIterator();

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldSkipUnknownClasses()
    {
        ClassByNameIterator iterator =
                new ClassByNameIterator("java.lang.Integer", "foo.Bar", "java.lang.String");
        final TestAnalysisListenerStub listener = new TestAnalysisListenerStub();
        iterator.setListener(listener);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.class, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(String.class, iterator.next());
        Assert.assertFalse(iterator.hasNext());

        Assert.assertEquals(1, listener.getUnexpected().size());
    }
}
