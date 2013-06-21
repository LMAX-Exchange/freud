package examples.org.freud.matchers;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.freud.analysed.classbytecode.method.instruction.AbstractInstructionVisitor;
import org.freud.analysed.classbytecode.method.instruction.CastOperandStack;
import org.freud.analysed.classbytecode.method.instruction.Instruction;
import org.freud.analysed.classbytecode.method.instruction.Opcode;
import org.freud.analysed.classbytecode.method.instruction.OperandStack;
import org.freud.java.matcher.FreudExtendedMatcher;
import org.freud.java.matcher.RegexMatcherAdapter;
import org.freud.java.matcher.StringMatcherBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Arrays;

import static org.freud.analysed.classbytecode.method.instruction.AbstractInstructionVisitor.typeEncoding;

public final class ClassByteCodeMethodMatchers {

    private ClassByteCodeMethodMatchers() {
        // static utility
    }

    public static StringMatcherBuilder<ClassByteCodeMethod> methodName() {
        return new StringMatcherBuilder<ClassByteCodeMethod>(new RegexMatcherAdapter<ClassByteCodeMethod>() {
            @Override
            public String getStringToMatch(final ClassByteCodeMethod toBeAnalysed) {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName() {
                return "MethodName";
            }
        });
    }

    private static final String[] EMPTY_ARGS = new String[0];

    public static FreudExtendedMatcher<ClassByteCodeMethod> hasMethodInvocation
            (final Class expectedOwner, final String expectedMethodName, final Class... expectedParamsDeclared) {
        return new FreudExtendedMatcher<ClassByteCodeMethod>() {
            private String expectedOwnerName;
            private String[] expectedParamNames;

            {
                expectedOwnerName = typeEncoding(expectedOwner);
                expectedParamNames = (expectedParamsDeclared.length == 0) ? EMPTY_ARGS : new String[expectedParamsDeclared.length];
                for (int i = 0, size = expectedParamsDeclared.length; i < size; i++) {
                    expectedParamNames[i] = typeEncoding(expectedParamsDeclared[i]);

                }
            }

            @Override
            protected boolean matchesSafely(final ClassByteCodeMethod item) {
                final boolean[] found = new boolean[]{false};
                found[0] = false;
                item.findInstruction(new AbstractInstructionVisitor() {
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

            @Override
            public void describeTo(final Description description) {
                description.appendText("hasMethodInvocation(" + expectedOwner.getName() + ", " + expectedMethodName + ", " + Arrays.toString(expectedParamNames) + ")");
            }
        };
    }

    public static FreudExtendedMatcher<ClassByteCodeMethod> methodInvokedWithParams
            (final Class expectedOwner,
             final String expectedMethodName,
             final Matcher<OperandStack>... expectedParamsPassed) {
        return new FreudExtendedMatcher<ClassByteCodeMethod>() {
            private String expectedOwnerName;

            {
                expectedOwnerName = typeEncoding(expectedOwner);
            }

            @Override
            protected boolean matchesSafely(final ClassByteCodeMethod item) {
                final boolean[] found = new boolean[1];
                found[0] = false;
                item.findInstruction(new AbstractInstructionVisitor() {
                    @Override
                    public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args) {
                        if (!found[0] && expectedOwnerName.equals(owner) &&
                                expectedMethodName.equals(methodName)) {
                            Instruction prevInstruction = item.getInstruction(instruction.getInstructionIndex() - 1);
                            OperandStack operandStack = prevInstruction.getOperandStack();
                            found[0] = true;
                            for (int i = expectedParamsPassed.length - 1; i >= 0; i--) {
                                Matcher<OperandStack> matcher = expectedParamsPassed[i];
                                if (!matcher.matches(operandStack)) {
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

            @Override
            public void describeTo(final Description description) {
                description.appendText("methodInvokedWithParams(" + expectedOwner.getName() + ", " + expectedMethodName + ")");
            }
        };
    }

    public static FreudExtendedMatcher<ClassByteCodeMethod> containsInstructions(final Opcode... opcodes) {
        return new FreudExtendedMatcher<ClassByteCodeMethod>() {
            private Instruction found = null;

            @Override
            protected boolean matchesSafely(final ClassByteCodeMethod item) {
                item.findInstruction(new AbstractInstructionVisitor() {
                    @Override
                    public void noArgInstruction(final Instruction instruction) {
                        for (int i = 0; i < opcodes.length; i++) {
                            Opcode opcode = opcodes[i];
                            if (instruction.getOpcode() == opcode) {
                                found = instruction;
                                break;
                            }
                        }
                    }
                });
                return found != null;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("containsInstructions(");
                for (int i = 0; i < opcodes.length; i++) {
                    Opcode opcode = opcodes[i];
                    description.appendText(opcode.name());
                    description.appendText(", ");

                }
                description.appendText(") found");
            }
        };
    }

    public static FreudExtendedMatcher<OperandStack> a(final Class<?> aClass) {
        return new FreudExtendedMatcher<OperandStack>() {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item) {
                return expectedType.equals(item.getOperandType());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("a(" + aClass + ")");
            }
        };
    }

    public static FreudExtendedMatcher<OperandStack> aConstantOf(final Class<?> aClass) {
        return new FreudExtendedMatcher<OperandStack>() {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item) {
                return expectedType.equals(item.getOperandType()) &&
                        item.getGeneratingOpcode().isConstant();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("aConstantOf(" + aClass + ")");
            }
        };
    }

    public static FreudExtendedMatcher<OperandStack> castOf(final Class<?> aClass) {
        return new FreudExtendedMatcher<OperandStack>() {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item) {
                if (item instanceof CastOperandStack) {
                    return expectedType.equals(((CastOperandStack) item).getFromType());
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("a(" + aClass + ")");
            }
        };
    }
}
