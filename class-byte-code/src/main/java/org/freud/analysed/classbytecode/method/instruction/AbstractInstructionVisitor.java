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

package org.freud.analysed.classbytecode.method.instruction;

public abstract class AbstractInstructionVisitor implements InstructionVisitor {
    @Override
    public void noArgInstruction(final Instruction instruction) {
    }

    @Override
    public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args) {
    }

    public static String typeEncoding(Class clazz) {
        final StringBuilder sb = new StringBuilder();
        if (clazz.isArray()) {
            sb.append(clazz.getName().replace('.', '/'));
        }
        else {
            if (clazz.isPrimitive()) {
                if (boolean.class.equals(clazz)) {
                    sb.append("Z");
                }
                else if (byte.class.equals(clazz)) {
                    sb.append("B");
                }
                else if (char.class.equals(clazz)) {
                    sb.append("C");
                }
                else if (double.class.equals(clazz)) {
                    sb.append("D");
                }
                else if (float.class.equals(clazz)) {
                    sb.append("F");
                }
                else if (int.class.equals(clazz)) {
                    sb.append("I");
                }
                else if (long.class.equals(clazz)) {
                    sb.append("J");
                }
                else if (short.class.equals(clazz)) {
                    sb.append("S");
                }
            }
            else {
                sb.append("L").append(clazz.getName().replace('.', '/')).append(";");
            }
        }
        return sb.toString();
    }

}
