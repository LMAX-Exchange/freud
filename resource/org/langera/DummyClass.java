package org.langera;

public final class DummyClass
{
    private Object anonymousDummyClass()
    {
        return new Object()
        {
            // dummy
        };
    }

    private static final class InnerDummyClass
    {
        private static final class InnerInnerDummyClass
        {
            // dummy
        }
    }

    private static @interface InnerDummyAnnotation {}
}
