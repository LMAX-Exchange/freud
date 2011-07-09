package org.langera.freud.optional.classfile;

public interface TryCatchBlockDef
{
    boolean finallyBlock();

    int getStartPtr();

    int getEndPtr();

    int getHandlerPtr();

    String getExceptionType();
}
