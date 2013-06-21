package org.freud.analysed.classobject;

import org.freud.core.Creator;

import static java.lang.ClassLoader.getSystemClassLoader;

public final class ClassFromNameCreator implements Creator<String, Class> {

    private final ClassLoader classLoader;

    public ClassFromNameCreator() {
        this(getSystemClassLoader());
    }

    public ClassFromNameCreator(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Class create(final String source) {
        try {
            return classLoader.loadClass(source);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to create class object " + source, e);
        }
    }
}
