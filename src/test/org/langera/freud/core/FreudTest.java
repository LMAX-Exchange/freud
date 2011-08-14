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

package org.langera.freud.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.core.iterator.AbstractAnalysedObjectIterator;
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

import static org.langera.freud.optional.text.textline.TextLineDsl.lineLength;

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

        Freud.iterateOver(String.class).in(iterator(A_B_C, String.class)).
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
    public void shouldBuildTestWithSuperTypeFilters()
    {
        System.setProperty(String.class.getName() + Freud.FREUD_CONFIG_SUFFIX, StringTestFreudConfig.class.getName());

        Freud.iterateOver(String.class).within(iterator(
                        Arrays.<Integer>asList((int)'a', (int)'b', (int)'c'), Integer.class)).
                forEach().of(filterOut_cInteger(), Integer.class).
                assertThat(assert_a()).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void filtered(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("c", analysedObject);
                Assert.assertEquals("(java.lang.Integer Of (Filter C by hashCode)) AND (TRUE)", matcher.toString());
            }

            @Override
            public void unexpected(final Object analysedObject, final Exception exception)
            {
                Assert.fail();
            }
        });
    }


    @Test
    public void shouldKeepCurrentAnalysedItemsInMemory() throws Exception
    {
        final TypeSafeMatcher<String> filter = filterOut_c();
        final TypeSafeMatcher<String> assertion = assert_a();

        Freud.iterateOver(String.class).in(iterator(A_B_C, String.class)).
                forEach(filter).
                assertThat(assertion).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertEquals("a", FreudRuntimeContext.getCurrentlyAnalysed(String.class));
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
                Assert.assertEquals("text", FreudRuntimeContext.getCurrentlyAnalysed(Text.class).getText());
                Assert.assertEquals("text", FreudRuntimeContext.getCurrentlyAnalysed(TextLine.class).getLine());
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

        Freud.iterateOver(String.class).in(iterator(A_B_C, String.class)).
                forEach(filter).
                assertThat(assertion).analyse(new NoOpAnalysisListener()
        {
            @Override
            public void passed(final Object analysedObject, final Matcher matcher)
            {
                Assert.assertNull(FreudRuntimeContext.getCurrentlyAnalysed(Boolean.class));
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
                description.appendText("filter C");
            }
        };
    }

    private TypeSafeMatcher<Integer> filterOut_cInteger()
    {
        return new TypeSafeMatcher<Integer>()
        {
            @Override
            protected boolean matchesSafely(final Integer item)
            {
                return "c".hashCode() != item.intValue();
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("Filter C by hashCode");
            }
        };
    }

    private <T> AnalysedObjectIterator<T> iterator(final List<T> list, Class<T> type)
    {
        return new IteratorWrapperAnalysedObjectIterator<T>(list, type, false);
    }

    static class StringTestFreudConfig implements FreudConfig<String>
    {
        @Override
        public AnalysedObjectIterator<String> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
        {
            return new AbstractAnalysedObjectIterator<String>(String.class, false)
            {
                @Override
                protected String generateNextItem()
                {
                    final Integer next = (Integer) superTypeIterator.next();
                    return (next == null) ? null : String.valueOf(Character.valueOf((char) next.intValue()));
                }
            };
        }

        @Override
        public Class<?> supports()
        {
            return Integer.class;
        }
    }
}
