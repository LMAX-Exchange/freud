package org.langera.freud.method.assertion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.AbstractAnalysis;
import org.langera.freud.AnalysisListener;
import org.langera.freud.NestedTypeAnalysisAdapter;
import org.langera.freud.util.collection.AnalysedObjectIterator;

import java.util.Iterator;

public class DeclaredMethodAssertionTest
{
    private DeclaredMethodAssertion assertionForString;

    @Test
    public void shouldPassAnalysisMethodDeclaredOnCurrentlyAnalysedClass() throws Exception
    {
        Assert.assertTrue(assertionForString.matches(String.class.getMethod("length")));
    }

    @Test
    public void shouldFailAnalysisMethodDeclaredOnCurrentlyAnalysedClass() throws Exception
    {
        Assert.assertFalse(assertionForString.matches(String.class.getMethod("getClass")));
    }

    @Before
    public void setUp() throws Exception
    {
        fakeCurrentlyAnalysedClass(String.class);

        assertionForString = new DeclaredMethodAssertion();
    }

    private void fakeCurrentlyAnalysedClass(final Class currentlyAnalysed)
    {
        new AbstractAnalysis<Class, Void>(new AnalysedObjectIterator<Class>()
        {
            public Class current()
            {
                return currentlyAnalysed;
            }

            public void setListener(AnalysisListener listener)
            {
            }

            public boolean hasNext()
            {
                return false;
            }

            public Iterator<Class> iterator()
            {
                return null;
            }

            public Class next()
            {
                return null;
            }

            public void remove()
            {
            }
        }, Class.class)
        {
            @Override
            protected NestedTypeAnalysisAdapter getAnalysisAdapter(Class type, Class nestedType)
            {
                return null;
            }
        };
    }
}
