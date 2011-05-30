package org.langera.freud.core.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class IntOperatorMatcherBuilderTest
{
    private IntOperatorMatcherBuilder<String> matcherBuilder;

    @Test
    public void shouldBuildAnEqualToMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.equalTo(17);

        Assert.assertThat("17", matcher);
    }


    @Test
    public void shouldBuildALessThanMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.lessThan(17);

        Assert.assertThat("13", matcher);
    }


    @Test
    public void shouldBuildAnGreaterThanMatcher()
    {
        final Matcher<String> matcher = matcherBuilder.greaterThan(17);

        Assert.assertThat("19", matcher);
    }



    @Before
    public void setUp()
    {
        IntOperatorMatcherAdapter<String> adapter = new IdentityIntOperatoerMatcherAdapter();
        matcherBuilder = new IntOperatorMatcherBuilder<String>(adapter);
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
            return "text";
        }
    }
}
