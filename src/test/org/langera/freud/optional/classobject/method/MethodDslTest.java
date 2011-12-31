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

package org.langera.freud.optional.classobject.method;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudConfigRegistry;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.classobject.ClassObjectDsl;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertThat;
import static org.langera.freud.core.iterator.resource.ResourceIterators.selfResourceIterator;
import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.method.MethodDsl.declaredMethod;
import static org.langera.freud.optional.classobject.method.MethodDsl.definedWithModifier;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodName;
import static org.langera.freud.optional.classobject.method.MethodDsl.numberOfParams;
import static org.langera.freud.optional.classobject.method.MethodDsl.publicMethod;
import static org.langera.freud.optional.classobject.method.MethodDsl.staticMethod;
import static org.langera.freud.optional.classobject.method.MethodDsl.throwsException;
import static org.langera.freud.optional.classobject.method.MethodDsl.withParams;

public final class MethodDslTest
{
    static
    {
        // Method is a third party class that needs a config - point ot it using a System property
        System.setProperty(Method.class.getName() + FreudConfigRegistry.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
    }

    private AnalysisListenerStub listenerStub;

    @Test
    public void shouldReturnTrueToAMatchedRegex() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), methodName().matches("should.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAMatchedRegex"), no(methodName().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAContainsRegex"), methodName().contains("should"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnFalseToANonContainsRegex"), no(methodName().contains("Q")));
    }

    @Test
    public void shouldReturnTrueToAMatchedExceptionInThrowsClause() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAMatchedExceptionInThrowsClause"), throwsException(Exception.class));
    }

    @Test
    public void shouldReturnTrueToAnInheritedExceptionInThrowsClause() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAnInheritedExceptionInThrowsClause"), throwsException(RuntimeException.class));
    }

    @Test
    public void shouldReturnFalseToANonMatchedExceptionInThrowsClause() throws NoSuchMethodException
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnFalseToANonMatchedExceptionInThrowsClause"), no(throwsException(IOException.class)));
    }

    @Test
    public void shouldReturnTrueToAMatchedModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAMatchedModifier"), definedWithModifier(Modifier.PUBLIC));
    }

    @Test
    public void shouldReturnFalseToANonMatchedModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnFalseToANonMatchedModifier"), no(definedWithModifier(Modifier.PRIVATE)));
    }

    @Test
    public void shouldReturnTrueToAPublicModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnTrueToAPublicModifier"), publicMethod());
    }

    @Test
    public void shouldReturnFalseToANonPublicModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getDeclaredMethod("dummyMethod"), no(publicMethod()));
    }

    @Test
    public void shouldReturnTrueToAStaticModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getDeclaredMethod("dummyMethod"), staticMethod());
    }

    @Test
    public void shouldReturnFalseToANonStaticModifier() throws Exception
    {
        assertThat(MethodDslTest.class.getMethod("shouldReturnFalseToANonStaticModifier"), no(staticMethod()));
    }

    @Test
    public void shouldReturnTrueToMatchingParams() throws Exception
    {
        assertThat(String.class.getMethod("startsWith", String.class, int.class), withParams(String.class, int.class));
    }


    @Test
    public void shouldReturnTrueToMatchingPrimitiveParams() throws Exception
    {
        assertThat(String.class.getMethod("charAt", int.class), withParams(int.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTrueToMatchingParamsUsingMatcher() throws Exception
    {
        assertThat(String.class.getMethod("charAt", int.class), withParams(ClassObjectDsl.primitive()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTrueToADeclaredMethod() throws Exception
    {
        Freud.iterateOver(Method.class).
                assertThat(declaredMethod()).within(selfResourceIterator(MethodDslTest.class)).
                analyse(listenerStub);

        listenerStub.assertPassed(MethodDslTest.class.getDeclaredMethod("dummyMethod"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnFalseToANonDeclaredMethod() throws Exception
    {
        Freud.iterateOver(Method.class).
                assertThat(declaredMethod()).within(selfResourceIterator(MethodDslTest.class)).
                analyse(listenerStub);

        listenerStub.assertFailed(MethodDslTest.class.getMethod("hashCode"));
    }


    @Test
    public void shouldPassWhenAnnotationExists() throws Exception
    {
        assertThat(myMethod(), methodAnnotation(Dummy.class));
    }

    @Test
    public void shouldFailAnalysisWhenAnnotationDoesNotExist() throws Exception
    {
        assertThat(myOtherMethod(), no(methodAnnotation(Dummy.class)));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        assertThat(myMethod(), methodAnnotation(Dummy.class, "value to test"));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithOtherValue() throws Exception
    {
        assertThat(myMethod(), no(methodAnnotation(Dummy.class, "other value")));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        assertThat(myMethod(), methodAnnotation(Dummy.class, no(Matchers.equalTo("other value"))));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        assertThat(myMethod(), methodAnnotation(Dummy.class, Matchers.equalTo("value to test")));
    }

    @Test
    public void shouldPassAnalysisForNumberOfParams() throws Exception
    {
        assertThat(myOtherMethod(), numberOfParams().equalTo(2));
    }

    @Test
    public void shouldFailAnalysisForNumberOfParams() throws Exception
    {
        assertThat(myOtherMethod(), no(numberOfParams().lessThan(2)));
    }

    static void dummyMethod()
    {
        // no op
    }

    private Method myMethod() throws NoSuchMethodException
    {
        return TestingDummy.class.getMethod("myMethod");
    }

    private Method myOtherMethod() throws NoSuchMethodException
    {
        return TestingDummy.class.getMethod("myOtherMethod", String.class, int.class);
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

        public void myOtherMethod(String param1, int param2)
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
