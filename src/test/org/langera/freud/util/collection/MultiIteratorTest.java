package org.langera.freud.util.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MultiIteratorTest 
{
    private MultiIterator<String> iterator;

    @Test
    public void shouldIterateOverAll() throws Exception
    {
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("c", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("d", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("e", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("f", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        try
        {
            iterator.next();
            Assert.fail("Expected NoSuchElementException");
        }
        catch (NoSuchElementException e)
        {
            // expected
        }
    }

    @Before
    public void setUp() throws Exception
    {
        List<List<String>> listOfListOfString = new LinkedList<List<String>>();
        listOfListOfString.add(Arrays.<String>asList("a", "b", "c"));
        listOfListOfString.add(Arrays.<String>asList());
        listOfListOfString.add(Arrays.<String>asList("d"));
        listOfListOfString.add(Arrays.<String>asList("e", "f"));

        iterator = new MultiIterator<String>(listOfListOfString);
    }




}
