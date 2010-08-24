package org.langera.freud.property;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class PropertyMatchers
{
    public static Matcher<Property> propertyKey(final String key)
    {
        return new PropertyKeyMatcher(key);
    }

    private PropertyMatchers()
    {
        // static utility
    }

    public static final class PropertyKeyMatcher extends TypeSafeMatcher<Property>
    {
        private final String key;

        public PropertyKeyMatcher(String key)
        {
            this.key = key;
        }

        @Override
        public final boolean matchesSafely(final Property property)
        {
            return key.equals(property.getKey());
        }

        public void describeTo(Description description)
        {
            description.appendText("Property[").appendText(key).appendText("]");
        }
    }
}
