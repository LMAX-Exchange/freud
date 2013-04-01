package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.properties.PropertiesDsl.propertyOf
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class PropertiesExamplesSpock extends Specification {

    static URL root = ClassLoader.getSystemResource('PropertiesExamples/')

    def 'properties that contain max or min in their name must be a number'() {
    expect:
        analyse(analysed) { (!it.key.contains('min') && !it.key.contains('max')) || it.value.number }
    where:
        analysed << forEach(propertyOf([new URL(root, 'ok.properties').text], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'properties that contain max or min in their name must be a number - failing test'() {
    expect:
        analyse(analysed) { (!it.key.contains('min') && !it.key.contains('max')) || it.value.number }
    where:
        analysed << forEach(propertyOf([new URL(root, 'badMinSize.properties').text], String))
    }

    def 'properties file should not contain passwords'() {
    expect:
        analyse(analysed) { !it.key.contains('password') }
    where:
        analysed << forEach(propertyOf([new URL(root, 'ok.properties').text], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'properties file should not contain passwords - failing test'() {
    expect:
        analyse(analysed) { !it.key.contains('password') }
    where:
        analysed << forEach(propertyOf([new URL(root, 'containsPassword.properties').text], String))
    }
}
