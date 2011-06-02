package org.langera.examples.classobject.method;

import org.junit.Test;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classobject.method.MethodFreudConfig;

import java.lang.reflect.Method;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodName;

public final class MethodExamples
{
    static
    {
        // Method is a third party class that needs a config - point ot it using a System property
        System.setProperty(Method.class.getName() + Freud.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
    }

    private MethodExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser allTestMethodsMustStartWithShould(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Method.class).within(iterator).
                assertThat(methodName().matches("should.+").or(no(methodAnnotation(Test.class))));
    }

}
