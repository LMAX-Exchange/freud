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

package org.langera.freud.core.listener;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class AnalysisListenerChainTest
{
    private static final Matcher DUMMY_ASSERTION = new TypeSafeMatcher()
    {
        public final boolean matchesSafely(final Object toBeAnalysed)
        {
            return false;
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    };
    private static final Object ANALYSED_OBJECT = new Object();

    private Mockery mockery = new Mockery();

    private AnalysisListener listener1;
    private AnalysisListener listener2;
    private AnalysisListenerChain chain;
    private static final Exception EXCEPTION = new Exception();


    @Test
    public void shouldDelegatePassed() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(listener1).passed(ANALYSED_OBJECT, DUMMY_ASSERTION);
                one(listener2).passed(ANALYSED_OBJECT, DUMMY_ASSERTION);
            }
        });

        chain.passed(ANALYSED_OBJECT, DUMMY_ASSERTION);
    }


    @Test
    public void shouldDelegateFiltered() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(listener1).filtered(ANALYSED_OBJECT, DUMMY_ASSERTION);
                one(listener2).filtered(ANALYSED_OBJECT, DUMMY_ASSERTION);
            }
        });

        chain.filtered(ANALYSED_OBJECT, DUMMY_ASSERTION);
    }


    @Test
    public void shouldDelegateFailed() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(listener1).failed(ANALYSED_OBJECT, DUMMY_ASSERTION);
                one(listener2).failed(ANALYSED_OBJECT, DUMMY_ASSERTION);
            }
        });

        chain.failed(ANALYSED_OBJECT, DUMMY_ASSERTION);
    }


    @Test
    public void shouldDelegateUnexpected() throws Exception
    {
        mockery.checking(new Expectations()
        {
            {
                one(listener1).unexpected(null, EXCEPTION);
                one(listener2).unexpected(null, EXCEPTION);
            }
        });

        chain.unexpected(null, EXCEPTION);
    }


    @Before
    public void setUp() throws Exception
    {
        listener1 = mockery.mock(AnalysisListener.class, "1");
        listener2 = mockery.mock(AnalysisListener.class, "2");
        chain = new AnalysisListenerChain(listener1, listener2);

    }
}
