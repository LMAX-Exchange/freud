package org.langera.examples.classobject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.runner.RunWith;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;

import java.util.Comparator;

import static org.langera.freud.core.matcher.FreudMatchers.no;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.classAnnotation;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.classSimpleNameMatches;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.hasDeclaredFieldOfType;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.hasDeclaredMethod;
import static org.langera.freud.optional.classobject.ClassObjectMatchers.subTypeOf;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 31-Oct-2008
 * Time: 09:09:32
 */
public final class ClassExamples
{
    private ClassExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser equalsAlwaysGoesTogetherWithHashCode(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).in(iterator).
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode")))));
    }

    public static FreudAnalyser testClassWithMockeryMustContainRunWithAnnotation(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).in(iterator).
                forEach(classSimpleNameMatches(".+Test")).
                assertThat(no(hasDeclaredFieldOfType(Mockery.class)).
                        or(classAnnotation(RunWith.class, JMock.class)));
    }

    // + example of use of Custom hamcrest Matcher

    public static FreudAnalyser allImplementorsOfComparatorMustNotContainFields(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).in(iterator).
                assertThat(no(subTypeOf(Comparator.class)).or(no(withFields())));
    }

    private static Matcher<Class> withFields()
    {
        return new TypeSafeMatcher<Class>()
        {
            @Override
            protected boolean matchesSafely(Class item)
            {
                return item.getDeclaredFields().length > 0;
            }

            public void describeTo(Description description)
            {
                description.appendText("<Class>.withFields()");
            }
        };
    }

}
