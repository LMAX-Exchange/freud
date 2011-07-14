package org.langera.freud.optional.classfile.method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.matcher.FreudMatcher;

import java.util.Arrays;
import java.util.List;

import static org.langera.freud.optional.classfile.method.AbstractInstructionVisitor.typeEncoding;

public final class MethodDsl
{

    private MethodDsl()
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
                }, Opcode.INVOKEVIRTUAL, Opcode.INVOKEDYNAMIC, Opcode.INVOKEINTERFACE, Opcode.INVOKESTATIC, Opcode.INVOKESPECIAL);
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
            (final Class expectedOwner, final String expectedMethodName, final Matcher<LocalVariable>... expectedParamsPassed)
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
                            int paramIndex = expectedParamsPassed.length - 1;
                            List<Instruction> instructionList = item.getInstructionList();
                            for (int i = instruction.getIndex() - 1; i >= 0 && paramIndex >= 0; i--)
                            {
                                final Instruction prevInstruction = instructionList.get(i);
                                if (prevInstruction.getOpcode() == Opcode.ALOAD)
                                {
                                    LocalVariable var = item.getLocalVariable(prevInstruction.getVarIndex());
                                    if (var == null || !expectedParamsPassed[paramIndex].matches(var))
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        paramIndex--;
                                    }
                                }
                            }
                            found[0] = paramIndex < 0;
                        }

                    }
                }, Opcode.INVOKEVIRTUAL, Opcode.INVOKEDYNAMIC, Opcode.INVOKEINTERFACE, Opcode.INVOKESTATIC, Opcode.INVOKESPECIAL);
                return found[0];
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("methodInvokedWithParams(" + expectedOwner.getName() + ", " + expectedMethodName + ")");
            }
        };
    }

    public static FreudMatcher<LocalVariable> variableOfType(final Class<?> aClass)
    {
        return new FreudMatcher<LocalVariable>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final LocalVariable item)
            {
                return expectedType.equals(item.getDesc());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("variableOfType(" + aClass + ")");
            }
        };
    }
}
