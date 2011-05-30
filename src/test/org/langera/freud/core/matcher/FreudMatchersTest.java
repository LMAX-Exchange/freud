package org.langera.freud.core.matcher;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.and;
import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.core.matcher.FreudMatchers.or;

public final class FreudMatchersTest
{
    @Test
    public void shouldReturnTrueWhenOriginalMatchesFailed()
    {
        Assert.assertTrue(no(Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenOriginalMatchesSucceeded()
    {
        Assert.assertFalse(no(Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenFirstMatchSucceeded()
    {
        Assert.assertTrue(or(Matchers.startsWith("a"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenOnlyFirstMatchSucceeded()
    {
        Assert.assertFalse(and(Matchers.startsWith("a"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenBothMatchesSucceededOnOr()
    {
        Assert.assertTrue(or(Matchers.startsWith("a"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenBothMatchesSucceededOnAnd()
    {
        Assert.assertTrue(and(Matchers.startsWith("a"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenBothMatchesFailedOnOr()
    {
        Assert.assertFalse(or(Matchers.startsWith("b"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseeWhenBothMatchesFailedOnAnd()
    {
        Assert.assertFalse(and(Matchers.startsWith("b"), Matchers.startsWith("b")).matches("aa"));
    }

    @Test
    public void shouldReturnTrueWhenSecondMatchesSucceeded()
    {
        Assert.assertTrue(or(Matchers.startsWith("b"), Matchers.startsWith("a")).matches("aa"));
    }

    @Test
    public void shouldReturnFalseWhenSecondMatchesSucceeded()
    {
        Assert.assertFalse(and(Matchers.startsWith("b"), Matchers.startsWith("a")).matches("aa"));
    }
}
