package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCodeAnnotation;
import org.freud.analysed.classbytecode.ClassByteCodeElement;
import org.objectweb.asm.Opcodes;

import java.util.LinkedList;
import java.util.List;

abstract class AsmElement implements ClassByteCodeElement
{
    private final int access;
    private final List<ClassByteCodeAnnotation> annotationList;

    protected AsmElement(final int access)
    {
        this.access = access;
        this.annotationList = new LinkedList<ClassByteCodeAnnotation>();
    }

    @Override
    public boolean isPublic()
    {
        return isAccessModifier(Opcodes.ACC_PUBLIC);
    }

    @Override
    public boolean isPrivate()
    {
        return isAccessModifier(Opcodes.ACC_PRIVATE);
    }

    @Override
    public boolean isProtected()
    {
        return isAccessModifier(Opcodes.ACC_PROTECTED);
    }

    @Override
    public boolean isFinal()
    {
        return isAccessModifier(Opcodes.ACC_FINAL);
    }

    @Override
    public boolean isSynthetic()
    {
        return isAccessModifier(Opcodes.ACC_SYNTHETIC);
    }

    @Override
    public List<ClassByteCodeAnnotation> getAnnotationList()
    {
        return annotationList;
    }

    protected boolean isAccessModifier(final int mask)
    {
        return (access & mask) != 0;
    }

    protected void addAnnotation(AsmAnnotation annotation)
    {
        annotationList.add(annotation);
    }
}
