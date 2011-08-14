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
