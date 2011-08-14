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

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

public class AnalysisListenerStub implements AnalysisListener
{
    private Deque<Object> passed = new LinkedList<Object>();
    private Deque<Object> failed = new LinkedList<Object>();
    private Deque<Object> filtered = new LinkedList<Object>();
    private Deque<Exception> unexpected = new LinkedList<Exception>();

    @Override
    public void failed(Object analysedObject, Matcher typeSafeMatcher)
    {
        failed.addLast(analysedObject);
    }

    @Override
    public void filtered(Object analysedObject, Matcher typeSafeMatcher)
    {
        filtered.addLast(analysedObject);
    }

    @Override
    public void passed(Object analysedObject, Matcher typeSafeMatcher)
    {
        passed.addLast(analysedObject);
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception)
    {
        exception.printStackTrace();
        unexpected.addLast(exception);
    }

    @Override
    public void done()
    {
        // do nothing
    }

    public void assertPassed(Object analysed)
    {
        assertOutcome(getMatcher(analysed), passed, "passed");
    }

    public void assertFailed(Object analysed)
    {
        assertOutcome(getMatcher(analysed), failed, "failed");
    }

    public void assertFiltered(Object analysed)
    {
        assertOutcome(getMatcher(analysed), filtered, "filtered");
    }

    public int getTotalObjectsAnalysed()
    {
        return passed.size() + failed.size() + filtered.size() + unexpected.size();
    }

    public Deque<Object> getPassed()
    {
        return passed;
    }

    public Deque<Object> getFailed()
    {
        return failed;
    }

    public Deque<Object> getFiltered()
    {
        return filtered;
    }

    public Deque<Exception> getUnexpected()
    {
        return unexpected;
    }

    @SuppressWarnings("unchecked")
    private void assertOutcome(Matcher matcher, final Deque<Object> deque, String eventDescription)
    {
        for (Object analysedObject : deque)
        {
            if (matcher.matches(analysedObject))
            {
                return;
            }
        }
        StringDescription description = new StringDescription();
        matcher.describeTo(description);
        Assert.fail(eventDescription + ": " + description.toString());
    }

    private Matcher getMatcher(Object analysed)
    {
        return (analysed instanceof Matcher) ? (Matcher) analysed : Matchers.equalTo(analysed);
    }
}
