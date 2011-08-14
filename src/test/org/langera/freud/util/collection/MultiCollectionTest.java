/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.util.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public void shouldCalculateSizeCorrectly() throws Exception
    {

        Assert.assertEquals(6, multiCollection.size());
    }


    @Test
    public void shouldIterateOverAll() throws Exception
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
