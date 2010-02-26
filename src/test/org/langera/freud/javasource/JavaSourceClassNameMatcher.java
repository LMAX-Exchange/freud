package org.langera.freud.javasource;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public final class JavaSourceClassNameMatcher extends TypeSafeMatcher<JavaSource>
{
    public static JavaSourceClassNameMatcher javaSourceClassNamed(final String expectedName)
    {
        return new JavaSourceClassNameMatcher(expectedName);
    }

    private String expectedName;

    private JavaSourceClassNameMatcher(String expectedName)
    {
        this.expectedName = expectedName;
    }

    @Override
    public boolean matchesSafely(JavaSource item)
    {
        return expectedName.equals(item.getFullClassName());
    }

    public void describeTo(Description description)
    {
        description.appendText("JavaSource[").appendText(expectedName).appendText("]");
    }
}