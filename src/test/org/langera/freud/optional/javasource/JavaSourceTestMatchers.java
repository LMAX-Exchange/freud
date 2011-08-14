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

package org.langera.freud.optional.javasource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.optional.javasource.apackage.PackageDeclaration;
import org.langera.freud.optional.javasource.block.CodeBlock;
import org.langera.freud.optional.javasource.classdecl.ClassDeclaration;

public final class JavaSourceTestMatchers
{
    public static Matcher<JavaSource> classNamed(final String expectedName)
    {
        return new JavaSourceClassNameMatcher(expectedName);
    }

    public static Matcher<PackageDeclaration> packagePath(final String expected)
    {
        return new PackageDeclarationMatcher(expected);
    }

    public static Matcher<ClassDeclaration> classDeclarationOf(final String expectedName)
    {
        return new ClassDeclarationClassNameMatcher(expectedName);
    }

    public static Matcher<CodeBlock> codeBlockIn(final String expectedName)
    {
        return new CodeBlockClassNameMatcher(expectedName);
    }

    private JavaSourceTestMatchers()
    {
        // static utility
    }

    private static class PackageDeclarationMatcher extends TypeSafeMatcher<PackageDeclaration>
    {

        private String expectedPackagePath;

        private PackageDeclarationMatcher(String expectedPackagePath)
        {
            this.expectedPackagePath = expectedPackagePath;
        }

        @Override
        public final boolean matchesSafely(final PackageDeclaration item)
        {
            return expectedPackagePath.equals(item.getPackagePathAsString());
        }

        public void describeTo(Description description)
        {
            description.appendText("PackageDeclaration[").appendText(expectedPackagePath).appendText("]");
        }
    }

    private static class JavaSourceClassNameMatcher extends TypeSafeMatcher<JavaSource>
    {

        private String expectedName;

        private JavaSourceClassNameMatcher(String expectedName)
        {
            this.expectedName = expectedName;
        }

        @Override
        public final boolean matchesSafely(final JavaSource item)
        {
            return expectedName.equals(item.getFullClassName());
        }

        public void describeTo(Description description)
        {
            description.appendText("JavaSource[").appendText(expectedName).appendText("]");
        }
    }

    private static class ClassDeclarationClassNameMatcher extends TypeSafeMatcher<ClassDeclaration>
    {

        private String expectedName;

        private ClassDeclarationClassNameMatcher(String expectedName)
        {
            this.expectedName = expectedName;
        }

        @Override
        public final boolean matchesSafely(final ClassDeclaration item)
        {
            return expectedName.equals(item.getName());
        }

        public void describeTo(Description description)
        {
            description.appendText("ClassDeclaration[").appendText(expectedName).appendText("]");
        }
    }

    private static class CodeBlockClassNameMatcher extends TypeSafeMatcher<CodeBlock>
    {
        private String expectedName;

        private CodeBlockClassNameMatcher(String expectedName)
        {
            this.expectedName = expectedName;
        }


        @Override
        public final boolean matchesSafely(final CodeBlock item)
        {
            return expectedName.equals(item.getClassDeclaration().getName());
        }

        public void describeTo(Description description)
        {
            description.appendText("CodeBlock[").appendText(expectedName).appendText("]");
        }
    }
}