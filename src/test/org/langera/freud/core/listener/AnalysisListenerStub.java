package org.langera.freud.core.listener;

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
