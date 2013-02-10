package org.freud.groovy

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

}
