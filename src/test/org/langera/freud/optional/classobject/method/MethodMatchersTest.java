package org.langera.freud.optional.classobject.method;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.classobject.method.MethodMatchers.methodNameMatches;

public final class MethodMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), methodNameMatches("should.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), no(methodNameMatches("a.*")));
    }
}
