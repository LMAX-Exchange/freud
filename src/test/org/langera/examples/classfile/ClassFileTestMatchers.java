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
