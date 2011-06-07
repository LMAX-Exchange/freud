package org.langera.freud.optional.css.cssrule.selector;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.css.cssrule.CssRule;

public final class CssSelectorFreudConfig implements FreudConfig<CssSelector>
{

    @Override
    public Class<?> supports()
    {
        return CssRule.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CssSelector> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<CssRule, CssSelector>((AnalysedObjectIterator<CssRule>) superTypeIterator,
                new SubTypeIteratorBuilder<CssRule, CssSelector>()
                {
                    @Override
                    public Iterable<CssSelector> buildIterable(final CssRule superTypeItem)
                    {
                        return superTypeItem.getCssSelectorList();
                    }
                }, CssSelector.class);
    }
}
