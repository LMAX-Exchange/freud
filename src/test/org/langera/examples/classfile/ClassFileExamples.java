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
import static org.langera.freud.optional.classfile.method.ClassFileMethodDsl.methodName;

public final class ClassFileExamples
{
    private ClassFileExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser doNotUseBigDecimalToString(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "toString")).
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", a(BigDecimal.class))))).within(iterator);
    }

    public static FreudAnalyser doNotUseBigDecimalEquals(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class))).within(iterator);
    }


    public static FreudAnalyser doNotThrowAnyExceptions(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(containsInstructions(Opcode.ATHROW))).within(iterator);
    }

    public static FreudAnalyser specificMethodsShouldNotHaveBranchLogic(final AnalysedObjectIterator<ClassFile> iterator)
    {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(methodName().matches("criticalPath")).
                            or(no(containsInstructions(Opcode.IFEQ,
                                                       Opcode.IFLT,
                                                       Opcode.IFLE,
                                                       Opcode.IFNE,
                                                       Opcode.IFGT,
                                                       Opcode.IFGE,
                                                       Opcode.IFNULL,
                                                       Opcode.IFNONNULL,
                                                       Opcode.IF_ICMPEQ,
                                                       Opcode.IF_ICMPGE,
                                                       Opcode.IF_ICMPGT,
                                                       Opcode.IF_ICMPLE,
                                                       Opcode.IF_ICMPLT,
                                                       Opcode.IF_ICMPNE,
                                                       Opcode.IF_ACMPEQ,
                                                       Opcode.IF_ACMPNE,
                                                       Opcode.TABLESWITCH,
                                                       Opcode.LOOKUPSWITCH,
                                                       Opcode.GOTO,
                                                       Opcode.GOTO_W,
                                                       Opcode.JSR,
                                                       Opcode.JSR_W)))).within(iterator);
    }
}

