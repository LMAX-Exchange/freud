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

package org.langera.freud.analysis;

import org.hamcrest.Matcher;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudRule;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.optional.javasource.importdecl.ImportDeclaration;
import org.langera.freud.optional.text.textline.TextLine;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.ClassObjectDsl.abstractClass;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classAnnotation;
import static org.langera.freud.optional.classobject.ClassObjectDsl.className;
import static org.langera.freud.optional.classobject.ClassObjectDsl.classSimpleName;
import static org.langera.freud.optional.classobject.ClassObjectDsl.hasDeclaredMethod;
import static org.langera.freud.optional.classobject.ClassObjectDsl.numberOfDeclaredMethods;
import static org.langera.freud.optional.classobject.ClassObjectDsl.packageName;
import static org.langera.freud.optional.classobject.field.FieldDsl.fieldName;
import static org.langera.freud.optional.classobject.field.FieldDsl.finalField;
import static org.langera.freud.optional.classobject.field.FieldDsl.packageProtectedField;
import static org.langera.freud.optional.classobject.field.FieldDsl.privateField;
import static org.langera.freud.optional.classobject.field.FieldDsl.protectedField;
import static org.langera.freud.optional.classobject.field.FieldDsl.staticField;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodName;
import static org.langera.freud.optional.classobject.method.MethodDsl.numberOfParams;
import static org.langera.freud.optional.javasource.importdecl.ImportDeclarationDsl.importDeclarationLastElement;
import static org.langera.freud.optional.javasource.importdecl.ImportDeclarationDsl.staticImport;
import static org.langera.freud.optional.text.textline.TextLineDsl.lineLength;

public final class JavaStyleRules
{
    //AbstractClassName	 Ensures that the names of abstract classes conforming to some regular expression.
    public static FreudRule<Class> abstractClassNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Class.class).
                assertThat(abstractClass().and(className().matches(regex)).
                        or(no(abstractClass()).and(no(className().matches(regex)))));
    }

    //AnnotationUseStyle	This check controls the style with the usage of annotations.
//AnonInnerLength	Checks for long anonymous inner classes.
//ArrayTrailingComma	Checks if array initialization contains optional trailing comma.
//ArrayTypeStyle	Checks the style of array type definitions.
//AvoidInlineConditionals	Detects inline conditionals.
//AvoidNestedBlocks	Finds nested blocks.
//AvoidStarImport	Check that finds import statements that use the * notation.
    public static FreudRule<ImportDeclaration> avoidStarImport()
    {
        return Freud.iterateOver(ImportDeclaration.class).
                assertThat(no(importDeclarationLastElement().is(equalTo("*"))));
    }

//AvoidStaticImport	Check that finds static imports.
public static FreudRule<ImportDeclaration> avoidStaticImport()
{
    return Freud.iterateOver(ImportDeclaration.class).assertThat(no(staticImport()));
}

//BooleanExpressionComplexity	 Restricts nested boolean operators (&&, ||, &, | and ^) to a specified depth (default = 3).
//ClassDataAbstractionCoupling	This metric measures the number of instantiations of other classes within the given class.
//ClassFanOutComplexity	The number of other classes a given class relies on.
//ClassTypeParameterName	 Checks that class type parameter names conform to a format specified by the format property.
//ConstantName	Checks that constant names conform to a format specified by the format property.
    public static FreudRule<Field> constantNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Field.class).forEach(staticField().and(finalField())).
                assertThat(fieldName().matches(regex));
    }

    public static FreudRule<Field> constantNameConformsToStandard()
    {
        return constantNameConformsTo("[A-Z][A-Z0-9]*(_[A-Z0-9]+)*");
    }


    //CovariantEquals	 Checks that if a class defines a covariant method equals, then it defines method equals(java.lang.Object).
//CyclomaticComplexity	Checks cyclomatic complexity against a specified limit.
//DeclarationOrder	 Checks that the parts of a class or interface declaration appear in the order suggested by the Code Conventions for the Java Programming Language.
//DefaultComesLast	 Check that the default is after all the cases in a switch statement.
//DescendantToken	Checks for restricted tokens beneath other tokens.
//DesignForExtension	Checks that classes are designed for inheritance.
//DoubleCheckedLocking	 Detect the double-checked locking idiom, a technique that tries to avoid synchronization overhead but is incorrect because of subtle artifacts of the java memory model.
//EmptyBlock	Checks for empty blocks.
//EmptyForInitializerPad	Checks the padding of an empty for initializer; that is whether a space is required at an empty for initializer, or such spaces are forbidden.
//EmptyForIteratorPad	Checks the padding of an empty for iterator; that is whether a space is required at an empty for iterator, or such spaces are forbidden.
//EmptyStatement	 Detects empty statements (standalone ';').
//EqualsAvoidNull	 Checks that any combination of String literals with optional assignment is on the left side of an equals() comparison.
    //EqualsHashCode	 Checks that classes that override equals() also override hashCode().
    public static FreudRule<Class> equalsAndHashCodeMustBeDeclaredTogether()
    {
        return Freud.iterateOver(Class.class).
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode")))));
    }

    //ExecutableStatementCount	 Restricts the number of executable statements to a specified limit (default = 30).
