package org.langera.freud.optional.classobject.method;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.Freud;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.classobject.method.MethodMatchers.declaredMethod;
import static org.langera.freud.optional.classobject.method.MethodMatchers.definedWithModifier;
import static org.langera.freud.optional.classobject.method.MethodMatchers.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodMatchers.methodNameMatches;
import static org.langera.freud.optional.classobject.method.MethodMatchers.publicMethod;
import static org.langera.freud.optional.classobject.method.MethodMatchers.staticMethod;
import static org.langera.freud.optional.classobject.method.MethodMatchers.throwsException;

public final class MethodMatchersTest
{
    static
    {
        // Method is a third party class that needs a config - point ot it using a System property
        System.setProperty(Method.class.getName() + Freud.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
    }

    private AnalysisListenerStub listenerStub;

    @Test
    public void shouldReturnTrueToAMatchedRegex() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), methodNameMatches("should.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), no(methodNameMatches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAMatchedExceptionInThrowsClause() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedExceptionInThrowsClause"), throwsException(Exception.class));
    }

    @Test
    public void shouldReturnTrueToAnInheritedExceptionInThrowsClause() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAnInheritedExceptionInThrowsClause"), throwsException(RuntimeException.class));
    }

    @Test
    public void shouldReturnFalseToANonMatchedExceptionInThrowsClause() throws NoSuchMethodException
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnFalseToANonMatchedExceptionInThrowsClause"), no(throwsException(IOException.class)));
    }

    @Test
    public void shouldReturnTrueToAMatchedModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAMatchedModifier"), definedWithModifier(Modifier.PUBLIC));
    }

    @Test
    public void shouldReturnFalseToANonMatchedModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnFalseToANonMatchedModifier"), no(definedWithModifier(Modifier.PRIVATE)));
    }

    @Test
    public void shouldReturnTrueToAPublicModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAPublicModifier"), publicMethod());
    }

    @Test
    public void shouldReturnFalseToANonPublicModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getDeclaredMethod("dummyMethod"), no(publicMethod()));
    }

    @Test
    public void shouldReturnTrueToAStaticModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getDeclaredMethod("dummyMethod"), staticMethod());
    }

    @Test
    public void shouldReturnFalseToANonStaticModifier() throws Exception
    {
        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnFalseToANonStaticModifier"), no(staticMethod()));
    }

    @Test
    public void shouldReturnTrueToADeclaredMethod() throws Exception
    {
        Freud.iterateOver(Method.class).within(ResourceIterators.selfResourceIterator(MethodMatchersTest.class)).
                assertThat(declaredMethod()).
                analyse(listenerStub);

        listenerStub.assertPassed(MethodMatchersTest.class.getDeclaredMethod("dummyMethod"));
    }

    @Test
    public void shouldReturnFalseToANonDeclaredMethod() throws Exception
    {
        Freud.iterateOver(Method.class).within(ResourceIterators.selfResourceIterator(MethodMatchersTest.class)).
                assertThat(declaredMethod()).
                analyse(listenerStub);

        listenerStub.assertFailed(MethodMatchersTest.class.getMethod("hashCode"));
    }


    @Test
    public void shouldPassWhenAnnotationExists() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class));
    }

    @Test
    public void shouldFailAnalysisWhenAnnotationDoesNotExist() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myOtherMethod"), no(methodAnnotation(Dummy.class)));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, "value to test"));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithOtherValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), no(methodAnnotation(Dummy.class, "other value")));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, no(Matchers.equalTo("other value"))));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, Matchers.equalTo("value to test")));
    }

    static void dummyMethod()
    {
        // no op
    }


    @java.lang.annotation.Retention(value = RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(value = {ElementType.METHOD})
    public @interface Dummy
    {
        String value();
    }

    private static class TestingDummy
    {
        @Dummy("value to test")
        public void myMethod()
        {
            // do nothing
        }

        public void myOtherMethod()
        {
            // do nothing
        }
    }

    @Before
    public void setUp()
    {
        listenerStub = new AnalysisListenerStub();
    }
}
