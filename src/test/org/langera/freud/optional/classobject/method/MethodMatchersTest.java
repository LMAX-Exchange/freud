package org.langera.freud.optional.classobject.method;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Modifier;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.classobject.method.MethodMatchers.definedWithModifier;
import static org.langera.freud.optional.classobject.method.MethodMatchers.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodMatchers.methodNameMatches;
import static org.langera.freud.optional.classobject.method.MethodMatchers.publicMethod;
import static org.langera.freud.optional.classobject.method.MethodMatchers.throwsException;

public final class MethodMatchersTest
{
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

//    @Test
//    public void shouldReturnTrueToAPublicModifier() throws Exception
//    {
//        Assert.assertThat(MethodMatchersTest.class.getMethod("shouldReturnTrueToAPublicModifier"), publicMethod());
//    }
//
//    @Test
//    public void shouldReturnTrueToAPublicModifier() throws Exception
//    {
//        Assert.assertThat(MethodMatchersTest.class.getMethod("dummyMethod"), publicMethod());
//    }

    @Test
    public void testShouldPassWhenAnnotationExists() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class));
    }

    @Test
    public void testShouldFailAnalysisWhenAnnotationDoesNotExist() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myOtherMethod"), no(methodAnnotation(Dummy.class)));
    }

    @Test
    public void testShouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, "value to test"));
    }

    @Test
    public void testShouldFailAnalysisForAnnotationWithOtherValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), no(methodAnnotation(Dummy.class, "other value")));
    }

    @Test
    public void testShouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, no(Matchers.equalTo("other value"))));
    }

    @Test
    public void testShouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class.getMethod("myMethod"), methodAnnotation(Dummy.class, Matchers.equalTo("value to test")));
    }

    private static void dummyMethod()
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
}
