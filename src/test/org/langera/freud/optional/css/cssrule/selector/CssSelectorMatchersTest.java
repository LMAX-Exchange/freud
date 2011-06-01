package org.langera.freud.optional.css.cssrule.selector;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.CLASS;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.ID;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.TAG;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.classSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.idSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.selectorContains;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.selectorMatches;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorMatchers.tagSelector;

public final class CssSelectorMatchersTest
{

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), selectorMatches("m.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(selectorMatches("x.+")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), selectorContains("m"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(selectorContains("x")));
    }

    @Test
    public void shouldReturnTrueToAClassSelector()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), classSelector());
    }

    @Test
    public void shouldReturnFalseToANonClassSelector()
    {
        Assert.assertThat(new CssSelectorStub(TAG, "my-tag", null), no(classSelector()));
    }

    @Test
    public void shouldReturnTrueToATagSelector()
    {
        Assert.assertThat(new CssSelectorStub(TAG, "my-tag", null), tagSelector());
    }

    @Test
    public void shouldReturnFalseToANonTagSelector()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(tagSelector()));
    }

    @Test
    public void shouldReturnTrueToAnIdSelector()
    {
        Assert.assertThat(new CssSelectorStub(ID, "my-id", null), idSelector());
    }

    @Test
    public void shouldReturnFalseToANonIdSelector()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(idSelector()));
    }
}
