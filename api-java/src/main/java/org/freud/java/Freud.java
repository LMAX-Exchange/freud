package org.freud.java;

import org.hamcrest.Matcher;

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
}
