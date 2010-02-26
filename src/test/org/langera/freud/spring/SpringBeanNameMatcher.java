package org.langera.freud.spring;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public final class SpringBeanNameMatcher extends TypeSafeMatcher<SpringBean>
{
    public static SpringBeanNameMatcher beanNamed(final String expectedName)
    {
        return new SpringBeanNameMatcher(expectedName);
    }

    private String expectedName;

    private SpringBeanNameMatcher(String expectedName)
    {
        this.expectedName = expectedName;
    }

    @Override
    public boolean matchesSafely(SpringBean item)
    {
        return expectedName.equals(item.getBeanName());
    }

    public void describeTo(Description description)
    {
        description.appendText("SpringBean[").appendText(expectedName).appendText("]");
    }
}
