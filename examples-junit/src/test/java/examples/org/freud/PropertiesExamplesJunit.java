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

import org.freud.analysed.properties.Property;
import org.freud.java.Freud;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static examples.org.freud.matchers.PropertyMatchers.propertyKey;
import static examples.org.freud.matchers.PropertyMatchers.propertyValue;
import static java.util.Arrays.asList;
import static org.freud.analysed.properties.PropertiesDsl.propertyOf;

public final class PropertiesExamplesJunit {

    private AnalysisListenerStub listener = new AnalysisListenerStub();

    @Test
    public void propertiesThatContainMaxOrMinInTheirNameMustHaveAnIntegerValue() {
        Freud.iterateOver(Property.class).
                forEach(propertyKey().contains("min").or(propertyKey().contains("max"))).
                assertThat(propertyValue().is(aNumber())).in(propertyOf(asList(
                ClassLoader.getSystemResource("PropertiesExamples/ok.properties"),
                ClassLoader.getSystemResource("PropertiesExamples/badMaxLength.properties"),
                ClassLoader.getSystemResource("PropertiesExamples/badMinSize.properties")))).
                analyse(listener);

        listener.assertFiltered(3);
        listener.assertPassed(3);
        listener.assertFailed(2);
    }

    private Matcher<? super String> aNumber() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(final String item) {
                try {
                    Double.parseDouble(item);
                    return true;
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("aNumber");
            }
        };
    }
}
