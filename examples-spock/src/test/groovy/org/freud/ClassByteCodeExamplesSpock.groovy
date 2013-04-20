package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification
import spock.lang.Unroll

import static org.freud.analysed.classbytecode.ClassByteCodeDsl.classOf
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.hasMethodInvocation
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.methodInvokedWithParams
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.methodsWithin
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class ClassByteCodeExamplesSpock extends Specification {

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
/*
    def 'do not use BigDecimal.equals()'() {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "toString")).
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", a(BigDecimal.class))))).
                within(iterator);
    }

    def 'do not throw any exceptions'() {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(containsInstructions(Opcode.ATHROW))).within(iterator);
    }

    def 'specific methods should not have branch logic'() {
        return Freud.iterateOver(ClassFileMethod.class).
                assertThat(no(methodName().is(equalTo("criticalPath"))).
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
  */
}
