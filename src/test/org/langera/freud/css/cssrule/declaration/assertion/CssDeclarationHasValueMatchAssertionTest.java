package org.langera.freud.css.cssrule.declaration.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AnalysisAssertion;
import org.langera.freud.css.cssrule.CssRule;
import org.langera.freud.css.cssrule.declaration.CssDeclaration;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertion;

public final class CssDeclarationHasValueMatchAssertionTest
{
    private AnalysisAssertion<CssDeclaration> assertion;

    @Test
    public void shouldMatchDeclarationValue()
    {
        Assert.assertTrue(assertion.analyse(new SimpleCssDeclaration("color", "#ff0000")));
    }

    @Test
    public void shouldNotMatchDeclarationValue()
    {
        Assert.assertFalse(assertion.analyse(new SimpleCssDeclaration("color", "red")));
    }

    @Before
    public void setUp()
    {
        assertion = new RegexMatchAnalysisAssertion<CssDeclaration>("#[a-fA-F0-9]{3,6}", true,
                                                                    CssDeclarationValueMatchAssertionAdapter.getInstance());
    }

    private static final class SimpleCssDeclaration implements CssDeclaration
    {
        private final String key;
        private final String value;

        private SimpleCssDeclaration(final String key, final String value)
        {
            this.key = key;
            this.value = value;
        }

        public String getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }

        public CssRule getCssRuleForDeclaration()
        {
            return null;
        }
    }
}
