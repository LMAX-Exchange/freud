package org.freud.java;

import org.freud.core.Filter;
import org.freud.core.FreudSource;
import org.freud.core.iterator.Files;
import org.freud.core.iterator.FilteredAnalysedObjects;
import org.hamcrest.Matcher;

import java.io.File;
import java.io.FilenameFilter;

import static java.util.Arrays.asList;

public final class Freud {

    static <A> boolean analyse(A analysedObject, Matcher assertion) {
        return assertion.matches(analysedObject);
//        int numberOfParameters = assertion.maximumNumberOfParameters
//        int ptr = BREADCRUMBS.size()
//        if (numberOfParameters > ptr + 1) {
//            throw new IllegalArgumentException("Assertion has $numberOfParameters parameters, where only $ptr available")
//        }
//        for (int i = 1; i < numberOfParameters; i++) {
//            curriedAssertion = curriedAssertion.curry(BREADCRUMBS.get(--ptr))
//        }
//        return curriedAssertion.call();
    }

    public static <A> Iterable<A> forEach(Iterable<A> analysedObjects, Matcher<A> matcher) {
        return new FilteredAnalysedObjects<A>(analysedObjects, new ToFilter<A>(matcher));
    }

    public static FreudSource<File> filesIn(String... paths) {
        return new FreudSource<File>(new Files(asList(paths), true, null), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(String... paths) {
        return new FreudSource<File>(new Files(asList(paths), false, null), File.class);
    }

    public static FreudSource<File> filesIn(FilenameFilter filter, String... paths) {
        return new FreudSource<File>(new Files(asList(paths), true, filter), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(FilenameFilter filter, String... paths) {
        return new FreudSource<File>(new Files(asList(paths), false, filter), File.class);
    }

    public static FreudSource<File> filesIn(File... files) {
        return new FreudSource<File>(new Files(asList(files), true, null), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(File... files) {
        return new FreudSource<File>(new Files(asList(files), false, null), File.class);
    }

    public static FreudSource<File> filesIn(FilenameFilter filter, File... files) {
        return new FreudSource<File>(new Files(asList(files), true, filter), File.class);
    }

    public static FreudSource<File> nonRecursivelyFilesIn(FilenameFilter filter, File... files) {
        return new FreudSource<File>(new Files(asList(files), false, filter), File.class);
    }

    private static class ToFilter<A> implements Filter<A> {

        private final Matcher<A> matcher;

        private ToFilter(final Matcher<A> matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean filter(final A analysedObject) {
            return !matcher.matches(analysedObject);
        }
    }

}
