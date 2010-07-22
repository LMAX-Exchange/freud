package org.langera.freud.css.cssrule.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRuleStub;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorStub;

import java.util.Arrays;

public final class SelectorTypeLastIndexCalculationTest
{
    private SelectorTypeLastIndexCalculation tagLastIndexCalculation;
    private CssRuleStub cssRule;

    @Test
    public void shouldReturnNegativeOneIfSelectorTypeNotInList()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));

        Assert.assertEquals(-1, tagLastIndexCalculation.analyse(cssRule));
    }

    @Test
    public void shouldReturnLastIndexOfSelectorType()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule),
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule),
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));

        Assert.assertEquals(4, tagLastIndexCalculation.analyse(cssRule));
    }

    @Before
    public void setUp()
    {
        cssRule = new CssRuleStub();
        tagLastIndexCalculation = new SelectorTypeLastIndexCalculation(CssSelector.Type.TAG);
    }
}
