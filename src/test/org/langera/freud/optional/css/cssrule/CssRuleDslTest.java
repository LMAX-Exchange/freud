package org.langera.freud.optional.css.cssrule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.optional.css.cssrule.declaration.CssDeclarationStub;
import org.langera.freud.optional.css.cssrule.selector.CssSelectorStub;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.containsSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.lastIndexOfSelector;
import static org.langera.freud.optional.css.cssrule.CssRuleDsl.selectors;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.CLASS;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.ID;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.TAG;

public final class CssRuleDslTest
{
    private CssRuleStub cssRule;

    @Test
    public void shouldReturnTrueWhenTheRuleContainsASelectorOfTypeClass()
    {
        Assert.assertThat(cssRule, containsSelector(CLASS));
    }

    @Test
    public void shouldReturnFalseWhenTheRuleContainsASelectorOfTypeId()
    {
        Assert.assertThat(cssRule, no(containsSelector(ID)));
    }

    @Test
    public void shouldReturnTrueWhenSelectorsCountIsRight()
    {
        Assert.assertThat(cssRule, selectors().equalTo(2));
    }

    @Test
    public void shouldReturnFalseWhenSelectorsCountIsWrong()
    {
        Assert.assertThat(cssRule, no(selectors().equalTo(3)));
    }

    @Test
    public void shouldReturnTrueWhenSelectorsOfTypeClassCountIsRight()
    {
        Assert.assertThat(cssRule, selectors(CLASS).equalTo(1));
    }

    @Test
    public void shouldReturnFalseWhenSelectorsOfTypeClassCountIsWrong()
    {
        Assert.assertThat(cssRule, no(selectors(CLASS).equalTo(3)));
    }

    @Test
    public void shouldReturnTrueWhenLastIndexOfTypeClassCountIsRight()
    {
        Assert.assertThat(cssRule, lastIndexOfSelector(CLASS).equalTo(0));
    }

    @Test
    public void shouldReturnTrueWhenLastIndexOfATypeWhichDoesNotExistIsMinusOne()
    {
        Assert.assertThat(cssRule, lastIndexOfSelector(ID).equalTo(-1));
    }

    @Test
    public void shouldReturnFalseWhenLastIndexOfTypeClassCountIsWrong()
    {
        Assert.assertThat(cssRule, no(lastIndexOfSelector(CLASS).equalTo(1)));
    }


    @Before
    public void setUp()
    {
        cssRule = new CssRuleStub().
                cssSelectors(new CssSelectorStub(CLASS, "1", null),
                        new CssSelectorStub(TAG, "2", null)).
                cssDeclarations(new CssDeclarationStub("align", "right", null));
    }
}
