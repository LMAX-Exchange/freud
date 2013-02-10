package org.freud.core.iterator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static java.util.Collections.addAll;

public final class FileIterator implements Iterable<File>, Iterator<File> {

    private boolean recursive;
    private FilenameFilter filenameFilter;
    private final Deque<File> paths = new LinkedList<File>();
    private File[] currentFiles;
    private int filesPtr;
    private File nextFile;

    public FileIterator(final boolean recursive, final String... paths) {
        this.recursive = recursive;
        for (String path : paths) {
            this.paths.add(new File(path));
        }
    }


    public FileIterator(final boolean recursive, final File... paths) {
        this.recursive = recursive;
        addAll(this.paths, paths);
    }

    public FileIterator(final boolean recursive, final FilenameFilter filter, final String... paths) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        for (String path : paths) {
            this.paths.add(new File(path));
        }
    }


    public FileIterator(final boolean recursive, final FilenameFilter filter, final File... paths) {
        this.recursive = recursive;
        this.filenameFilter = filter;
        addAll(this.paths, paths);
    }

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
        while (!paths.isEmpty()) {
            final File file = paths.removeFirst();
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
                    paths.addFirst(file);
                }
            }
            else if (filenameFilter == null || filenameFilter.accept(file.getParentFile(), file.getName())) {
                return file;
            }
        }
        return null;
    }


    @Override
    public Iterator<File> iterator() {
        return this;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
