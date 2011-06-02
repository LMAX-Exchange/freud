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