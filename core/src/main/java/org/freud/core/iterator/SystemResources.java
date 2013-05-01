package org.freud.core.iterator;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class SystemResources implements Iterable<File> {

    private final Deque<File> files = new LinkedList<File>();


    public SystemResources(final Collection<String> paths) {
        this(paths, getSystemClassLoader());
    }

    public SystemResources(final Collection<String> paths, final ClassLoader classLoader) {
        for (String path : paths) {
            this.files.add(toFile(classLoader, path));
        }
    }

    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }

    static File toFile(final ClassLoader classLoader, final String path) {
        final URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Failed to find resource " + path);
        }
        return new File(resource.getFile());
    }
}
