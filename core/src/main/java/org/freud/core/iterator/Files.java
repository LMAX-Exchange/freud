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

package org.freud.core.iterator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public final class Files implements Iterable<File> {

    private boolean recursive;
    private final FilenameFilter filenameFilter;
    private final Deque<File> files = new LinkedList<File>();


    public Files(final File root, final boolean recursive, final FilenameFilter filter) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        this.files.add(root);
    }

    public Files(final String rootPath, final boolean recursive, final FilenameFilter filter) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        this.files.add(toFile(rootPath));
    }

    public Files(final Collection filesOrPaths, final boolean recursive, final FilenameFilter filter) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        for (Object fileOrPath : filesOrPaths) {
            this.files.add(toFile(fileOrPath));
        }
    }

    @Override
    public Iterator<File> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<File> {
        private File[] currentFiles;
        private int filesPtr;
        private File nextFile;

        @Override
        public boolean hasNext() {
            return (nextFile != null || ((nextFile = nextFile()) != null));
        }

        @Override
        public File next() {
            if (nextFile != null) {
                return returnNextFile();
            }
            nextFile = nextFile();
            if (nextFile != null) {
                return returnNextFile();
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        private File returnNextFile() {
            File toReturn = nextFile;
            nextFile = null;
            return toReturn;
        }

        private File nextFile() {
            File next = nextFileFromCurrentDir();
            if (next != null) {
                return next;
            }
            while (!files.isEmpty()) {
                final File file = files.removeFirst();
                if (file.isDirectory()) {
                    currentFiles = file.listFiles();
                    filesPtr = 0;
                }
                next = nextFileFromCurrentDir();
                if (next != null) {
                    return next;
                }
            }
            return null;
        }

        private File nextFileFromCurrentDir() {
            while (currentFiles != null && filesPtr < currentFiles.length) {
                File file = currentFiles[filesPtr++];
                if (file.isDirectory()) {
                    if (recursive) {
                        files.addFirst(file);
                    }
                }
                else if (filenameFilter == null || filenameFilter.accept(file.getParentFile(), file.getName())) {
                    return file;
                }
            }
            return null;
        }
    }


    static File toFile(final Object fileOrPath) {
        if (fileOrPath instanceof File) {
            return (File) fileOrPath;
        }
        else if (fileOrPath instanceof String) {
            return new File((String) fileOrPath);
        }
        throw new UnsupportedOperationException("Cannot convert [" + fileOrPath + "] to File");
    }
}
