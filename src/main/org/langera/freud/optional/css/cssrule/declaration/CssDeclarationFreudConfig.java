package org.langera.freud.optional.css.cssrule.declaration;

import org.langera.freud.core.FreudBuilderException;
import org.langera.freud.core.FreudConfig;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeAnalysedObjectIterator;
import org.langera.freud.core.iterator.SubTypeIteratorBuilder;
import org.langera.freud.optional.css.cssrule.CssRule;

public final class CssDeclarationFreudConfig implements FreudConfig<CssDeclaration>
{

    @Override
    public Class<?> supports()
    {
        return CssRule.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnalysedObjectIterator<CssDeclaration> iteratorAdapter(final AnalysedObjectIterator<?> superTypeIterator) throws FreudBuilderException
    {
        return new SubTypeAnalysedObjectIterator<CssRule, CssDeclaration>((AnalysedObjectIterator<CssRule>) superTypeIterator,
                new SubTypeIteratorBuilder<CssRule, CssDeclaration>()
                {
                    @Override
                    public Iterable<CssDeclaration> buildIterable(final CssRule superTypeItem)
                    {
                        return superTypeItem.getCssDeclarationList();
                    }
                }, CssDeclaration.class);
    }
}
