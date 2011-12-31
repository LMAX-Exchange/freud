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

package org.langera.freud.optional.classobject.field;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudConfigRegistry;
import org.langera.freud.core.listener.AnalysisListenerStub;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertThat;
import static org.langera.freud.core.iterator.resource.ResourceIterators.selfResourceIterator;
import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.ClassObjectDsl.anEnum;
import static org.langera.freud.optional.classobject.ClassObjectDsl.primitive;
import static org.langera.freud.optional.classobject.field.FieldDsl.declaredField;
import static org.langera.freud.optional.classobject.field.FieldDsl.definedWithModifier;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldAnnotation;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldName;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldType;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldTypeIsSubTypeOf;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldTypeMatches;
import static org.langera.freud.optional.classobject.field.FieldDsl.privateField;
import static org.langera.freud.optional.classobject.field.FieldDsl.publicField;
import static org.langera.freud.optional.classobject.field.FieldDsl.staticField;

public final class FieldDslTest
{
    static
    {
        // Field is a third party class that needs a config - point ot it using a System property
        System.setProperty(Field.class.getName() + FreudConfigRegistry.FREUD_CONFIG_SUFFIX, FieldFreudConfig.class.getName());
    }

    static String dummyField;
    private AnalysisListenerStub listenerStub;

    @Test
    public void shouldReturnTrueToAMatchedRegex() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), fieldName().matches("lis.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), no(fieldName().matches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainsRegex() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), fieldName().contains("lis"));
    }

    @Test
    public void shouldReturnFalseToANonContainsRegex() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), no(fieldName().contains("Q")));
    }

    @Test
    public void shouldReturnTrueToAMatchedModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), definedWithModifier(Modifier.PRIVATE));
    }

    @Test
    public void shouldReturnFalseToANonMatchedModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), no(definedWithModifier(Modifier.PUBLIC)));
    }

    @Test
    public void shouldReturnTrueToAPrivateModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), privateField());
    }

    @Test
    public void shouldReturnFalseToANonPublicModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("dummyField"), no(publicField()));
    }

    @Test
    public void shouldReturnTrueToAStaticModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("dummyField"), staticField());
    }

    @Test
    public void shouldReturnFalseToANonStaticModifier() throws Exception
    {
        assertThat(FieldDslTest.class.getDeclaredField("listenerStub"), no(staticField()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnTrueToADeclaredField() throws Exception
    {
        Freud.iterateOver(Field.class).
                assertThat(declaredField()).within(selfResourceIterator(TestingDummySubClass.class)).
                analyse(listenerStub);

        listenerStub.assertPassed(testingDummySubClassDeclaredField());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnFalseToANonDeclaredField() throws Exception
    {
        Freud.iterateOver(Field.class).
                assertThat(declaredField()).within(selfResourceIterator(TestingDummySubClass.class)).
                analyse(listenerStub);

        listenerStub.assertFailed(myField());
    }

    @Test
    public void shouldPassWhenTypeMatches() throws Exception
    {
        assertThat(myField(), fieldType(String.class));
    }

    @Test
    public void shouldFailWhenTypeDoesNotMatch() throws Exception
    {
        assertThat(myField(), no(fieldType(Integer.class)));
    }

    @Test
    public void shouldPassWhenTypeMatchesClassMatcher() throws Exception
    {
        assertThat(myOtherField(), fieldTypeMatches(primitive()));
    }

    @Test
    public void shouldFailWhenTypeDoesNotMatchClassMatcher() throws Exception
    {
        assertThat(myField(), no(fieldTypeMatches(anEnum())));
    }

    @Test
    public void shouldPassWhenTypeIsSubTypeOfInput() throws Exception
    {
        assertThat(testingDummySubClassDeclaredField(), fieldTypeIsSubTypeOf(TestingDummy.class));
    }

    @Test
    public void shouldFailWhenTypeIsNotSubTypeOfInput() throws Exception
    {
        assertThat(testingDummySubClassDeclaredField(), no(fieldTypeIsSubTypeOf(String.class)));
    }


    @Test
    public void shouldPassWhenAnnotationExists() throws Exception
    {
        assertThat(myField(), fieldAnnotation(Dummy.class));
    }

    @Test
    public void shouldFailAnalysisWhenAnnotationDoesNotExist() throws Exception
    {
        assertThat(myOtherField(), no(fieldAnnotation(Dummy.class)));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithValue() throws Exception
    {
        assertThat(myField(), fieldAnnotation(Dummy.class, "value to test"));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithOtherValue() throws Exception
    {
        assertThat(myField(), no(fieldAnnotation(Dummy.class, "other value")));
    }

    @Test
    public void shouldFailAnalysisForAnnotationWithMatcher() throws Exception
    {
        assertThat(myField(), fieldAnnotation(Dummy.class, no(Matchers.equalTo("other value"))));
    }

    @Test
    public void shouldPassAnalysisForAnnotationWithMatcher() throws Exception
    {
        assertThat(myField(), fieldAnnotation(Dummy.class, Matchers.equalTo("value to test")));
    }

    private Field myField() throws NoSuchFieldException
    {
        return TestingDummy.class.getDeclaredField("myField");
    }

    private Field myOtherField() throws NoSuchFieldException
    {
        return TestingDummy.class.getDeclaredField("myOtherField");
    }

    private Field testingDummySubClassDeclaredField() throws NoSuchFieldException
    {
        return TestingDummySubClass.class.getDeclaredField("declaredField");
    }


    @java.lang.annotation.Retention(value = RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(value = {ElementType.FIELD})
    public @interface Dummy
    {
        String value();
    }

    private static class TestingDummy
    {
        @Dummy("value to test")
        public String myField;
        private volatile int myOtherField;
    }

    private static class TestingDummySubClass extends TestingDummy
    {
        private TestingDummySubClass declaredField;
    }

    @Before
    public void setUp()
    {
        listenerStub = new AnalysisListenerStub();
    }
}
