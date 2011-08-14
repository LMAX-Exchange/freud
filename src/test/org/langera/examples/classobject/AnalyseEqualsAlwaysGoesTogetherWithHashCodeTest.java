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

package org.langera.examples.classobject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

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
