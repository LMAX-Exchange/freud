package org.langera.freud.javasource.method.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.javasource.annotation.Annotation;
import org.langera.freud.javasource.annotation.AnnotationStub;
import org.langera.freud.javasource.method.MethodDeclarationStub;

public class HasDeclaredAnnotationAssertionTest
{
    private HasDeclaredAnnotationAssertion nameAssertion;
    private HasDeclaredAnnotationAssertion nameAndDefaultValueAssertion;
    private HasDeclaredAnnotationAssertion nameAndValueAssertion;

    @Test
    public void shouldDetectAnnotation()
    {
        Assert.assertTrue(nameAssertion.analyse(
                new MethodDeclarationStub("",
                        new Annotation[]
                        {
                            new AnnotationStub("SuppressWarnings", null, null, null)
                        }, null)));
    }

    @Test
    public void shouldDetectAnnotationWithDefaultValue()
    {
        Assert.assertTrue(nameAndDefaultValueAssertion.analyse(
                new MethodDeclarationStub("",
                        new Annotation[]
                        {
                            new AnnotationStub("SuppressWarnings", "\"unused\"", null, null)
                        }, null)));
    }

    @Test
    public void shouldNotDetectAnnotationWithDifferentDefaultValue()
    {
        Assert.assertFalse(nameAndDefaultValueAssertion.analyse(
                new MethodDeclarationStub("",
                        new Annotation[]
                        {
                            new AnnotationStub("SuppressWarnings", "\"unchecked\"", null, null)
                        }, null)));
    }

    @Test
    public void shouldDetectAnnotationWithKeyValuePair()
    {
        Assert.assertTrue(nameAndValueAssertion.analyse(
                new MethodDeclarationStub("",
                        new Annotation[]
                        {
                            new AnnotationStub("Test", null, "\"expected\"", "\"IllegalStateException.class\"")
                        }, null)));
    }

    @Test
    public void shouldNotDetectAnnotationWithDifferentKeyValuePair()
    {
        Assert.assertFalse(nameAndValueAssertion.analyse(
                new MethodDeclarationStub("",
                        new Annotation[]
                        {
                            new AnnotationStub("Test", null, "\"expected\"", "17")
                        }, null)));
    }


    @Test
    public void shouldNotDetectAnnotation()
    {
        Assert.assertFalse(nameAssertion.analyse(
                new MethodDeclarationStub("", new Annotation[]{}, null)));
    }


    @Before
    public void setUp()
    {
        nameAssertion = new HasDeclaredAnnotationAssertion("SuppressWarnings");
        nameAndDefaultValueAssertion = new HasDeclaredAnnotationAssertion("SuppressWarnings", "\"unused\"");
        nameAndValueAssertion = new HasDeclaredAnnotationAssertion("Test", "\"expected\"", "\"IllegalStateException.class\"");
    }
}
