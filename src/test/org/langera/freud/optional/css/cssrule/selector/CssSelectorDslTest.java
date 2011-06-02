package org.langera.freud.optional.css.cssrule.selector;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.CLASS;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.ID;
import static org.langera.freud.optional.css.cssrule.selector.CssSelector.Type.TAG;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.classSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.idSelector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.selector;
import static org.langera.freud.optional.css.cssrule.selector.CssSelectorDsl.tagSelector;

public final class CssSelectorDslTest
{

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), selector().matches("m.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(selector().matches("x.+")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), selector().contains("m"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(new CssSelectorStub(CLASS, "my-class", null), no(selector().contains("x")));
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
