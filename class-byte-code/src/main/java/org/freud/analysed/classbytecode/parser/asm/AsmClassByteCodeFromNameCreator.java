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

package org.freud.analysed.classbytecode.parser.asm;

import org.freud.analysed.classbytecode.ClassByteCode;
import org.freud.core.Creator;

import java.io.File;
import java.io.InputStream;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class AsmClassByteCodeFromNameCreator implements Creator<String, ClassByteCode> {

    private final AsmClassByteCodeFromStreamCreator fromStreamCreator;
    private final ClassLoader classLoader;

    public AsmClassByteCodeFromNameCreator() {
        this(getSystemClassLoader());
    }

    public AsmClassByteCodeFromNameCreator(final ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.fromStreamCreator = new AsmClassByteCodeFromStreamCreator();
    }

    @Override
    public ClassByteCode create(final String source) {
        String fileName = toFileName(source);
        final InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return fromStreamCreator.create(inputStream);
    }

    private String toFileName(final String className) {
        return className.replace('.', File.separatorChar) + ".class";
    }
}
