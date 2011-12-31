/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.IteratorWrapperAnalysedObjectIterator;
import org.langera.freud.core.listener.AnalysisListener;
import org.langera.freud.core.matcher.FreudMatcher;

import java.util.Iterator;

public final class Freud<T> implements
        FreudRule<T>, FreudRuleBuilder<T>,
        FreudAssertionAndFilterBuilder<T>, EmbeddedFreudAnalyser<T>, FreudAnalyser
{
    private Class<T> type;
    private AnalysedObjectIterator<T> iterator;
    private Matcher<T> filter;
    private Matcher<T> assertion;
    private final FreudConfigRegistry freudConfigRegistry = new FreudConfigRegistry();

    public Freud(final Class<T> type)
    {
        this.type = type;
        FreudRuntimeContext.clear();
    }

    public static <T> FreudRuleBuilder<T> iterateOver(Class<T> type)
    {
        return new Freud<T>(type);
    }

    @Override
    public EmbeddedFreudAnalyser<T> embedded()
    {
        return this;
    }

    @Override
    public FreudAnalyser in(final Iterator<T> iterator)
    {
        this.iterator = toAnalysedObjectIterator(iterator, type);
        FreudRuntimeContext.register(this.iterator);
        return this;
    }

    @Override
    public FreudAnalyser within(final AnalysedObjectIterator<?> analysedObjectIterator)
    {
        FreudRuntimeContext.register(analysedObjectIterator);
        this.iterator = adapter(analysedObjectIterator);
        FreudRuntimeContext.register(this.iterator);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S> FreudAnalyser within(final Iterator<S> iterator, final Class<S> iteratedType)
    {
        final AnalysedObjectIterator<T> analysedObjectIterator = toAnalysedObjectIterator(iterator, iteratedType);
        return within(analysedObjectIterator);
    }

    @Override
    public <S> FreudAssertionAndFilterBuilder<T> of(final Matcher<S> superTypeMatcher, final Class<S> superType)
    {
        FreudMatcher<T> superTypeFilterWrapper = new FreudMatcher<T>()
        {
            @Override
            protected boolean matchesSafely(final T item)
            {
                S currentlyAnalysedSuperType = FreudRuntimeContext.getCurrentlyAnalysed(superType);
                return (currentlyAnalysedSuperType != null) &&
                        superTypeMatcher.matches(currentlyAnalysedSuperType);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText(superType.getCanonicalName());
                description.appendText(" Of (");
                superTypeMatcher.describeTo(description);
                description.appendText(")");
            }
        };
        filter = superTypeFilterWrapper.and(filter);
        return this;
    }

    @Override
    public FreudAssertionAndFilterBuilder<T> forEach()
    {
        filter = trueMatcher();
        return this;
    }

    @Override
    public FreudAssertionAndFilterBuilder<T> forEach(final Matcher<T> matcher)
    {
        filter = matcher;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FreudRule<T> assertThat(final Matcher<T> matcher)
    {
        assertion = matcher;
        return this;
    }

    @Override
    public void analyse(final AnalysisListener listener)
    {
        if (listener == null)
        {
            throw new IllegalArgumentException("listener cannot be null");
        }
        iterator.setListener(listener);
        for (T currentlyAnalysed : iterator)
        {
            analyse(listener, currentlyAnalysed);
        }
        listener.done();
    }

    @Override
    public void analyse(final AnalysisListener listener, final T currentlyAnalysed)
    {
        try
        {
            if (filter == null || filter.matches(currentlyAnalysed))
            {
                if (assertion.matches(currentlyAnalysed))
                {
                    listener.passed(currentlyAnalysed, assertion);
                }
                else
                {
                    listener.failed(currentlyAnalysed, assertion);
                }
            }
            else
            {
                listener.filtered(currentlyAnalysed, filter);
            }
        }
        catch (Exception e)
        {
            listener.unexpected(currentlyAnalysed, e);
        }
    }

    @Override
    public Class<T> getType()
    {
        return type;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    private AnalysedObjectIterator<T> toAnalysedObjectIterator(final Iterator<?> iterator, final Class iteratedType)
    {
        if (iterator instanceof AnalysedObjectIterator)
        {
            return (AnalysedObjectIterator<T>) iterator;
        }
        else
        {
            return new IteratorWrapperAnalysedObjectIterator<T>(new Iterable<T>()
            {
                @Override
                public Iterator<T> iterator()
                {
                    return (Iterator<T>) iterator;
                }
            }, iteratedType, true);
        }
    }

    private Matcher<T> trueMatcher()
    {
        return new FreudMatcher<T>()
        {

            @Override
            protected boolean matchesSafely(final T item)
            {
                return true;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("TRUE");
            }
        };
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////

    private AnalysedObjectIterator<T> adapter(final AnalysedObjectIterator<?> iterator)
    {
        FreudConfig<T> config = freudConfigRegistry.configOf(type);
        final Class<?> superTypeForT = config.supports();
        if (superTypeForT == null || iterator.analysedObjectType().equals(superTypeForT))
        {
            return config.iteratorAdapter(iterator);
        }
        else
        {
            final AnalysedObjectIterator<?> superTypeIterator = freudConfigRegistry.configOf(superTypeForT).iteratorAdapter(iterator);
            FreudRuntimeContext.register(superTypeIterator);
            return config.iteratorAdapter(superTypeIterator);
        }
    }
}
