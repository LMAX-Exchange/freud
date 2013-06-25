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

package org.freud.analysed.classobject;

import org.freud.core.Creator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ClassFromFileCreator implements Creator<File, Class> {

    private final List<String> rootDirList = new ArrayList<String>();
    private final ClassFromNameCreator fromNameCreator;

    public ClassFromFileCreator(final ClassFromNameCreator fromNameCreator, final File... rootDirs) {
        try {
            for (File dir : rootDirs) {
                this.rootDirList.add(dir.getCanonicalPath() + File.separatorChar);
            }
            this.fromNameCreator = fromNameCreator;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Cannot construct creator " + Arrays.toString(rootDirs) + " must all exist", e);
        }
    }

    @Override
    public Class create(final File source) {
        try {
            String name = source.getCanonicalPath();
            for (String rootDir : rootDirList) {
                if (name.startsWith(rootDir)) {
                    return fromNameCreator.create(convertFileNameToClassName(name, rootDir));
                }
            }
            throw new IllegalArgumentException("Cannot create class object from " + source + " - not under any dir " + rootDirList);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Cannot create class object from " + source, e);
        }
    }

    private String convertFileNameToClassName(String name, final String rootDir) {
        name = name.substring(rootDir.length());
        name = name.substring(0, name.lastIndexOf('.'));
        name = name.replace('/', '.');
        name = name.replace('\\', '.');
        return name;
    }
}
