package org.langera.freud.core;

public class FreudException extends RuntimeException
{
    public FreudException(final String message)
    {
        super(message);
    }

    public FreudException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
