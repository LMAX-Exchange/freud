package org.langera.freud.optional.css.cssrule.selector;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.css.Css;
import org.langera.freud.optional.css.cssrule.CssRule;

import java.util.ArrayList;
import java.util.List;

public final class CssSelectorFreudConfig implements FreudConfig<CssSelector>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CssSelector> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (Css.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<Css, CssSelector>((AnalysedObjectIterator<Css>) superTypeIterator,
                    new SubTypeIteratorBuilder<Css, CssSelector>()
                    {
                        @Override
                        public Iterable<CssSelector> buildIterable(final Css superTypeItem)
                        {
                            List<CssSelector> collector = new ArrayList<CssSelector>();
                            for (CssRule cssRule : superTypeItem.getCssRuleList())
                            {
                                collector.addAll(cssRule.getCssSelectorList());
                            }
                            return collector;
                        }
                    }, CssSelector.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over CssSelector objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }
}
