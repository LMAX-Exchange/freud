package org.freud

import spock.lang.Specification

class ClassByteCodeExamplesSpock extends Specification {

//    def 'do not use BigDecimal.equals'() {
//    expect:
//        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
//                (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
//        }
//    where:
//        analysed << methodsWithin(classOf(['examples.classbytecode.ClassThatUsesBigDecimal']))

//        return Freud.iterateOver(ClassFileMethod.class).
//                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class))).within(iterator);
//    }
/*
    def 'do not use BigDecimal.toString()'() {
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
