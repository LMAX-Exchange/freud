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

package org.freud.analysed.classbytecode;

import org.freud.analysed.classbytecode.method.ClassByteCodeMethod;

import java.util.List;

public interface ClassByteCode extends ClassByteCodeElement {
    List<ClassByteCodeField> getFieldList();

    List<ClassByteCodeMethod> getMethodList();

    String getName();

    String[] getInterfaces();

    String getSignature();

    String getSuperName();

    boolean isInterface();

    boolean isEnum();

    boolean isAnnotation();

    boolean isAbstract();

    boolean isSuper();

    int getVersion();

    List<ClassByteCodeInnerClass> getInnerClassList();

}
