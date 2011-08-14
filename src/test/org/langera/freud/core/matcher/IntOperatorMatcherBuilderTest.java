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

package org.langera.freud.core.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;

public final class IntOperatorMatcherBuilderTest
{
    private IntOperatorMatcherBuilder<String> matcherBuilder;
    private IntOperatorMatcherBuilder<String> stringValueTimesTwo;

    @Test
    public void shouldBuildAnEqualToMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.equalTo(17);

        Assert.assertThat("17", matcher);
        Assert.assertThat("16", no(matcher));
    }

    @Test
    public void shouldBuildAnEqualToMatcherUsingAnAdapterParam()
    {
        final Matcher<String> matcher = matcherBuilder.equalTo(stringValueTimesTwo);

        Assert.assertThat("17", no(matcher));
        Assert.assertThat("0", matcher);
    }

    @Test
    public void shouldBuildALessThanMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.lessThan(17);

        Assert.assertThat("13", matcher);
        Assert.assertThat("18", no(matcher));
    }

    @Test
    public void shouldBuildALessThanMatcherUsingAnAdapterParam()
    {
        final Matcher<String> matcher = matcherBuilder.lessThan(stringValueTimesTwo);

        Assert.assertThat("17", matcher);
        Assert.assertThat("-17", no(matcher));
    }

    @Test
    public void shouldBuildAnGreaterThanMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.greaterThan(17);

        Assert.assertThat("19", matcher);
        Assert.assertThat("16", no(matcher));
    }

    @Test
    public void shouldBuildAnGreaterThanMatcherUsingAnAdapterParam()
    {
        final Matcher<String> matcher = matcherBuilder.greaterThan(stringValueTimesTwo);

        Assert.assertThat("-17", matcher);
        Assert.assertThat("17", no(matcher));
    }

    @Test
    public void shouldBuildALessThanOrEqualToMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.lessThanOrEqualTo(17);

        Assert.assertThat("13", matcher);
        Assert.assertThat("17", matcher);
        Assert.assertThat("18", no(matcher));
    }

    @Test
    public void shouldBuildALessThanOrEqualToMatcherUsingAnAdapterParam()
    {
        final Matcher<String> matcher = matcherBuilder.lessThanOrEqualTo(stringValueTimesTwo);

        Assert.assertThat("17", matcher);
        Assert.assertThat("-17", no(matcher));
        Assert.assertThat("0", matcher);
    }

    @Test
    public void shouldBuildAnGreaterThanOrEqualToMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.greaterThanOrEqualTo(17);

        Assert.assertThat("19", matcher);
        Assert.assertThat("17", matcher);
        Assert.assertThat("16", no(matcher));
    }

    @Test
    public void shouldBuildAnGreaterThanOrEqualToMatcherUsingAnAdapterParam()
    {
        final Matcher<String> matcher = matcherBuilder.greaterThanOrEqualTo(stringValueTimesTwo);

        Assert.assertThat("17", no(matcher));
        Assert.assertThat("-17", matcher);
        Assert.assertThat("0", matcher);
    }


    @Before
    public void setUp()
    {
        matcherBuilder = new IntOperatorMatcherBuilder<String>(new IdentityIntOperatoerMatcherAdapter());
        stringValueTimesTwo = new IntOperatorMatcherBuilder<String>(new IdentityTimesTwoIntOperatoerMatcherAdapter());
    }

    private static class IdentityIntOperatoerMatcherAdapter implements IntOperatorMatcherAdapter<String>
    {
        @Override
        public int valueOf(final String matched)
        {
            return Integer.parseInt(matched);
        }

        @Override
        public String matcherDisplayName()
        {
            return "identity";
        }
    }

    private static class IdentityTimesTwoIntOperatoerMatcherAdapter implements IntOperatorMatcherAdapter<String>
    {
        @Override
        public int valueOf(final String matched)
        {
            return Integer.parseInt(matched) * 2;
        }

        @Override
        public String matcherDisplayName()
        {
            return "identity * 2";
        }
    }
}
