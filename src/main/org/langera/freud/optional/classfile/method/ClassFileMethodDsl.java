package org.langera.freud.optional.classfile.method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.optional.classfile.method.instruction.AbstractInstructionVisitor;
import org.langera.freud.optional.classfile.method.instruction.Instruction;
import org.langera.freud.optional.classfile.method.ref.CastLoadedReference;
import org.langera.freud.optional.classfile.method.ref.StaticLoadedReference;
import org.langera.freud.optional.classfile.method.ref.LoadedReference;

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
            (final Class expectedOwner, final String expectedMethodName, final Matcher<LoadedReference>... expectedParamsPassed)
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
                            int index = instruction.getInstructionIndex() - 1;
                            found[0] = true;
                            for (int i = expectedParamsPassed.length - 1; index >= 0 && i >= 0; i--)
                            {
                                Matcher<LoadedReference> matcher = expectedParamsPassed[i];
                                final Instruction prevInstruction = item.getInstruction(index);
                                final LoadedReference loadedReference = prevInstruction.getLoadedReference();
                                if (loadedReference == null || !matcher.matches(loadedReference))
                                {
                                    found[0] = false;
                                    break;
                                }
                                index = loadedReference.getInstructionIndex() - 1;
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

    public static FreudMatcher<LoadedReference> a(final Class<?> aClass)
    {
        return new FreudMatcher<LoadedReference>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final LoadedReference item)
            {
                return expectedType.equals(item.getTypeOfReference());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("a(" + aClass + ")");
            }
        };
    }

    public static FreudMatcher<LoadedReference> aConstantOf(final Class<?> aClass)
    {
        return new FreudMatcher<LoadedReference>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final LoadedReference item)
            {
                return item instanceof StaticLoadedReference && expectedType.equals(item.getTypeOfReference());
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("aConstantOf(" + aClass + ")");
            }
        };
    }

    public static FreudMatcher<LoadedReference> castOf(final Class<?> aClass)
    {
        return new FreudMatcher<LoadedReference>()
        {
            private final String expectedType = typeEncoding(aClass);

            @Override
            protected boolean matchesSafely(final LoadedReference item)
            {
                if (item instanceof CastLoadedReference)
                {
                    return expectedType.equals(((CastLoadedReference)item).getLoadedReference().getTypeOfReference());
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
