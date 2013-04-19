package org.freud.analysed.classbytecode.parser

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor

class DebugVisitor implements ClassVisitor, AnnotationVisitor, FieldVisitor, MethodVisitor {

    @Delegate ClassVisitor classVisitor = impl(ClassVisitor) as ClassVisitor
    @Delegate AnnotationVisitor annotationVisitor = impl(AnnotationVisitor) as AnnotationVisitor
    @Delegate FieldVisitor fieldVisitor = impl(FieldVisitor) as FieldVisitor
    @Delegate MethodVisitor methodVisitor = impl(MethodVisitor) as MethodVisitor


    private Map impl(Class clazz) {
        clazz.methods.collectEntries {
            method ->
            [method.name, { Object[] args ->
                println "$method.name ${Arrays.toString(args)}"
                return (method.returnType != Void) ? this : null
            }]
        }
    }
}
