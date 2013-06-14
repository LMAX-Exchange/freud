package org.langera.freud.analysis.javasource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.DummyClass;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.JavaSourceJdom;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.langera.freud.core.iterator.resource.ResourceIterators.analysedObjectIterator;

public final class ClassObjectOfJavaSourceFreudConfigTest
{

    private static final String NAME = "org/langera/DummyClass.java";
    private JavaSource javaSource;

    @Before
    public void setUp() throws Exception
    {
        javaSource = new JavaSourceJdom(new InputStreamReader(ClassLoader.getSystemResourceAsStream(NAME)), NAME);
    }

    @Test
    public void shouldReturnAllClassesReferencedByInputJavaSource()
    {
        ClassObjectOfJavaSourceFreudConfig freudConfig = new ClassObjectOfJavaSourceFreudConfig();

        final AnalysedObjectIterator<Class> classAnalysedObjectIterator =
                freudConfig.iteratorAdapter(analysedObjectIterator(Collections.singleton(javaSource).iterator(), JavaSource.class));

        Assert.assertThat(classAnalysedObjectIterator, collectionOf(
                                    DummyClass.class.getName(),
                                    DummyClass.class.getName() + "$InnerDummyClass",
                                    DummyClass.class.getName() + "$InnerDummyClass$InnerInnerDummyClass",
                                    DummyClass.class.getName() + "$InnerDummyAnnotation"));
    }

    private Matcher<AnalysedObjectIterator<Class>> collectionOf(final String... classNames)
    {
        return new TypeSafeMatcher<AnalysedObjectIterator<Class>>()
        {
            private String errorMsg = "";

            @Override
            protected boolean matchesSafely(final AnalysedObjectIterator<Class> iterator)
            {
                final List<String> collector = new LinkedList<String>();
                while (iterator.hasNext())
                {
                    collector.add(iterator.next().getName());
                }


                if (collector.size() == classNames.length)
                {
                    for (String name : classNames)
                    {
                        if (!collector.contains(name))
                        {
                            errorMsg = "Got " + collector;
                            return false;
                        }
                    }
                    return true;
                }
                errorMsg = "Got " + collector;
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("expected ");
                description.appendText(Arrays.toString(classNames));
                description.appendText(" ");
                description.appendText(errorMsg);
            }
        };
    }
}
