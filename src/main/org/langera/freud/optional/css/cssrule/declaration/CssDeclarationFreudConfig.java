package org.langera.freud.optional.css.cssrule.declaration;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.css.Css;
import org.langera.freud.optional.css.cssrule.CssRule;

import java.util.ArrayList;
import java.util.List;

public final class CssDeclarationFreudConfig implements FreudConfig<CssDeclaration>
{
    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CssDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        if (Css.class.equals(superTypeIterator.analysedObjectType()))
        {
            return new SubTypeAnalysedObjectIterator<Css, CssDeclaration>((AnalysedObjectIterator<Css>) superTypeIterator,
                    new SubTypeIteratorBuilder<Css, CssDeclaration>()
                    {
                        @Override
                        public Iterable<CssDeclaration> buildIterable(final Css superTypeItem)
                        {
                            List<CssDeclaration> collector = new ArrayList<CssDeclaration>();
                            for (CssRule cssRule : superTypeItem.getCssRuleList())
                            {
                                collector.addAll(cssRule.getCssDeclarationList());
                            }
                            return collector;
                        }
                    }, CssDeclaration.class);
        }
        else
        {
            throw new FreudBuilderException("Cannot iterate over CssDeclaration objects from [" +
                    superTypeIterator.analysedObjectType() + "] iterator.");
        }
    }
}
