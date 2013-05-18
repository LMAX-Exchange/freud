package examples.org.freud.matchers;

import org.freud.analysed.properties.Property;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;

public final class PropertyMatchers {
    private PropertyMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<Property> propertyKey() {
        return new StringMatcherBuilder<Property>(new RegexMatcherAdapter<Property>() {
            @Override
            public String getStringToMatch(final Property toBeAnalysed) {
                return toBeAnalysed.getKey();
            }

            @Override
            public String matcherDisplayName() {
                return "PropertyKey";
            }
        });
    }


    public static StringMatcherBuilder<Property> propertyValue() {
        return new StringMatcherBuilder<Property>(new RegexMatcherAdapter<Property>() {
            @Override
            public String getStringToMatch(final Property toBeAnalysed) {
                return toBeAnalysed.getValue();
            }

            @Override
            public String matcherDisplayName() {
                return "PropertyValue";
            }
        });
    }
}
