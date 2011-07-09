package org.langera.freud.optional.classfile.parser.asm;


import org.langera.freud.optional.classfile.TryCatchBlockDef;
import org.objectweb.asm.Label;

final class AsmTryCatchBlockDef implements TryCatchBlockDef, AsmStatement
{
    private final String type;
    private final AsmMethod method;
    private Label start;
    private Label end;
    private Label handler;
    private int startPtr;
    private int endPtr;
    private int handlerPtr;

    AsmTryCatchBlockDef(final AsmMethod method, final String exceptionType,
                        final Label start, final Label end, final Label handler)
    {
        this.method = method;
        this.type = exceptionType;
        this.start = start;
        this.end = end;
        this.handler = handler;
    }

    @Override
    public void assignLabelPtrs()
    {
        startPtr = method.getPtrForLabel(start);
        endPtr = method.getPtrForLabel(end);
        handlerPtr = method.getPtrForLabel(handler);
        start = null;
        end = null;
        handler = null;
    }

    @Override
    public boolean finallyBlock()
    {
        return type == null;
    }

    @Override
    public String getExceptionType()
    {
        return type;
    }

    @Override
    public int getStartPtr()
    {
        return startPtr;
    }

    @Override
    public int getEndPtr()
    {
        return endPtr;
    }

    @Override
    public int getHandlerPtr()
    {
        return handlerPtr;
    }
}
