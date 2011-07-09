package org.langera.freud.optional.classfile;

import org.hamcrest.Description;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;

import java.util.Arrays;

import static org.langera.freud.optional.classfile.AbstractInstructionVisitor.typeEncoding;

public final class ClassFileDsl
{
    private ClassFileDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<ClassFile> classSimpleName()
    {
        return new RegexMatcherBuilder<ClassFile>(new RegexMatcherAdapter<ClassFile>()
        {
            @Override
            public String getStringToMatch(final ClassFile toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "ClassFileName";
            }
        });
    }

    public static FreudMatcher<ClassFile> hasMethodInvocation(final Class expectedOwner, final String expectedMethodName, final Class... expectedArgs)
    {
        return new FreudMatcher<ClassFile>()
        {
            private String expectedOwnerName;
            private String[] expectedArgNames;

            {
                expectedOwnerName = typeEncoding(expectedOwner);
                expectedArgNames = new String[expectedArgs.length];
                for (int i = 0, size = expectedArgs.length; i < size; i++)
                {
                    expectedArgNames[i] = typeEncoding(expectedArgs[i]);

                }
            }

            @Override
            protected boolean matchesSafely(final ClassFile item)
            {
                final boolean[] found = new boolean[1];
                for (ClassFileMethod method : item.getMethodList())
                {
                    method.findInstruction(new AbstractInstructionVisitor()
                        {
                            @Override
                            public void methodInvocation(final Opcode opcode, final int lineNumber, final String owner, final String methodName, final String... args)
                            {
                                if (expectedOwnerName.equals(owner) &&
                                        expectedMethodName.equals(methodName) &&
                                        Arrays.equals(expectedArgNames, args))
                                {
                                    found[0] = true;
                                }

                            }
                        }, Opcode.INVOKEVIRTUAL, Opcode.INVOKEDYNAMIC, Opcode.INVOKEINTERFACE, Opcode.INVOKESTATIC, Opcode.INVOKESPECIAL);
                    if (found[0])
                    {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("hasMethodInvocation(" + expectedOwner.getName() + ", " + expectedMethodName + ")");
            }
        };
    }
}
