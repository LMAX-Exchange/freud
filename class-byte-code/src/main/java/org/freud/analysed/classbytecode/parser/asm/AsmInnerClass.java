package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCodeField;
import org.freud.analysed.classbytecode.ClassByteCodeInnerClass;
import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;
import org.objectweb.asm.Opcodes;

import java.util.List;

final class AsmInnerClass extends AsmElement implements ClassByteCodeInnerClass
{
    private final AsmClassByteCode classByteCode;
    private final String innerName;

    AsmInnerClass(final AsmClassByteCode classByteCode, final String innerName, final int access)
    {
        super(access);
        this.classByteCode = classByteCode;
        this.innerName = innerName;
    }

    @Override
    public boolean isAnonymous()
    {
        return innerName == null;
    }

    @Override
    public boolean isStatic()
    {
        return isAccessModifier(Opcodes.ACC_STATIC);
    }

    @Override
    public String getOuterName()
    {
        return classByteCode.getOuterName();
    }

    @Override
    public String getOuterDesc()
    {
        return classByteCode.getOuterDesc();
    }

    @Override
    public List<ClassByteCodeInnerClass> getInnerClassList()
    {
        return classByteCode.getInnerClassList();
    }

    @Override
    public List<ClassByteCodeField> getFieldList()
    {
        return classByteCode.getFieldList();
    }

    @Override
    public List<ClassByteCodeMethod> getMethodList()
    {
        return classByteCode.getMethodList();
    }

    @Override
    public String getSuperName()
    {
        return classByteCode.getSuperName();
    }

    @Override
    public String getSignature()
    {
        return classByteCode.getSignature();
    }

    @Override
    public String[] getInterfaces()
    {
        return classByteCode.getInterfaces();
    }

    @Override
    public String getName()
    {
        return classByteCode.getName();
    }

    @Override
    public boolean isSuper()
    {
        return classByteCode.isSuper();
    }

    @Override
    public boolean isAbstract()
    {
        return classByteCode.isAbstract();
    }

    @Override
    public boolean isAnnotation()
    {
        return classByteCode.isAnnotation();
    }

    @Override
    public boolean isEnum()
    {
        return classByteCode.isEnum();
    }

    @Override
    public boolean isInterface()
    {
        return classByteCode.isInterface();
    }

    @Override
    public int getVersion()
    {
        return classByteCode.getVersion();
    }
}
