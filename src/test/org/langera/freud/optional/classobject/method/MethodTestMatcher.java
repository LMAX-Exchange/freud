package org.langera.freud.optional.classobject.method;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Method;

public final class MethodTestMatcher extends TypeSafeMatcher<Method>
{
    public static MethodTestMatcher method(final String expectedName, final Class... expectedParams)
    {
        return new MethodTestMatcher(expectedName, expectedParams);
    }

    private String expectedName;
    private final Class[] expectedParams;

    private MethodTestMatcher(final String expectedName, final Class... expectedParams)
    {
        this.expectedName = expectedName;
        this.expectedParams = expectedParams;
    }

    @Override
    public final boolean matchesSafely(final Method item)
    {
        if (!expectedName.equals(item.getName()))
        {
            return false;
        }
        else
        {
            Class[] params = item.getParameterTypes();
            if (params.length != expectedParams.length)
            {
                return false;
            }
            else
            {
                for (int i = 0; i < params.length; i++)
                {
                    if (!params[i].equals(expectedParams[i]))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void describeTo(Description description)
    {
        description.appendText("Method[").appendText(expectedName).
                appendText("(");
        for (int i = 0; i < expectedParams.length; i++)
        {
            if (i > 0)
            {
                description.appendText(",");
            }
            description.appendText(expectedParams[i].getName());
        }
        description.appendText(")]");
    }
}