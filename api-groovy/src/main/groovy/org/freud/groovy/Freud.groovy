package org.freud.groovy

import org.freud.core.Filter
import org.freud.core.FreudSource
import org.freud.core.iterator.ClassNames
import org.freud.core.iterator.Files
import org.freud.core.iterator.FilteredAnalysedObjects

import static org.freud.core.iterator.AnalysedObjectBreadcrumbs.BREADCRUMBS

class Freud {

    static <A> boolean analyse(A analysedObject, Closure<Boolean> assertion) {
        Closure<Boolean> curriedAssertion = assertion.curry(analysedObject)
        int numberOfParameters = assertion.maximumNumberOfParameters
        int ptr = BREADCRUMBS.size()
        if (numberOfParameters > ptr + 1) {
            throw new IllegalArgumentException("Assertion has $numberOfParameters parameters, where only $ptr available")
        }
        for (int i = 1; i < numberOfParameters; i++) {
            curriedAssertion = curriedAssertion.curry(BREADCRUMBS.get(--ptr))
        }

        new File('/tmp/freud.log').withWriterAppend {
            it.println(analysedObject)
            it.println(BREADCRUMBS);
        }

        return curriedAssertion.call();
    }

    static <A> Iterable<A> forEach(Iterable<A> analysedObjects, Closure<Boolean> filter = { false }) {
        new FilteredAnalysedObjects<A>(analysedObjects, filter as Filter)
    }

    static <T> FreudSource<T> sourcesIn(Iterable<T> iterable, Class<T> type) {
        new FreudSource<T>(iterable, type)
    }


    static FreudSource<File> filesIn(File root, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new Files(root, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(String path, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new Files(path, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new Files(filesOrPaths, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(Collection filesOrPaths, boolean recursive, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new Files(filesOrPaths, recursive, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<String> classNamesIn(File root, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<String>(new ClassNames(root, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(String path, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<String>(new ClassNames(path, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<String>(new ClassNames(filesOrPaths, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(Collection filesOrPaths, boolean recursive, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<String>(new ClassNames(filesOrPaths, recursive, toFilenameFilter(filenameFilter)), String)
    }

    private static FilenameFilter toFilenameFilter(Closure<Boolean> suppliedClosure) {

        switch (suppliedClosure.maximumNumberOfParameters) {
            case 0:
                return { parentFile, name -> !suppliedClosure.call() } as FilenameFilter
            case 1:
                return { parentFile, name -> !suppliedClosure.call(name) } as FilenameFilter
            case 2:
                return { parentFile, name -> !suppliedClosure.call(parentFile, name) } as FilenameFilter
            default:
                throw new IllegalArgumentException('Filter has too many parameters')
        }
    }

}
