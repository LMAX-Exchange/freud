package org.langera.freud.core;

import java.util.HashMap;
import java.util.Map;

public final class FreudConfigRegistry
{
    public static final String FREUD_CONFIG_SUFFIX = "FreudConfig";
    private Map<Class, FreudConfig> configByTypeMap = new HashMap<Class, FreudConfig>();


    @SuppressWarnings("unchecked")
    public <T> FreudConfig<T> configOf(Class<T> type)
    {
        FreudConfig<T> config = configByTypeMap.get(type);
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
