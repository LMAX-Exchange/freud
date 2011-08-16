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

import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.method.ClassFileMethod;
import org.langera.freud.optional.classfile.method.instruction.Opcode;

import java.math.BigDecimal;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.a;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.containsInstructions;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.hasMethodInvocation;
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.methodInvokedWithParams;

public final class ClassFileExamples
{
    private ClassFileExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser doNotUseBigDecimalToString(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "toString")).
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", a(BigDecimal.class)))));
    }

    public static FreudAnalyser doNotUseBigDecimalEquals(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class)));
    }


    public static FreudAnalyser doNotThrowAnyExceptions(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(containsInstructions(Opcode.ATHROW)));
    }






//    public static void main(String[] args) throws IOException, ResourceParserException
//    {
//        final AnalysedObjectIterator<ClassFile> iterator = ResourceIterators.filesByPathResourceIterator(
//                new AsmClassFileParser(new InnerClassFileResourceIdentifierGetter()
//                {
//                    @Override
//                    public String getResourceIdentifier(final String name, final ClassFile currentClassFile, final String currentResourceIdentifier)
//                    {
//                        int indexOfClassesDir = currentResourceIdentifier.indexOf("classes/");
//                        return currentResourceIdentifier.substring(0, indexOfClassesDir + "classes/".length()) + name + ".class";
//                    }
//                }),
//                new FilenameFilter()
//                {
//                    @Override
//                    public boolean accept(final File dir, final String name)
//                    {
//                        return name.endsWith(".class");
//                    }
////                }, true, "../java/classes");
////                }, true, "../trunk/build/classes/");
//                }, true, "build");
//
//        final FreudAnalyser analyser = doNotUseBigDecimalEquals(iterator);
//
//        analyser.analyse(new PrintAnalysisListener(new PrintWriter(System.out)));
//    }

}

