package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.text.TextDsl.textLineWithin
import static org.freud.analysed.text.TextDsl.textOf
import static org.freud.groovy.Freud.analyse

class TextExamplesSpock extends Specification {

    static URL root = ClassLoader.getSystemResource('TextExamples/')

    def 'line length does not exceed 80'() {
    expect:
        analyse(analysed) { it.line.length() < 80 }
    where:
        analysed << textLineWithin(textOf([new URL(root, 'textFile').text]))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'line length does not exceed 80 - failing test'() {
    expect:
        analyse(analysed) { it.line.length() < 80 }
    where:
        analysed << textLineWithin(textOf([new URL(root, 'textFileWithLongLine').text]))
    }
}
