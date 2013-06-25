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



package org.freud

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod
import org.freud.analysed.classbytecode.method.instruction.Opcode
import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification
import spock.lang.Unroll

import static org.freud.analysed.classbytecode.ClassByteCodeDsl.classOf
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.containsInstructions
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.hasMethodInvocation
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.methodInvokedWithParams
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.methodsWithin
import static org.freud.analysed.classbytecode.method.instruction.Opcode.ATHROW
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class ClassByteCodeExamplesSpock extends Specification {


    @Unroll
    def '#analysed.name do not use BigDecimal.equals()'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method -> !hasMethodInvocation(method, BigDecimal, 'equals', Object) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { !it.name.contains('equals') })
    }

    @Unroll
    @FailsWith(ConditionNotSatisfiedError)
    def '#analysed.name do not use BigDecimal.equals() - Failing test'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method -> !hasMethodInvocation(method, BigDecimal, 'equals', Object) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { it.name.contains('equals') })
    }

    @Unroll
    def '#analysed.name do not use BigDecimal.toString()'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method ->
            !hasMethodInvocation(method, BigDecimal, 'toString') &&
                    !methodInvokedWithParams(method, StringBuilder, 'append', BigDecimal) &&
                    !methodInvokedWithParams(method, PrintStream, 'print', BigDecimal) &&
                    !methodInvokedWithParams(method, PrintStream, 'println', BigDecimal)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { !it.name.contains('ToString') })
    }

    @Unroll
    @FailsWith(ConditionNotSatisfiedError)
    def '#analysed.name do not use BigDecimal.toString() - Failing test'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method ->
            !hasMethodInvocation(method, BigDecimal, 'toString') &&
                    !methodInvokedWithParams(method, StringBuilder, 'append', BigDecimal) &&
                    !methodInvokedWithParams(method, PrintStream, 'print', BigDecimal) &&
                    !methodInvokedWithParams(method, PrintStream, 'println', BigDecimal)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { it.name.contains('ToString') })
    }

    @Unroll
    def '#analysed.name do not throw any exceptions'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method -> !containsInstructions(method, ATHROW) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { !it.name.contains('Throws') })
    }

    @Unroll
    @FailsWith(ConditionNotSatisfiedError)
    def '#analysed.name do not throw any exceptions - Failing test'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method -> !containsInstructions(method, ATHROW) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { it.name.contains('Throws') })
    }

    @Unroll
    def '#analysed.name should not have branch logic'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method ->
            !containsInstructions(method, Opcode.IFEQ,
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
                                  Opcode.JSR_W)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { !it.name.contains('BranchLogic') })
    }

    @Unroll
    @FailsWith(ConditionNotSatisfiedError)
    def '#analysed.name should not have branch logic - Failing test'() {
    expect:
        analyse(analysed) { ClassByteCodeMethod method ->
            !containsInstructions(method, Opcode.IFEQ,
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
                                  Opcode.JSR_W)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { it.name.contains('BranchLogic') })
    }
}
