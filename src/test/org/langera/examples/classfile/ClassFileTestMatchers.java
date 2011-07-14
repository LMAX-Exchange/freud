package org.langera.examples.classfile;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.optional.classfile.method.ClassFileMethod;

final class ClassFileTestMatchers
{
    private ClassFileTestMatchers()
    {
        // static utility
    }

    static Matcher<ClassFileMethod> methodNamed(final String name)
    {
        return new TypeSafeMatcher<ClassFileMethod>()
        {
            @Override
            protected boolean matchesSafely(final ClassFileMethod item)
            {
                return item.getName().equals(name);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("class file method [" + name + "]");
            }
        };

    }
}