//ExplicitInitialization	 Checks if any class or object member explicitly initialized to default for its type value (null for object references, zero for numeric types and char and false for boolean.
//FallThrough	Checks for fall through in switch statements Finds locations where a case contains Java code - but lacks a break, return, throw or continue statement.
//FileLength	 Checks for long source files.
//FileTabCharacter	Checks to see if a file contains a tab character.
//FinalClass	 Checks that class which has only private ctors is declared as final.
//FinalLocalVariable	 Ensures that local variables that never get their values changed, must be declared final.
//FinalParameters	Check that method/constructor/catch/foreach parameters are final.
//GenericWhitespace	Checks that the whitespace around the Generic tokens < and > are correct to the typical convention.
//Header	Checks the header of the source against a fixed header file.
//HiddenField	Checks that a local variable or a parameter does not shadow a field that is defined in the same class.
//HideUtilityClassConstructor	Make sure that utility classes (classes that contain only static methods) do not have a public constructor.
//IllegalCatch	Catching java.lang.Exception, java.lang.Error or java.lang.RuntimeException is almost never acceptable.
//IllegalImport	 Checks for imports from a set of illegal packages.
//IllegalInstantiation	 Checks for illegal instantiations where a factory method is preferred.
//IllegalThrows	Throwing java.lang.Error or java.lang.RuntimeException is almost never acceptable.
//IllegalToken	 Checks for illegal tokens.
//IllegalTokenText	 Checks for illegal token text.
//IllegalType	 Checks that particular class are never used as types in variable declarations, return values or parameters.
//ImportControl	Check that controls what packages can be imported in each package.
//ImportOrder	Ensures that groups of imports come in a specific order.
//Indentation	Checks correct indentation of Java Code.
//InnerAssignment	 Checks for assignments in subexpressions, such as in String s = Integer.toString(i = 2);.
//InnerTypeLast	 Check nested (internal) classes/interfaces are declared at the bottom of the class after all method and field declarations.
//InterfaceIsType	Implements Bloch, Effective Java, Item 17 - Use Interfaces only to define types.
//JUnitTestCase	Ensures that the setUp(), tearDown()methods are named correctly, have no arguments, return void and are either public or protected.
//JavaNCSS	This check calculates the Non Commenting Source Statements (NCSS) metric for java source files and methods.
//JavadocMethod	Checks the Javadoc of a method or constructor.
//JavadocPackage	Checks that all packages have a package documentation.
//JavadocStyle	Custom Checkstyle Check to validate Javadoc.
//JavadocType	Checks the Javadoc of a type.
//JavadocVariable	Checks that a variable has Javadoc comment.
//LeftCurly	 Checks the placement of left curly braces on types, methods and other blocks:
//LineLength	Checks for long lines.
    public static FreudRule<TextLine> lineLengthLessThan(final int value)
    {
        return Freud.iterateOver(TextLine.class).
                assertThat(lineLength().lessThan(value));
    }

    //LocalFinalVariableName	 Checks that local final variable names conform to a format specified by the format property.
//LocalVariableName	 Checks that local, non-final variable names conform to a format specified by the format property.
//MagicNumber	 Checks for magic numbers.
//MemberName	 Checks that instance variable names conform to a format specified by the format property.
    public static FreudRule<Field> memberNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Field.class).forEach(no(staticField())).assertThat(fieldName().matches(regex));
    }

    public static FreudRule<Field> memberNameConformsToStandard()
    {
        return memberNameConformsTo("[a-z][a-zA-Z0-9]*");
    }

    //MethodCount	Checks the number of methods declared in each type.
    public static FreudRule<Class> numberOfMethodsLessThan(final int value)
    {
        return Freud.iterateOver(Class.class).
                assertThat(numberOfDeclaredMethods().lessThan(value));
    }
