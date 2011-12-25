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

package org.langera.examples.javasource;

import org.hamcrest.Matchers;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.javasource.JavaSource;
import org.langera.freud.optional.javasource.block.CodeBlock;
import org.langera.freud.optional.javasource.packagedecl.PackageDeclaration;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.codeBlockLines;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.hasMethodCall;
import static org.langera.freud.optional.javasource.block.CodeBlockDsl.method;
import static org.langera.freud.optional.javasource.methoddecl.MethodDeclarationDsl.hasDeclaredAnnotation;
import static org.langera.freud.optional.javasource.packagedecl.PackageDeclarationDsl.packageDepth;

public final class JavaSourceExamples
{
    private JavaSourceExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser packageDepthMustBeBetween3And7(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(PackageDeclaration.class).
                assertThat(packageDepth().lessThanOrEqualTo(7).and(
                        packageDepth().greaterThanOrEqualTo(3))).within(iterator);
    }

    public static FreudAnalyser noSystemOutPrintInCode(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println")))).within(iterator);
    }

    public static FreudAnalyser codeBlockSizeIsLimitedTo30Lines(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                assertThat(codeBlockLines().lessThanOrEqualTo(30)).within(iterator);
    }

    public static FreudAnalyser codeBlockSizeIsLimitedToOneLineIfFreudNotSuppressed(final AnalysedObjectIterator<JavaSource> iterator)
    {
        return Freud.iterateOver(CodeBlock.class).
                forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("\"freud:"))))).
                assertThat(codeBlockLines().lessThanOrEqualTo(1)).within(iterator);
    }
}
