package org.langera.freud.optional.classfile.method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.optional.classfile.method.instruction.AbstractInstructionVisitor;
import org.langera.freud.optional.classfile.method.instruction.CastOperandStack;
import org.langera.freud.optional.classfile.method.instruction.Instruction;
import org.langera.freud.optional.classfile.method.instruction.OperandStack;

import java.util.Arrays;

import static org.langera.freud.optional.classfile.method.instruction.AbstractInstructionVisitor.typeEncoding;

public final class ClassFileMethodDsl
{

    private ClassFileMethodDsl()
    {
        // static utility
    }

    private static final String[] EMPTY_ARGS = new String[]{""};

    public static FreudMatcher<ClassFileMethod> hasMethodInvocation
            (final Class expectedOwner, final String expectedMethodName, final Class... expectedParamsDeclared)
    {
        return new FreudMatcher<ClassFileMethod>()
        {
            private String expectedOwnerName;
            private String[] expectedParamNames;

            {
                expectedOwnerName = typeEncoding(expectedOwner);
                expectedParamNames = (expectedParamsDeclared.length == 0) ? EMPTY_ARGS : new String[expectedParamsDeclared.length];
                for (int i = 0, size = expectedParamsDeclared.length; i < size; i++)
                {
                    expectedParamNames[i] = typeEncoding(expectedParamsDeclared[i]);

                }
            }

            @Override
            protected boolean matchesSafely(final ClassFileMethod item)
            {
                final boolean[] found = new boolean[1];
                found[0] = false;
                item.findInstruction(new AbstractInstructionVisitor()
                {
                    @Override
                    public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args)
                    {
                        if (!found[0] && expectedOwnerName.equals(owner) &&
                                expectedMethodName.equals(methodName) &&
                                Arrays.equals(expectedParamNames, args))
                        {
                            found[0] = true;
                        }

                    }
                });
                return found[0];
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("hasMethodInvocation(" + expectedOwner.getName() + ", " + expectedMethodName + ", " + Arrays.toString(expectedParamNames) + ")");
            }
        };
    }

    public static FreudMatcher<ClassFileMethod> methodInvokedWithParams
            (final Class expectedOwner, final String expectedMethodName, final Matcher<OperandStack>... expectedParamsPassed)
    {
        return new FreudMatcher<ClassFileMethod>()
        {
            private String expectedOwnerName;

            {
                expectedOwnerName = typeEncoding(expectedOwner);
            }

            @Override
            protected boolean matchesSafely(final ClassFileMethod item)
            {
                final boolean[] found = new boolean[1];
                found[0] = false;
                item.findInstruction(new AbstractInstructionVisitor()
                {
                    @Override
                    public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args)
                    {
                        if (!found[0] && expectedOwnerName.equals(owner) &&
                                expectedMethodName.equals(methodName))
                        {
                            OperandStack operandStack = instruction.getOperandStack();
                            found[0] = true;
                            for (int i = expectedParamsPassed.length - 1; operandStack.next() != null && i >= 0; i--)
                            {
                                Matcher<OperandStack> matcher = expectedParamsPassed[i];
                                if (!matcher.matches(operandStack))
                                {
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
            public void describeTo(final Description description)
            {
                description.appendText("methodInvokedWithParams(" + expectedOwner.getName() + ", " + expectedMethodName + ")");
            }
        };
    }

    public static FreudMatcher<OperandStack> a(final Class<?> aClass)
    {
        return new FreudMatcher<OperandStack>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item)
            {
                return expectedType.equals(item.getOperandType(0));
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("a(" + aClass + ")");
            }
        };
    }

    public static FreudMatcher<OperandStack> aConstantOf(final Class<?> aClass)
    {
        return new FreudMatcher<OperandStack>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item)
            {
                return expectedType.equals(item.getOperandType(0)) &&
                        item.generatingOpcode().isConstant();
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("aConstantOf(" + aClass + ")");
            }
        };
    }

    // TODO - follow prev instruction and use Static operand stack
    public static FreudMatcher<OperandStack> castOf(final Class<?> aClass)
    {
        return new FreudMatcher<OperandStack>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final OperandStack item)
            {
                if (item instanceof CastOperandStack)
                {
                    return expectedType.equals(((CastOperandStack)item).getFromType());
                }
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("a(" + aClass + ")");
            }
        };
    }
}
