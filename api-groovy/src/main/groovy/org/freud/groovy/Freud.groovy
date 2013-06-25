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



package org.freud.groovy

import org.freud.core.Filter
import org.freud.core.FreudSource
import org.freud.core.iterator.ClassNames
import org.freud.core.iterator.Files
import org.freud.core.iterator.FilteredAnalysedObjects
import org.freud.core.iterator.SystemResources

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

    static <A> Iterable<A> forEach(Iterable<A> analysedObjects, Closure<Boolean> filter = { true }) {
        new FilteredAnalysedObjects<A>(analysedObjects, filter as Filter)
    }

    static FreudSource<File> filesIn(File root, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<File>(new Files(root, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(String path, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<File>(new Files(path, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<File>(new Files(filesOrPaths, true, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<File> filesIn(Collection filesOrPaths, boolean recursive, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<File>(new Files(filesOrPaths, recursive, toFilenameFilter(filenameFilter)), File)
    }

    static FreudSource<URL> resourcesOf(Collection<String> paths) {
        new FreudSource<URL>(new SystemResources(paths), URL)
    }

    static FreudSource<URL> resourcesOf(Collection<String> paths, ClassLoader classLoader) {
        new FreudSource<URL>(new SystemResources(paths, classLoader), URL)
    }

    static FreudSource<String> classNamesIn(File root, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<String>(new ClassNames(root, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(String path, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<String>(new ClassNames(path, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(Collection filesOrPaths, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<String>(new ClassNames(filesOrPaths, true, toFilenameFilter(filenameFilter)), String)
    }

    static FreudSource<String> classNamesIn(Collection filesOrPaths, boolean recursive, Closure<Boolean> filenameFilter = { true }) {
        new FreudSource<String>(new ClassNames(filesOrPaths, recursive, toFilenameFilter(filenameFilter)), String)
    }

    private static FilenameFilter toFilenameFilter(Closure<Boolean> suppliedClosure) {

        switch (suppliedClosure.maximumNumberOfParameters) {
            case 0:
                return { parentFile, name -> suppliedClosure.call() } as FilenameFilter
            case 1:
                return { parentFile, name -> suppliedClosure.call(name) } as FilenameFilter
            case 2:
                return { parentFile, name -> suppliedClosure.call(parentFile, name) } as FilenameFilter
            default:
                throw new IllegalArgumentException('Filter has too many parameters')
        }
    }

}
