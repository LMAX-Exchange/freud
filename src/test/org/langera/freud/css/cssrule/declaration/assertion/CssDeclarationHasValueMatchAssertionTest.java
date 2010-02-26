package org.langera.freud.css.cssrule.declaration.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;

public final class CssDeclarationHasValueMatchAssertionTest
{
    private CssDeclarationHasValueMatchAssertion assertion;

    @Test
    public void shouldMatchDeclarationValue()
    {
        Assert.assertTrue(assertion.analyse(new SimeplCssDeclaration("color", "#ff0000")));   
    }

    @Test
    public void shouldNotMatchDeclarationValue()
    {
        Assert.assertFalse(assertion.analyse(new SimeplCssDeclaration("color", "red")));
    }

    @Before
    public void setUp()
    {
        assertion = new CssDeclarationHasValueMatchAssertion("#[a-fA-F0-9]{3,6}");
    }

    private static final class SimeplCssDeclaration implements CssDeclaration
    {
        private final String key;
        private final String[] values;

        private SimeplCssDeclaration(final String key, final String... values)
        {
            this.key = key;
            this.values = values;
        }

        public String getKey()
        {
            return key;
        }

        public String[] getValues()
        {
            return values;
        }

        public CssRule getCssRuleForDeclaration()
        {
            return null;
        }
    }
}
