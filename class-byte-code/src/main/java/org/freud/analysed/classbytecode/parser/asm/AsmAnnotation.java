package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCodeAnnotation;
import org.objectweb.asm.AnnotationVisitor;

final class AsmAnnotation implements AnnotationVisitor, ClassByteCodeAnnotation
{
    private final int parameterIndex;
    private final String desc;
    private final boolean visible;
    private final boolean defaultAnnotation;
    private String name;
    private Object value;
    private String valueDesc;

    public AsmAnnotation(final AsmElement element)
    {
        this(element, -1, null, false, true);
    }

    public AsmAnnotation(final AsmElement element, final String desc, final boolean visible)
    {
        this(element, -1, desc, visible, false);
    }

    public AsmAnnotation(final AsmElement element, final int parameter, final String desc, final boolean visible)
    {
        this(element, parameter, desc, visible, false);
    }

    public AsmAnnotation(final AsmElement element, final int parameter, final String desc, final boolean visible, final boolean defaultAnnotation)
    {
        this.parameterIndex = parameter;
        this.desc = desc;
        this.visible = visible;
        this.defaultAnnotation = defaultAnnotation;
        element.addAnnotation(this);
    }

    @Override
    public int getParameterIndex()
    {
        return parameterIndex;
    }

    @Override
    public String getDesc()
    {
        return desc;
    }

    @Override
    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public boolean isDefaultAnnotation()
    {
        return defaultAnnotation;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Object getValue()
    {
        return value;
    }

    @Override
    public String getValueDesc()
    {
        return valueDesc;
    }

    @Override
    public void visit(final String name, final Object value)
    {
        visitInternal(name, value, null);
    }

    @Override
    public void visitEnum(final String name, final String desc, final String value)
    {
        visitInternal(name, value, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name, final String desc)
    {
        visitInternal(name, null, desc);
        return this;
    }

    @Override
    public AnnotationVisitor visitArray(final String name)
    {
        visitInternal(name, null, null);
        return this;
    }

    @Override
    public void visitEnd()
    {
    }

    private void visitInternal(final String name, final Object value, final String valueDesc)
    {
        if (name != null)
        {
            this.name = name;
        }
        if (value != null)
        {
            this.value = value;
        }
        if (valueDesc != null)
        {
            this.valueDesc = valueDesc;
        }
    }
}
