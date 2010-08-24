package org.langera.freud.css.cssrule.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRuleStub;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorStub;

import java.util.Arrays;

public final class HasSelectorTypeAssertionTest
{
    private HasSelectorTypeAssertion assertion;
    private CssRuleStub cssRule;

    @Test
    public void shouldReturnTrueWhenSelectorTypeExistInRule()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule)
        ));

        Assert.assertTrue(assertion.matches(cssRule));
    }

    @Test
    public void shouldReturnFalseWhenSelectorTypeDoesNotExistInRule()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));

        Assert.assertFalse(assertion.matches(cssRule));
    }

    @Before
    public void setUp()
    {
        assertion = new HasSelectorTypeAssertion(CssSelector.Type.TAG);
        cssRule = new CssRuleStub();
    }
}
