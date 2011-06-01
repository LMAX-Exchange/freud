package org.langera.freud.optional.css.cssrule;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.css.Css;

public final class CssRuleFreudConfig implements FreudConfig<CssRule>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CssRule> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (Css.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<Css, CssRule>((AnalysedObjectIterator<Css>) superTypeIterator,
                    new SubTypeIteratorBuilder<Css, CssRule>()
                    {
                        @Override
                        public Iterable<CssRule> buildIterable(final Css superTypeItem)
                        {
                            return superTypeItem.getCssRuleList();
                        }
                    }, CssRule.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over CssRule objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }
}
