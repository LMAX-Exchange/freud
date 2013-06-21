package examples.org.freud;

import org.freud.core.listener.AnalysisListener;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

final class AnalysisListenerStub implements AnalysisListener {

    private Deque<Object> passed = new LinkedList<Object>();
    private Deque<Object> failed = new LinkedList<Object>();
    private Deque<Object> filtered = new LinkedList<Object>();
    private Deque<Exception> unexpected = new LinkedList<Exception>();

    @Override
    public void passed(final Object analysedObject) {
        passed.add(analysedObject);
    }

    @Override
    public void failed(final Object analysedObject, final String details) {
        failed.add(analysedObject);
    }

    @Override
    public void filtered(final Object analysedObject, final String details) {
        filtered.add(analysedObject);
    }

    @Override
    public void unexpected(final Object analysedObject, final Exception exception) {
        unexpected.add(exception);
    }

    @Override
    public void done() {
    }

    public void assertPassed(int total) {
        Assert.assertEquals("passed count", total, passed.size());
    }

    public void assertFailed(int total) {
        Assert.assertEquals("failed count", total, failed.size());
    }

    public void assertFiltered(int total) {
        Assert.assertEquals("filtered count", total, filtered.size());
    }

    public void assertNotPassed() {
        assertPassed(0);
    }

    public void assertNotFailed() {
        assertFailed(0);
    }

    public void assertNotFiltered() {
        assertFiltered(0);
    }

    public void assertPassed(Object analysed) {
        assertOutcome(getMatcher(analysed), passed, "passed");
    }

    public void assertFailed(Object analysed) {
        assertOutcome(getMatcher(analysed), failed, "failed");
    }

    public void assertFiltered(Object analysed) {
        assertOutcome(getMatcher(analysed), filtered, "filtered");
    }

    public int getTotalObjectsAnalysed() {
        return passed.size() + failed.size() + filtered.size() + unexpected.size();
    }

    public Deque<Object> getPassed() {
        return passed;
    }

    public Deque<Object> getFailed() {
        return failed;
    }

    public Deque<Object> getFiltered() {
        return filtered;
    }

    public Deque<Exception> getUnexpected() {
        return unexpected;
    }

    @SuppressWarnings("unchecked")
    private void assertOutcome(Matcher matcher, final Deque<Object> deque, String eventDescription) {
        for (Object analysedObject : deque) {
            if (matcher.matches(analysedObject)) {
                return;
            }
        }
        StringDescription description = new StringDescription();
        matcher.describeTo(description);
        Assert.fail(eventDescription + ": " + description.toString());
    }

    private Matcher getMatcher(Object analysed) {
        return (analysed instanceof Matcher) ? (Matcher) analysed : Matchers.equalTo(analysed);
    }
}
