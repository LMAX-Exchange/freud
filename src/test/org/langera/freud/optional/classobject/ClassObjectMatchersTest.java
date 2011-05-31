package org.langera.freud.optional.classobject;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.classAnnotation;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.classSimpleNameContains;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.classSimpleNameMatches;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.hasDeclaredFieldOfType;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.hasDeclaredMethod;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.subTypeOf;

public final class ClassObjectMatchersTest
{
    @Test
    public void shouldReturnTrueToAMatchedRegex()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, classSimpleNameMatches("Class.+"));
    }

    @Test
    public void shouldReturnFalseToANonMatchedRegex()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, no(classSimpleNameMatches("a.*")));
    }

    @Test
    public void shouldReturnTrueToAContainedRegex()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, classSimpleNameContains("Class"));
    }

    @Test
    public void shouldReturnFalseToANonContainedRegex()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, no(classSimpleNameContains("Q")));
    }


    @Test
    public void shouldReturnTrueToACorrectSubType()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, subTypeOf(Object.class));
    }

    @Test
    public void shouldReturnFalseToAWrongSubType()
    {
        Assert.assertThat(ClassObjectMatchersTest.class, no(subTypeOf(String.class)));
    }

    @Test
    public void shouldReturnTrueWhentheMethodIsDeclared() throws Exception
    {
        Assert.assertThat(ClassObjectMatchersTest.class, hasDeclaredMethod("shouldReturnTrueWhentheMethodIsDeclared"));
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
