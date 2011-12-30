package org.langera.freud.analysis.javasource;

import org.langera.freud.analysis.FreudSessionContext;
import org.langera.freud.core.Freud;
import org.langera.freud.core.listener.AnalysisListener;
import org.langera.freud.optional.classobject.field.FieldFreudConfig;
import org.langera.freud.optional.classobject.method.MethodFreudConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class JavaSourceFileFreudSessionContext implements FreudSessionContext
{
    private final AnalysisListener listener;

    public JavaSourceFileFreudSessionContext(final AnalysisListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void sessionRuntimeContextSetUp()
    {
        System.setProperty(Class.class.getName() + Freud.FREUD_CONFIG_SUFFIX, ClassObjectOfJavaSourceFreudConfig.class.getName());
        System.setProperty(Method.class.getName() + Freud.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
        System.setProperty(Field.class.getName() + Freud.FREUD_CONFIG_SUFFIX, FieldFreudConfig.class.getName());
    }

    @Override
    public AnalysisListener getListener()
    {
        return listener;
    }
}