//MethodLength	 Checks for long methods.

    //MethodName	 Checks that method names conform to a format specified by the format property.
    public static FreudRule<Method> methodNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Method.class).assertThat(methodName().matches(regex));
    }

    public static FreudRule<Method> methodNameConformsToStandard()
    {
        return methodNameConformsTo("[a-z][a-zA-Z0-9]*");
    }

//MethodParamPad	 Checks the padding between the identifier of a method definition, constructor definition, method call, or constructor invocation; and the left parenthesis of the parameter list.
//MethodTypeParameterName	 Checks that class type parameter names conform to a format specified by the format property.

    //MissingCtor	 Checks that classes (except abstract one) define a ctor and don't rely on the default one.
//MissingDeprecated	 This class is used to verify that both the
//MissingOverride	 This class is used to verify that the
//MissingSwitchDefault	 Checks that switch statement has "default" clause.
//ModifiedControlVariable	Check for ensuring that for loop control variables are not modified inside the for block.
//ModifierOrder	 Checks that the order of modifiers conforms to the suggestions in the Java Language specification, sections 8.1.1, 8.3.1 and 8.4.3.
//MultipleStringLiterals	Checks for multiple occurrences of the same string literal within a single file.
//MultipleVariableDeclarations	 Checks that each variable declaration is in its own statement and on its own line.
//MutableException	 Ensures that exceptions (defined as any class name conforming to some regular expression) are immutable.
//NPathComplexity	Checks the npath complexity against a specified limit (default = 200).
//NeedBraces	 Checks for braces around code blocks.
//NestedForDepth	Restricts nested for blocks to a specified depth (default = 1).
//NestedIfDepth	Restricts nested if-else blocks to a specified depth (default = 1).
//NestedTryDepth	Restricts nested try-catch-finally blocks to a specified depth (default = 1).
//NewlineAtEndOfFile	 Checks that there is a newline at the end of each file.
//NoClone	 Checks that the clone method is not overridden from the Object class.
//NoFinalizer	Checks that no method having zero parameters is defined using the name finalize.
    public static FreudRule<Class> noFinalizerDeclared()
    {
        return Freud.iterateOver(Class.class).assertThat(no(hasDeclaredMethod("finalize")));
    }

    //NoWhitespaceAfter	 Checks that there is no whitespace after a token.
//NoWhitespaceBefore	 Checks that there is no whitespace before a token.
//OneStatementPerLine	Checks there is only one statement per line.
//OperatorWrap	 Checks line wrapping for operators.
//OuterTypeFilename	Checks that the outer type name and the file name match.
//OuterTypeNumber	Checks for the number of defined types at the "outer" level.
//PackageAnnotation	This check makes sure that all package annotations are in the package-info.java file.
//PackageDeclaration	Ensures there is a package declaration and (optionally) in the correct directory.

//PackageName	 Checks that package names conform to a format specified by the format property.
    public static FreudRule<Class> packageNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Class.class).
                assertThat(packageName().matches(regex));
    }

    public static FreudRule<Class> packageNameConformsToStandard()
    {
        return packageNameConformsTo("[a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*");
    }
//ParameterAssignment	 Disallow assignment of parameters.
//ParameterName	 Checks that parameter names conform to a format specified by the format property.
//ParameterNumber	 Checks the number of parameters that a method or constructor has.
    public static FreudRule<Method> numberOfParametersLessThan(final int value)
    {
        return Freud.iterateOver(Method.class).
                assertThat(numberOfParams().lessThan(value));
    }

    //ParenPad	Checks the padding of parentheses; that is whether a space is required after a left parenthesis and before a right parenthesis, or such spaces are forbidden, with the exception that it does not check for padding of the right parenthesis at an empty for iterator.
//RedundantImport	 Checks for imports that are redundant.
//RedundantModifier	Checks for redundant modifiers in interface and annotation definitions.
//RedundantThrows	Checks for redundant exceptions declared in throws clause such as duplicates, unchecked exceptions or subclasses of another declared exception.
//Regexp	 A check that makes sure that a specified pattern exists (or not) in the file.
//RegexpHeader	Checks the header of the source against a header file that contains a
//RegexpMultiline	Implementation of a check that looks that matches across multiple lines in any file type.
//RegexpSingleline	Implementation of a check that looks for a single line in any file type.
//RegexpSinglelineJava	Implementation of a check that looks for a single line in Java files.
//RequireThis	Checks that code doesn't rely on the "this" default.
//ReturnCount	 Restricts return statements to a specified count (default = 2).
//RightCurly	 Checks the placement of right curly braces.
//SimplifyBooleanExpression	 Checks for overly complicated boolean expressions.
//SimplifyBooleanReturn	 Checks for overly complicated boolean return statements.
//StaticVariableName	 Checks that static, non-final variable names conform to a format specified by the format property.
    public static FreudRule<Field> staticVariableNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Field.class).forEach(staticField().and(no(finalField()))).
                assertThat(fieldName().matches(regex));
    }

    public static FreudRule<Field> staticVariableNameConformsToStandard()
    {
        return staticVariableNameConformsTo("[a-z][a-zA-Z0-9]*");
    }
