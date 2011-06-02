package org.langera.freud.optional.css.cssrule.declaration;

import org.junit.Assert;
import org.junit.Test;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationKey;
import static org.langera.freud.optional.css.cssrule.declaration.CssDeclarationDsl.declarationValue;

public final class CssDeclarationDslTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKey().matches("d.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKey().matches("n.+")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationKey().contains("d"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexForAKey()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationKey().contains("n")));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValue().matches("n.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValue().matches("d.+")));
    }


    @Test
    public void shouldReturnTrueToAContainsRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), declarationValue().contains("n"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegexForAValue()
    {
        Assert.assertThat(new CssDeclarationStub("display", "none", null), no(declarationValue().contains("d")));
    }
}
