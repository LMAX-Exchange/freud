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

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classAnnotation;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classSimpleName;
import static org.langera.freud.optional.classobject.ClassObjectDsl.hasDeclaredFieldOfType;
import static org.langera.freud.optional.classobject.ClassObjectDsl.hasDeclaredMethod;
import static org.langera.freud.optional.classobject.ClassObjectDsl.subTypeOf;

public final class ClassExamples
{
    private ClassExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser equalsAlwaysGoesTogetherWithHashCode(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode"))))).in(iterator);
    }

    public static FreudAnalyser testClassWithMockeryMustContainRunWithAnnotation(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).
                forEach(classSimpleName().matches(".+Test")).
                assertThat(no(hasDeclaredFieldOfType(Mockery.class)).
                        or(classAnnotation(RunWith.class, JMock.class))).in(iterator);
    }

    // + example of use of Custom hamcrest Matcher

    public static FreudAnalyser allImplementorsOfComparatorMustNotContainFields(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Class.class).
                assertThat(no(subTypeOf(Comparator.class)).or(no(withFields()))).in(iterator);
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
