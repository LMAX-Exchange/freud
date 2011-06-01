package org.langera.freud.optional.css.cssrule.declaration;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationKeyContains;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationKeyMatches;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationValueContains;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationMatchers.declarationValueMatches;

public final class CssDeclarationMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKeyMatches("d.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKeyMatches("n.+")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKeyContains("d"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKeyContains("n")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValueMatches("n.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValueMatches("d.+")));
    }


    @Test
    public void shouldReturnTrueToAContainedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValueContains("n"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValueContains("d")));
    }
}
