package org.langera.freud.util.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public final class GenericIterableAnalysedObjectIteratorTest
{
    @Test
    public void shouldIterateOverElements()
    {
        GenericIterableAnalysedObjectIterator<String> iterator =
                new GenericIterableAnalysedObjectIterator<String>(Arrays.asList("a", "b", "c"), false);

        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
    }

    @Test
    public void shouldIterateOverElementsWithCorrectHasNextValue()
    {
        GenericIterableAnalysedObjectIterator<String> iterator =
                new GenericIterableAnalysedObjectIterator<String>(Arrays.asList("a", "b", "c"), false);

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("c", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnCurrentElementMultipleTimes()
    {
        GenericIterableAnalysedObjectIterator<String> iterator =
                new GenericIterableAnalysedObjectIterator<String>(Arrays.asList("a", "b", "c"), false);

        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("a", iterator.current());
        Assert.assertEquals("a", iterator.current());
    }

    @Test
    public void shouldHandleEmptyLists()
    {
        GenericIterableAnalysedObjectIterator<String> iterator =
                new GenericIterableAnalysedObjectIterator<String>(Collections.<String>emptyList(), false);

        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next()); // return null - dont blow up
    }   

    @Test(expected = AssertionError.class)
    public void shouldAlertOnEmptyLists()
    {
        GenericIterableAnalysedObjectIterator<String> iterator =
                new GenericIterableAnalysedObjectIterator<String>(Collections.<String>emptyList(), true);

        Assert.assertFalse(iterator.hasNext());
    }
}