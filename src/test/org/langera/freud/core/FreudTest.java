package org.langera.freud.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.IteratorWrapperAnalysedObjectIterator;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListener;
import org.langera.freud.core.listener.NoOpAnalysisListener;
import org.langera.freud.optional.text.Text;
import org.langera.freud.optional.text.TextResourceParser;
import org.langera.freud.optional.text.textline.TextLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.langera.freud.optional.text.textline.TextLineMatchers.lineLength;

public final class FreudTest
{

    private static final List<String> A_B_C = Arrays.asList("a", "b", "c");

    @Test
    public void shouldBuildTestToAnalyse() throws Exception
    {
        final TypeSafeMatcher<String> filter = filterOut_c();
        final TypeSafeMatcher<String> assertion = assert_a();
        final List<String> analysedObjectCollector = new ArrayList<String>();
        final boolean[] isDone = {false};
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


    @Test
    public void shouldKeepCurrentAnalysedItemsInMemory() throws Exception
    {
        final TypeSafeMatcher<String> filter = filterOut_c();
        final TypeSafeMatcher<String> assertion = assert_a();

        Freud.iterateOver(String.class).in(iterator(A_B_C)).
                forEach(filter).
                assertThat(assertion).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("a", Freud.getCurrentlyAnalysed(String.class));
            }

            @Override
            public void unexpected(final Object analysedObject, final Exception exception)
            {
                exception.printStackTrace();
                Assert.fail();
            }
        });
    }

    @Test
    public void shouldKeepCurrentlyIteratedSuperTypesInMemoryAsWellAsType() throws Exception
    {
        Freud.iterateOver(TextLine.class).within(ResourceIterators.selfResourceIterator(TextResourceParser.getInstance(), "text")).
                assertThat(lineLength().lessThan(17)).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("text", Freud.getCurrentlyAnalysed(Text.class).getText());
                Assert.assertEquals("text", Freud.getCurrentlyAnalysed(TextLine.class).getLine());
            }

            @Override
            public void unexpected(final Object analysedObject, final Exception exception)
            {
                exception.printStackTrace();
                Assert.fail();
            }
        });


    }


    @Test
    public void shouldReturnNullAsCurrentAnlysedIfNotIteratingOverThatType() throws Exception
    {
        final TypeSafeMatcher<String> filter = filterOut_c();
        final TypeSafeMatcher<String> assertion = assert_a();

        Freud.iterateOver(String.class).in(iterator(A_B_C)).
                forEach(filter).
                assertThat(assertion).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertNull(Freud.getCurrentlyAnalysed(Integer.class));
            }

            @Override
            public void unexpected(final Object analysedObject, final Exception exception)
            {
                exception.printStackTrace();
                Assert.fail();
            }
        });
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
