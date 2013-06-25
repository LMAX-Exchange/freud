/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.method.instruction.AbstractInstructionVisitor;
import org.freud.analysed.classbytecode.method.instruction.Instruction;
import org.freud.analysed.classbytecode.method.instruction.Opcode;
import org.freud.analysed.classbytecode.method.instruction.OperandStack;
import org.freud.analysed.classbytecode.parser.asm.AsmClassByteCodeFromFileCreator;
import org.freud.analysed.classbytecode.parser.asm.AsmClassByteCodeFromNameCreator;
import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.util.Arrays;

import static org.freud.analysed.classbytecode.method.instruction.AbstractInstructionVisitor.typeEncoding;
import static org.freud.core.FreudSource.typeOf;

@SuppressWarnings("unchecked")
public final class ClassByteCodeDsl {

    private ClassByteCodeDsl() {
        // static utility
    }

    public static <T> Iterable<ClassByteCode> classOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return classOf(new FreudSource(iterable, type));
    }

    public static Iterable<ClassByteCode> classOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, ClassByteCode>(new AsmClassByteCodeFromFileCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, ClassByteCode>(new AsmClassByteCodeFromNameCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to ClassByteCode");
    }

    public static Iterable<ClassByteCode> classOf(FreudSource<String> source, ClassLoader classLoader) {
        return new AnalysedObjects<String, ClassByteCode>(new AsmClassByteCodeFromNameCreator(classLoader), source.getSources());
    }

    public static Iterable<ClassByteCodeMethod> methodsWithin(Iterable<ClassByteCode> classes) {
        return new SubTypeAnalysedObjects<ClassByteCode, ClassByteCodeMethod>(new MethodsOfClassByteCodeCreator(), classes);
    }

    public static Iterable<ClassByteCodeField> fieldsWithin(Iterable<ClassByteCode> classes) {
        return new SubTypeAnalysedObjects<ClassByteCode, ClassByteCodeField>(new FieldsOfClassByteCodeCreator(), classes);
    }

    private static final String[] EMPTY_ARGS = new String[0];

    public static boolean hasMethodInvocation(final ClassByteCodeMethod analysed, final Class expectedOwner, final String expectedMethodName, final Class... expectedParamsDeclared) {
        final boolean[] found = new boolean[]{false};
        final String expectedOwnerName = typeEncoding(expectedOwner);
        final String[] expectedParamNames = (expectedParamsDeclared.length == 0) ? EMPTY_ARGS : new String[expectedParamsDeclared.length];
        for (int i = 0, size = expectedParamsDeclared.length; i < size; i++) {
            expectedParamNames[i] = typeEncoding(expectedParamsDeclared[i]);

        }
        found[0] = false;
        analysed.findInstruction(new AbstractInstructionVisitor() {
            @Override
            public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args) {
                if (!found[0] && expectedOwnerName.equals(owner) &&
                        expectedMethodName.equals(methodName) &&
                        Arrays.equals(expectedParamNames, args)) {
                    found[0] = true;
                }

            }
        });
        return found[0];
    }


    public static boolean methodInvokedWithParams(final ClassByteCodeMethod analysed,
                                                  final Class expectedOwner,
                                                  final String expectedMethodName,
                                                  final Class... expectedParamTypes) {
        final String expectedOwnerName = typeEncoding(expectedOwner);
        final boolean[] found = new boolean[1];
        found[0] = false;
        analysed.findInstruction(new AbstractInstructionVisitor() {
            @Override
            public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args) {
                if (!found[0] && expectedOwnerName.equals(owner) &&
                        expectedMethodName.equals(methodName)) {
                    Instruction prevInstruction = analysed.getInstruction(instruction.getInstructionIndex() - 1);
                    OperandStack operandStack = prevInstruction.getOperandStack();
                    found[0] = true;
                    for (int i = expectedParamTypes.length - 1; i >= 0; i--) {
                        final String expectedType = typeEncoding(expectedParamTypes[i]);
                        if (!expectedType.equals(operandStack.getOperandType())) {
                            found[0] = false;
                            break;
                        }
                        operandStack = operandStack.next();
                    }
                }
            }
        });
        return found[0];
    }


    public static boolean containsInstructions(final ClassByteCodeMethod analysed, final Opcode... opcodes) {

        final Instruction[] found = new Instruction[1];
        analysed.findInstruction(new AbstractInstructionVisitor() {
            @Override
            public void noArgInstruction(final Instruction instruction) {
                for (int i = 0; i < opcodes.length; i++) {
                    Opcode opcode = opcodes[i];
                    if (instruction.getOpcode() == opcode) {
                        found[0] = instruction;
                        break;
                    }
                }
            }
        });
        return found[0] != null;
    }

}
