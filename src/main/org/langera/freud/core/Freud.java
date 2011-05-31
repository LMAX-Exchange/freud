package org.langera.freud.core;

import org.hamcrest.Matcher;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.listener.AnalysisListener;

import java.util.HashMap;
import java.util.Map;

public final class Freud<T> implements FreudIteration<T>, FreudRule<T>, FreudAnalyser
{
    public static final String FREUD_CONFIG_SUFFIX = "FreudConfig";
    private Class type;
    private AnalysedObjectIterator<T> iterator;
    private Matcher<T> filter;
    private Matcher<T> assertion;
    private FreudConfig<T> config;

    private static final ThreadLocal<Map<Class, AnalysedObjectIterator>> CONTEXT =
            new ThreadLocal<Map<Class, AnalysedObjectIterator>>()
            {
                @Override
                protected Map<Class, AnalysedObjectIterator> initialValue()
                {
                    return new HashMap<Class, AnalysedObjectIterator>();
                }
            };

    @SuppressWarnings("unchecked")
    public static <T> T getCurrentlyAnalysed(Class<T> type)
    {
        final AnalysedObjectIterator analysedObjectIterator = CONTEXT.get().get(type);
        return (T) ((analysedObjectIterator == null) ? null : analysedObjectIterator.current());
    }

    private static <T> void register(Class<T> type, AnalysedObjectIterator<T> iterator)
    {
        CONTEXT.get().put(type, iterator);
    }

    public Freud(final Class type)
    {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public static <T> FreudIteration<T> iterateOver(Class<T> type)
    {
        return new Freud(type);
    }

    @Override
    public FreudRule<T> in(final AnalysedObjectIterator<T> iterator)
    {
        this.iterator = iterator;
        register(iterator.analysedObjectType(), iterator);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FreudRule<T> within(final AnalysedObjectIterator<?> iterator)
    {
        register((Class<Object>)iterator.analysedObjectType(), (AnalysedObjectIterator<Object>) iterator);
        this.iterator = adapter(iterator);
        register(this.iterator.analysedObjectType(), this.iterator);
        return this;
    }

    @Override
    public FreudAssertion<T> forEach(final Matcher<T> matcher)
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

    private AnalysedObjectIterator<T> adapter(final AnalysedObjectIterator<?> iterator)
    {
        ensureConfigLoaded();
        return config.iteratorAdapter(iterator);
    }

    @SuppressWarnings("unchecked")
    private void ensureConfigLoaded()
    {
        if (config == null)
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
                
                config = (FreudConfig<T>) configClass.newInstance();
            }
            catch (ClassNotFoundException e)
            {
                config = DefaultFreudConfig.getInstance();
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


}
