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

package examples.org.freud;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.method.instruction.Opcode;
import org.freud.java.Freud;
import org.junit.Test;

import java.math.BigDecimal;

import static examples.org.freud.matchers.ClassByteCodeMethodMatchers.a;
import static examples.org.freud.matchers.ClassByteCodeMethodMatchers.containsInstructions;
import static examples.org.freud.matchers.ClassByteCodeMethodMatchers.hasMethodInvocation;
import static examples.org.freud.matchers.ClassByteCodeMethodMatchers.methodInvokedWithParams;
import static java.util.Arrays.asList;
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.classOf;
import static org.freud.analysed.classbytecode.ClassByteCodeDsl.methodsWithin;
import static org.freud.java.matcher.FreudDsl.no;


public final class ClassByteCodeExamplesJunit {

    private AnalysisListenerStub listener = new AnalysisListenerStub();

    @Test
    public void doNotUseBigDecimalToString() {
        Freud.iterateOver(ClassByteCodeMethod.class).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "toString")).
                        and(no(methodInvokedWithParams(StringBuilder.class, "append", a(BigDecimal.class))))).
                in(methodsWithin(classOf(asList("examples.classbytecode.SomeClass", "examples.classbytecode.ClassThatUsesBigDecimal")))).
                analyse(listener);
    }

    @Test
    public void doNotUseBigDecimalEquals() {
        Freud.iterateOver(ClassByteCodeMethod.class).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class))).in(methodsWithin(classOf(
                asList("examples.classbytecode.SomeClass", "examples.classbytecode.ClassThatUsesBigDecimal")))).
                analyse(listener);
    }


    @Test
    public void doNotThrowAnyExceptions() {
        Freud.iterateOver(ClassByteCodeMethod.class).
                assertThat(no(containsInstructions(Opcode.ATHROW))).in(methodsWithin(classOf(asList("examples.classbytecode.SomeClass")))).
                analyse(listener);
    }

    @Test
    public void specificMethodsShouldNotHaveBranchLogic() {
        Freud.iterateOver(ClassByteCodeMethod.class).
                assertThat(no(containsInstructions(Opcode.IFEQ,
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
                                                   Opcode.JSR_W))).in(methodsWithin(classOf(asList("examples.classbytecode.SomeClass")))).
                analyse(listener);
    }
}

