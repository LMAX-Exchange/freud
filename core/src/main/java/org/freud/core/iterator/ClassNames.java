package org.freud.core.iterator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.freud.core.iterator.Files.toFile;

public final class ClassNames implements Iterable<String> {

    private boolean recursive;
    private final FilenameFilter filenameFilter;
    private final Deque<File> roots = new LinkedList<File>();


    public ClassNames(final Collection filesOrPaths, final boolean recursive, final FilenameFilter filter) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        for (Object fileOrPath : filesOrPaths) {
            this.roots.add(toFile(fileOrPath));
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new InternalIterator();
    }

    private final class InternalIterator implements Iterator<String> {
        private File currentRoot;
        private Iterator<File> currentFilesIterator;

        @Override
        public boolean hasNext() {
            if (currentFilesIterator == null || !currentFilesIterator.hasNext()) {
                if (!roots.isEmpty()) {
                    currentRoot = roots.removeFirst();
                    currentFilesIterator = new Files(currentRoot, recursive, filenameFilter).iterator();
                    if (!currentFilesIterator.hasNext()) {
                        currentFilesIterator = null;
                        return hasNext();
                    }
                    return true;
                }
                else {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String next() {
            try {
                if (currentFilesIterator == null || !currentFilesIterator.hasNext()) {
                    if (!roots.isEmpty()) {
                        currentRoot = roots.removeFirst();
                        currentFilesIterator = new Files(currentRoot, recursive, filenameFilter).iterator();
                        if (!currentFilesIterator.hasNext()) {
                            currentFilesIterator = null;
                            return next();
                        }
                        return toClassName(currentFilesIterator.next());
                    }
                    else {
                        throw new NoSuchElementException();
                    }
                }
                return toClassName(currentFilesIterator.next());
            }
            catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private String toClassName(final File file) throws IOException {
            String path = file.getCanonicalPath().substring(currentRoot.getCanonicalPath().length() + 1);
            final String name = file.getName();
            int index = name.lastIndexOf('.');
            if (index > 0) {
                path = path.substring(0, path.length() - name.length() + index);
            }
            return path.replace(File.separatorChar, '.');
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
