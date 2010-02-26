package org.langera.freud.util.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MultiCollectionTest 
{
    private MultiCollection<String> multiCollection;


    @Test
    public void testEmptyCollection() throws Exception
    {
        MultiCollection<Object> emptyCollection =
                new MultiCollection<Object>(Collections.<Collection<Object>>emptyList());

        Assert.assertEquals(0, emptyCollection.size());

        Iterator<Object> iterator = emptyCollection.iterator();        
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

    @Test
    public void testShouldCalculateSizeCorrectly() throws Exception 
    {

        Assert.assertEquals(6, multiCollection.size());
    }


    @Test
    public void testShouldIterateOverAll() throws Exception
    {
        Iterator<String> iterator = multiCollection.iterator();
        
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

        multiCollection = new MultiCollection<String>(listOfListOfString);
    }
}
