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

package org.langera.examples.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.text.TextResourceParser;

import static org.langera.examples.text.TextTestMatchers.line;

public class AnalyseLineLengthNotExceedTest
{
    private static final String PASSING_TEXT = "0\n1\n2\n3\n4\n5";
    private static final String FAILURE_TEXT = "12345678901";
    private AnalysisListenerStub listener;

    @Test
    public void shouldAnalyseTextLinesForLineLength() throws Exception
    {

        FreudAnalyser analyser = TextExamples.lineLengthDoesNotExceed(10,
                ResourceIterators.selfResourceIterator(TextResourceParser.getInstance(),
                        PASSING_TEXT,
                        FAILURE_TEXT));


        analyser.analyse(listener);

                           
        Assert.assertEquals(7, listener.getTotalObjectsAnalysed());
        // first 6 lines passed
        for (int i=0; i <  6; i++)
        {
            listener.assertPassed(line(i, "" + i));
        }
        listener.assertFailed(line(0, FAILURE_TEXT));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();

    }
}