//StrictDuplicateCode	Performs a line-by-line comparison of all code lines and reports duplicate code if a sequence of lines differs only in indentation.
//StringLiteralEquality	Checks that string literals are not used with == or !=.
//SuperClone	 Checks that an overriding clone() method invokes super.clone().
//SuperFinalize	 Checks that an overriding finalize() method invokes super.finalize().

//SuppressWarnings	 This check allows you to specify what warnings that

    @SuppressWarnings("unchecked")
    public static FreudRule<Method> warningsThatCannotBeSuppressedForMethods(final String... warnings)
    {
        final Matcher[] warningMatchers = new Matcher[warnings.length];
        for (int i = 0, size = warnings.length; i < size; i++)
        {
            warningMatchers[i] = equalTo(warnings[i]);
        }
        return warningsThatCannotBeSuppressedForMethods(warningMatchers);
    }

    public static FreudRule<Method> warningsThatCannotBeSuppressedForMethods(final Matcher<String>... warningMatchers)
    {
        return Freud.iterateOver(Method.class).
                assertThat(no(methodAnnotation(SuppressWarnings.class, allOf(warningMatchers))));
    }

    @SuppressWarnings("unchecked")
    public static FreudRule<Class> warningsThatCannotBeSuppressedForClasses(final String... warnings)
    {
        final Matcher[] warningMatchers = new Matcher[warnings.length];
        for (int i = 0, size = warnings.length; i < size; i++)
        {
            warningMatchers[i] = equalTo(warnings[i]);
        }
        return warningsThatCannotBeSuppressedForClasses(warningMatchers);
    }

    public static FreudRule<Class> warningsThatCannotBeSuppressedForClasses(final Matcher<String>... warningMatchers)
    {
        return Freud.iterateOver(Class.class).
                assertThat(no(classAnnotation(SuppressWarnings.class, allOf(warningMatchers))));
    }

    //ThrowsCount	 Restricts throws statements to a specified count (default = 1).
//TodoComment	 A check for TODO comments.
//TrailingComment	 The check to ensure that requires that comments be the only thing on a line.
//Translation	 The TranslationCheck class helps to ensure the correct translation of code by checking property files for consistency regarding their keys.
//TypeName	 Checks that type names conform to a format specified by the format property.
    public static FreudRule<Class> typeNameConformsTo(final String regex)
    {
        return Freud.iterateOver(Class.class).assertThat(classSimpleName().matches(regex));
    }

    public static FreudRule<Class> typeNameConformsToStandard()
    {
        return typeNameConformsTo("[A-Z][a-zA-Z0-9]*");
    }

    //TypecastParenPad	Checks the padding of parentheses for typecasts.
//UncommentedMain	Detects uncommented main methods.
//UnnecessaryParentheses	 Checks if unnecessary parentheses are used in a statement or expression.
//UnusedImports	 Checks for unused import statements.
//UpperEll	Checks that long constants are defined with an upper ell.
//VisibilityModifier	Checks visibility of class members.
    public static FreudRule<Field> limitMemberVisibilityToPrivate(Matcher<Field> exclusions)
    {
        return limitMemberVisibilityInternal(exclusions, privateField());
    }

    public static FreudRule<Field> limitMemberVisibilityToProtected(Matcher<Field> exclusions)
    {
        return limitMemberVisibilityInternal(exclusions, privateField().or(protectedField()));
    }

    public static FreudRule<Field> limitMemberVisibilityToPackageProtected(Matcher<Field> exclusions)
    {
        return limitMemberVisibilityInternal(exclusions, privateField().or(protectedField()).or(packageProtectedField()));
    }

    private static FreudRule<Field> limitMemberVisibilityInternal(final Matcher<Field> exclusions, final FreudMatcher<Field> limit)
    {
        return Freud.iterateOver(Field.class).forEach(no(exclusions).and(no(staticField().and(finalField())))).
                assertThat(limit);
    }

//WhitespaceAfter	 Checks that a token is followed by whitespace, with the exception that it does not check for whitespace after the semicolon of an empty for iterator.
//WhitespaceAround	 Checks that a token is surrounded by whitespace.
//WriteTag	 Outputs a JavaDoc tag as information.

}
