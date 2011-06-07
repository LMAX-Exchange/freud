package org.langera.freud.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.listener.AnalysisListener;
import org.langera.freud.core.matcher.FreudMatcher;

import java.util.HashMap;
import java.util.Map;

public final class Freud<T> implements FreudIteration<T>, FreudRule<T>, FreudAssertionAndFilter<T>, FreudAnalyser
{
    public static final String FREUD_CONFIG_SUFFIX = "FreudConfig";
    private Class<T> type;
    private AnalysedObjectIterator<T> iterator;
    private Matcher<T> filter;
    private Matcher<T> assertion;
    private Map<Class, FreudConfig> configByTypeMap = new HashMap<Class, FreudConfig>();

    public Freud(final Class<T> type)
    {
        this.type = type;
        FreudRuntimeContext.clear();
    }

    @SuppressWarnings("unchecked")
    public static <T> FreudIteration<T> iterateOver(Class<T> type)
    {
        return new Freud<T>(type);
    }

    @Override
    public FreudRule<T> in(final AnalysedObjectIterator<T> iterator)
    {
        this.iterator = iterator;
        FreudRuntimeContext.register(iterator);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FreudRule<T> within(final AnalysedObjectIterator<?> iterator)
    {
        FreudRuntimeContext.register(iterator);
        this.iterator = adapter(iterator);
        FreudRuntimeContext.register(this.iterator);
        return this;
    }

    @Override
    public <S> FreudAssertionAndFilter<T> of(final Matcher<S> superTypeMatcher, final Class<S> superType)
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
    public FreudAssertionAndFilter<T> forEach()
    {
        filter = trueMatcher();
        return this;
    }

    @Override
    public FreudAssertionAndFilter<T> forEach(final Matcher<T> matcher)
    {
        filter = matcher;
        return this;
    }

    @Override
    public FreudAnalyser assertThat(final Matcher<T> matcher)
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
        listener.done();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

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
        FreudConfig<T> config = configOf(type);
        final Class<?> superTypeForT = config.supports();
        if (superTypeForT == null || iterator.analysedObjectType().equals(superTypeForT))
        {
            return config.iteratorAdapter(iterator);
        }
        else
        {
            final AnalysedObjectIterator<?> superTypeIterator = configOf(superTypeForT).iteratorAdapter(iterator);
            FreudRuntimeContext.register(superTypeIterator);
            return config.iteratorAdapter(superTypeIterator);
        }
    }

    @SuppressWarnings("unchecked")
    private <TT> FreudConfig<TT> configOf(Class<TT> type)
    {
        FreudConfig<TT> config = configByTypeMap.get(type);
        if (config == null)
        {
            config = loadConfig(type);
            configByTypeMap.put(type, config);
        }
        return config;
    }

    @SuppressWarnings("unchecked")
    private static <T> FreudConfig<T> loadConfig(final Class<T> type)
    {
        try
        {
            final String name = type.getName() + FREUD_CONFIG_SUFFIX;
            String classname = System.getProperty(name);
            if (classname == null)
            {
                classname = name;
            }
                Class configClass = Thread.currentThread().getContextClassLoader().loadClass(classname);

                return (FreudConfig<T>) configClass.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            return DefaultFreudConfig.getInstance();
        }
        catch (InstantiationException e)
        {
            throw new FreudBuilderException("Could not load config class for " + type, e);

        }
        catch (IllegalAccessException e)
        {
            throw new FreudBuilderException("Could not load config class for " + type, e);
        }
    }


}
