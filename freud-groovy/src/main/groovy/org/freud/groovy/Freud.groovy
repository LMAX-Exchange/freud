package org.freud.groovy

class Freud {

    static <A> boolean analyse(A analysedObject, Closure<Boolean> assertion) {
        return assertion.call(analysedObject);
    }

}
