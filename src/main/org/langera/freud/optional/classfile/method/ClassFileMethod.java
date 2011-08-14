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

package org.langera.freud.optional.classfile.method;

import org.langera.freud.optional.classfile.ClassFileElement;
import org.langera.freud.optional.classfile.ClassFileInnerClass;
import org.langera.freud.optional.classfile.method.instruction.Instruction;
import org.langera.freud.optional.classfile.method.instruction.InstructionVisitor;

import java.util.List;

public interface ClassFileMethod extends ClassFileElement
{
    String getName();

    String getDesc();

    String getSignature();

    String[] getExceptions();

    boolean isStatic();

    boolean isSynchronized();

    boolean isBridge();

    boolean isVarargs();

    boolean isNative();

    boolean isStrict();

    boolean isAbstract();

    List<ClassFileInnerClass> getAnonymousClassList();

    LocalVariable getLocalVariable(String name);

    LocalVariable getLocalVariable(int index);

    void findInstruction(final InstructionVisitor instructionVisitor);

    Instruction getInstruction(int index);

    String getReturnType();

    String getLocalVariableType(int index);
}
