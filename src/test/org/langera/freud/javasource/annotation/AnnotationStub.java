package org.langera.freud.javasource.annotation;

import java.util.Collections;
import java.util.Map;

public class AnnotationStub implements Annotation
{
    private String name;
    private String defaultParameter;
    private String key;
    private String value;

    public AnnotationStub(String name, String defaultParameter, String key, String value)
    {
        this.name = name;
        this.defaultParameter = defaultParameter;
        this.key = key;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public String getDefaultParameter()
    {
        return defaultParameter;
    }

    public Map<String, String> getParameterMap()
    {
        return Collections.singletonMap(key, value);
    }
}
