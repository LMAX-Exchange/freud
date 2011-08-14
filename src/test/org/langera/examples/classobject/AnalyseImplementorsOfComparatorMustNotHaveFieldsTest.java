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

import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

import java.util.Comparator;

public class AnalyseImplementorsOfComparatorMustNotHaveFieldsTest
{
    private AnalysisListenerStub listener;

    @Test
    public void shouldPassBecauseComparatorHasNoFields()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(ComparatorWithNoFields.class));

        analyser.analyse(listener);
        listener.assertPassed(ComparatorWithNoFields.class);
    }

    @Test
    public void shouldFailBecauseImplementationHasAField()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(ComparatorWithFields.class));

        analyser.analyse(listener);
        listener.assertFailed(ComparatorWithFields.class);
    }

    @Test
    public void shouldPassBecauseNotAComparator()
    {
        FreudAnalyser analyser = ClassExamples.allImplementorsOfComparatorMustNotContainFields(
                ResourceIterators.<Class>selfResourceIterator(String.class));

        analyser.analyse(listener);
        listener.assertPassed(String.class);
    }


    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }

    private static class ComparatorWithNoFields implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            return 0;
        }
    }

    private static class ComparatorWithFields implements Comparator
    {
        private String aFields = "";

        public int compare(Object o1, Object o2)
        {
            return 0;
        }
    }
}
