/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



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
