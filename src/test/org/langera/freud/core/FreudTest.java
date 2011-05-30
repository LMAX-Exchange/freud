package org.langera.freud.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.IteratorWrapperAnalysedObjectIterator;
import org.langera.freud.core.listener.AnalysisListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FreudTest
{

    private static final List<String> A_B_C = Arrays.asList("a", "b", "c");

    @Test
    public void shouldBuildTestToAnalyse()  throws Exception
    {
        final TypeSafeMatcher<String> filter = filterOut_c();
        final TypeSafeMatcher<String> assertion = assert_a();
        final List<String> analysedObjectCollector = new ArrayList<String>();
        final boolean[] isDone = { false };
        final List<String> expected = A_B_C;

        Freud.iterateOver(String.class).in(iterator(A_B_C)).
                forEach(filter).
                assertThat(assertion).analyse(new AnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("a", analysedObject);
                Assert.assertEquals(assertion, matcher);
                analysedObjectCollector.add((String) analysedObject);
            }

            @Override
            public void failed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("b", analysedObject);
                Assert.assertEquals(assertion, matcher);
                analysedObjectCollector.add((String) analysedObject);
            }

            @Override
            public void filtered(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("c", analysedObject);
                Assert.assertEquals(filter, matcher);
                analysedObjectCollector.add((String) analysedObject);
            }

            @Override
            public void unexpected(final Object analysedObject, final Exception exception)
            {
                Assert.fail();
            }

            @Override
            public void done()
            {
                isDone[0] = true;
            }
        });

        Assert.assertEquals(expected, analysedObjectCollector);
        Assert.assertTrue(isDone[0]);
    }

    private TypeSafeMatcher<String> assert_a()
    {
        return new TypeSafeMatcher<String>()
        {
            @Override
            protected boolean matchesSafely(final String item)
            {
                return "a".equals(item);
            }

            @Override
            public void describeTo(final Description description)
            {
            }
        };
    }

    private TypeSafeMatcher<String> filterOut_c()
    {
        return new TypeSafeMatcher<String>()
        {
            @Override
            protected boolean matchesSafely(final String item)
            {
                return !"c".equals(item);
            }

            @Override
            public void describeTo(final Description description)
            {
            }
        };
    }

    private AnalysedObjectIterator<String> iterator(final List<String> stringList)
    {
        return new IteratorWrapperAnalysedObjectIterator<String>(stringList, String.class, false);
    }
}
