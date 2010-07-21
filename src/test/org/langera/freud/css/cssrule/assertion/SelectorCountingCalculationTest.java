package org.langera.freud.css.cssrule.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRuleStub;
import org.langera.freud.css.cssrule.selector.CssSelector;
import org.langera.freud.css.cssrule.selector.CssSelectorStub;

import java.util.Arrays;

public final class SelectorCountingCalculationTest
{
    private SelectorCountingCalculation allSelectorsCalculation;
    private SelectorCountingCalculation tagSelectorsCalculation;
    private CssRuleStub cssRule;

    @Test
    public void shouldCountNumberOfTagsSelectorsInRule()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule),
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));
        Assert.assertEquals(1, tagSelectorsCalculation.analyse(cssRule));
    }

    @Test
    public void shouldCountNumberOfSelectorInRule()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.TAG, "testTag", cssRule),
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));

        Assert.assertEquals(3, allSelectorsCalculation.analyse(cssRule));
    }

    @Test
    public void shouldCountNumberOfTagsSelectorsInRuleWithNoTagSelectors()
    {
        cssRule.setCssSelectorList(Arrays.<CssSelector>asList(
                new CssSelectorStub(CssSelector.Type.CLASS, "testClass", cssRule),
                new CssSelectorStub(CssSelector.Type.ID, "testId", cssRule)
        ));

        Assert.assertEquals(0, tagSelectorsCalculation.analyse(cssRule));
    }

    @Before
    public void setUp()
    {
        cssRule = new CssRuleStub();
        allSelectorsCalculation = new SelectorCountingCalculation();
        tagSelectorsCalculation = new SelectorCountingCalculation(CssSelector.Type.TAG);
    }
}
