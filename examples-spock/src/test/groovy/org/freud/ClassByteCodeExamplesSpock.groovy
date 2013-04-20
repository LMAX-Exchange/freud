package org.freud

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


    @Unroll('#analysed.name')
    def 'do not use BigDecimal.equals()'() {
    expect:
        analyse(analysed) { !hasMethodInvocation(analysed, BigDecimal, 'equals', Object) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { !it.name.contains('equals') })
    }

    @Unroll('#analysed.name')
    @FailsWith(ConditionNotSatisfiedError)
    def 'do not use BigDecimal.equals() - Failing test'() {
    expect:
        analyse(analysed) { !hasMethodInvocation(analysed, BigDecimal, 'equals', Object) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { it.name.contains('equals') })
    }

    @Unroll('#analysed.name')
    def 'do not use BigDecimal.toString()'() {
    expect:
        analyse(analysed) {
            !hasMethodInvocation(analysed, BigDecimal, 'toString') &&
                    !methodInvokedWithParams(analysed, StringBuilder, 'append', BigDecimal) &&
                    !methodInvokedWithParams(analysed, PrintStream, 'print', BigDecimal) &&
                    !methodInvokedWithParams(analysed, PrintStream, 'println', BigDecimal)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { !it.name.contains('ToString') })
    }

    @Unroll('#analysed.name')
    @FailsWith(ConditionNotSatisfiedError)
    def 'do not use BigDecimal.toString() - Failing test'() {
    expect:
        analyse(analysed) {
            !hasMethodInvocation(analysed, BigDecimal, 'toString') &&
                    !methodInvokedWithParams(analysed, StringBuilder, 'append', BigDecimal) &&
                    !methodInvokedWithParams(analysed, PrintStream, 'print', BigDecimal) &&
                    !methodInvokedWithParams(analysed, PrintStream, 'println', BigDecimal)
        }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal'])),
                            { it.name.contains('ToString') })
    }

    @Unroll('#analysed.name')
    def 'do not throw any exceptions'() {
    expect:
        analyse(analysed) { !containsInstructions(analysed, ATHROW) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { !it.name.contains('Throws') })
    }

    @Unroll('#analysed.name')
    @FailsWith(ConditionNotSatisfiedError)
    def 'do not throw any exceptions - Failing test'() {
    expect:
        analyse(analysed) { !containsInstructions(analysed, ATHROW) }
    where:
        analysed << forEach(methodsWithin(classOf(['examples.classbytecode.SomeClass'])),
                            { it.name.contains('Throws') })
    }

    @Unroll('#analysed.name')
    def 'methods should not have branch logic'() {
    expect:
        analyse(analysed) {
            !containsInstructions(analysed, Opcode.IFEQ,
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

    @Unroll('#analysed.name')
    @FailsWith(ConditionNotSatisfiedError)
    def 'methods should not have branch logic - Failing test'() {
    expect:
        analyse(analysed) {
            !containsInstructions(analysed, Opcode.IFEQ,
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
