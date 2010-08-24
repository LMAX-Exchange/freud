package org.langera.freud;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 09-Oct-2008
 * Time: 09:11:43
 */
public class TestAnalysisListenerStub implements AnalysisListener
{
    private Deque<Event> passed = new LinkedList<Event>();
    private Deque<Event> failed = new LinkedList<Event>();
    private Deque<Event> filtered = new LinkedList<Event>();
    private Deque<Event> unexpected = new LinkedList<Event>();

    public void failed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        failed.addLast(new Event(toBeAnalysed, typeSafeMatcher));
    }

    public void filtered(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        filtered.addLast(new Event(toBeAnalysed, typeSafeMatcher));
    }

    public void passed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        passed.addLast(new Event(toBeAnalysed, typeSafeMatcher));
    }

    public void unexpected(Object toBeAnalysed, Exception exception)
    {
        exception.printStackTrace();
        unexpected.addLast(new Event(exception, null));
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

    public Deque<Event> getPassed()
    {
        return passed;
    }

    public Deque<Event> getFailed()
    {
        return failed;
    }

    public Deque<Event> getFiltered()
    {
        return filtered;
    }

    public Deque<Event> getUnexpected()
    {
        return unexpected;
    }

    @SuppressWarnings("unchecked")
    private void assertOutcome(Matcher analysed, final Deque<Event> deque, String eventDescription)
    {
        for (Event event : deque)
        {
            if (analysed.matches(event.getAnalysed()))
            {
                return;
            }
        }
        StringDescription description = new StringDescription();
        analysed.describeTo(description);
        Assert.fail(eventDescription + ": " + description.toString());
    }

    private Matcher getMatcher(Object analysed)
    {
        return (analysed instanceof Matcher) ? (Matcher) analysed : Matchers.equalTo(analysed);
    }

    @Deprecated
    public Deque<Event> getEventsOccured()
    {
        return null;// TODO delete
//        return eventsOccured;
    }

    public static class Event
    {
        private Object analysed;
        private Matcher assertion;

        public Event(Object analysed, Matcher assertion)
        {
            this.analysed = analysed;
            this.assertion = assertion;
        }

        public Object getAnalysed()
        {
            return analysed;
        }

        public Matcher getAssertion()
        {
            return assertion;
        }

        public String getEvent()
        {
            return null;
        }
    }
}
