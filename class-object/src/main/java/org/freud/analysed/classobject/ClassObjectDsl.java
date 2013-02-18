package org.freud.analysed.classobject;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;

import java.io.File;

public final class ClassObjectDsl {

    private ClassObjectDsl() {
        // static utility
    }

    public static Iterable<Class> classOf(FreudSource<String> source) {
        return new AnalysedObjects<String, Class>(source.getSources(), new ClassFromNameCreator());
    }

    public static Iterable<Class> classOf(FreudSource<File> source, File... rootDirs) {
        return new AnalysedObjects<File, Class>(source.getSources(), new ClassFromFileCreator(new ClassFromNameCreator(), rootDirs));
    }


//    public static Iterable<ClassLine> textLineWithin(Iterable<Class> texts) {
//        return new SubTypeAnalysedObjects<Class, ClassLine>(new ClassToLinesCreator(), texts);
//    }

}
