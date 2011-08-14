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

package org.langera.freud.optional.classobject;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classAnnotation;
import static org.langera.freud.optional.classobject.ClassObjectDsl.className;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classSimpleName;
import static org.langera.freud.optional.classobject.ClassObjectDsl.finalClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.hasDeclaredFieldOfType;
import static org.langera.freud.optional.classobject.ClassObjectDsl.hasDeclaredMethod;
import static org.langera.freud.optional.classobject.ClassObjectDsl.privateClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.protectedClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.publicClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.subTypeOf;

public final class ClassObjectDslTest
{
    @Test
    public void shouldReturnTrueToAFullNameMatchedRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class, className().matches("org.langera.freud.optional.classobject.Class.+"));
    }

    @Test
    public void shouldReturnFalseToAFullNameMatchedRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class,no(className().matches("com.langera.freud.optional.classobject.Class.+")));
    }

    @Test
    public void shouldReturnTrueToAPublicModifierMatcher()
    {
        Assert.assertThat(ClassObjectDslTest.class, publicClass());
    }

    @Test
    public void shouldReturnTrueToAFinalModifierMatcher()
    {
        Assert.assertThat(ClassObjectDslTest.class, finalClass());
    }

    @Test
    public void shouldReturnFalseToAProtectedModifierMatcher()
    {
        Assert.assertThat(ClassObjectDslTest.class, no(protectedClass()));
    }

    @Test
    public void shouldReturnFalseToAPrivateModifierMatcher()
    {
        Assert.assertThat(ClassObjectDslTest.class, no(privateClass()));
    }

    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class, classSimpleName().matches("Class.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class, no(classSimpleName().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class, classSimpleName().contains("Class"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex()
    {
        Assert.assertThat(ClassObjectDslTest.class, no(classSimpleName().contains("Q")));
    }


    @Test
    public void shouldReturnTrueToACorrectSubType()
    {
        Assert.assertThat(ClassObjectDslTest.class, subTypeOf(Object.class));
    }

    @Test
    public void shouldReturnFalseToAWrongSubType()
    {
        Assert.assertThat(ClassObjectDslTest.class, no(subTypeOf(String.class)));
    }

    @Test
    public void shouldReturnTrueWhentheMethodIsDeclared() throws Exception
    {
        Assert.assertThat(ClassObjectDslTest.class, hasDeclaredMethod("shouldReturnTrueWhentheMethodIsDeclared"));
    }

    @Test
    public void shouldPassWhenMethodIsDeclared() throws Exception
    {
        Assert.assertThat(TestingDummy.class, hasDeclaredMethod("myMethod"));
    }

    @Test
    public void shouldFailBecauseMethodNotDeclared() throws Exception
    {
        Assert.assertThat(TestingDummySubClass.class, no(hasDeclaredMethod("myMethod")));
    }

    @Test
    public void shouldFailBecauseMethodNotExist() throws Exception
    {
        Assert.assertThat(TestingDummy.class, no(hasDeclaredMethod("foo")));
    }

    @Test
    public void shouldPassWhenFieldIsDeclared() throws Exception
    {
        Assert.assertThat(TestingDummy.class, hasDeclaredFieldOfType(String.class));
    }

    @Test
    public void shouldFailBecauseFieldNotDeclared() throws Exception
    {
        Assert.assertThat(TestingDummySubClass.class, no(hasDeclaredFieldOfType(String.class)));
    }

    @Test
    public void shouldFailBecauseFieldNotExist() throws Exception
    {
        Assert.assertThat(TestingDummy.class, no(hasDeclaredFieldOfType(Boolean.class)));
    }


    @Test
    public void shouldPassWhenAnnotationExists() throws Exception
    {
        Assert.assertThat(TestingDummy.class, classAnnotation(Dummy.class));
    }

    @Test
    public void shouldFailAnalysisWhenAnnotationDoesNotExist() throws Exception
    {
        Assert.assertThat(TestingDummySubClass.class, no(classAnnotation(Dummy.class)));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class, classAnnotation(Dummy.class, "value to test"));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithOtherValue() throws Exception
    {
        Assert.assertThat(TestingDummy.class, no(classAnnotation(Dummy.class, "other value")));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class, classAnnotation(Dummy.class, no(Matchers.equalTo("other value"))));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        Assert.assertThat(TestingDummy.class, classAnnotation(Dummy.class, Matchers.equalTo("value to test")));
    }


    @java.lang.annotation.Retention(value = RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(value = {ElementType.TYPE})
    public @interface Dummy
    {
        String value();
    }

    @Dummy("value to test")
    private static class TestingDummy
    {
        private String myField;

        public void myMethod()
        {
            // do nothing
        }
    }

    private static final class TestingDummySubClass extends TestingDummy
    {
        private Integer myField;

        public void mySubClassMethod()
        {
            // do nothing
        }
    }
}
