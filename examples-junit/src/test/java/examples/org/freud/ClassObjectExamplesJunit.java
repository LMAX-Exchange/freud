/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples.org.freud;

import org.freud.core.listener.AnalysisListener;
import org.freud.core.listener.AssertionErrorAnalysisListener;
import org.freud.java.Freud;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Comparator;

import static examples.org.freud.matchers.ClassObjectMatchers.classAnnotation;
import static examples.org.freud.matchers.ClassObjectMatchers.classSimpleName;
import static examples.org.freud.matchers.ClassObjectMatchers.hasDeclaredFieldOfType;
import static examples.org.freud.matchers.ClassObjectMatchers.hasDeclaredMethod;
import static examples.org.freud.matchers.ClassObjectMatchers.subTypeOf;
import static java.util.Arrays.asList;
import static org.freud.analysed.classobject.ClassObjectDsl.classOf;
import static org.freud.java.matcher.FreudDsl.no;

public final class ClassObjectExamplesJunit {

    private AnalysisListener listener = new AssertionErrorAnalysisListener();

    @Test
    public void equalsAlwaysGoesTogetherWithHashCode() throws Exception {
        Freud.iterateOver(Class.class).
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode"))))).
                in(classOf(asList("examples.classobject.ClassWithEqualsAndHashCode", "examples.classobject.EmptyClass")))
                .analyse(listener);
    }

    @Test
    public void testClassWithMockeryMustContainRunWithAnnotation() throws Exception {
        Freud.iterateOver(Class.class).
                forEach(classSimpleName().matches(".+Test")).
                assertThat(no(hasDeclaredFieldOfType(Mockery.class)).
                        or(classAnnotation(RunWith.class, JMock.class))).
                in(classOf(asList(
                        "examples.classobject.ClassThatHasOtherRunWith",
                        "examples.classobject.ClassThatHasRunWithJMock",
                        "examples.classobject.EmptyClass"))).analyse(listener);
    }

    // + example of use of Custom hamcrest Matcher

    @Test
    public void allImplementorsOfComparatorMustNotContainFields() throws Exception {
        Freud.iterateOver(Class.class).
                assertThat(no(subTypeOf(Comparator.class)).or(no(withFields()))).
                in(classOf(asList("examples.classobject.StatelessComparator"))).analyse(listener);
    }

    private static Matcher<Class> withFields() {
        return new TypeSafeMatcher<Class>() {
            @Override
            protected boolean matchesSafely(Class item) {
                return item.getDeclaredFields().length > 0;
            }

            public void describeTo(Description description) {
                description.appendText("<Class>.withFields()");
            }
        };
    }
}
