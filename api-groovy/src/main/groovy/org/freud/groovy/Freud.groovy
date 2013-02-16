package org.freud.groovy

import org.freud.core.Filter
import org.freud.core.FreudSource
import org.freud.core.iterator.FileIterator
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
        return curriedAssertion.call();
    }

    static <A> Iterable<A> forEach(Iterable<A> analysedObjects, Closure<Boolean> filter = { false }) {
        new FilteredAnalysedObjects<A>(analysedObjects, filter as Filter)
    }

    static FreudSource<File> filesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new FileIterator(filesOrPaths, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> nonRecursivelyFilesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { false }) {
        new FreudSource<File>(new FileIterator(filesOrPaths, false, toFilenameFilter(filenameFilter)), File)
    }

    private static FilenameFilter toFilenameFilter(Closure<Boolean> suppliedClosure) {

        switch (suppliedClosure.maximumNumberOfParameters) {
            case 0:
                return { parentFile, name -> suppliedClosure.call() } as FilenameFilter
            case 1:
                return { parentFile, name -> suppliedClosure.call(name) } as FilenameFilter
            default:
                return suppliedClosure as FilenameFilter
        }
    }

}
