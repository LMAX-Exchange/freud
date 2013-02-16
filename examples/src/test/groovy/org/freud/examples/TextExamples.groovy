package org.freud.examples

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.text.TextDsl.textLineWithin
import static org.freud.analysed.text.TextDsl.textOf
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.filesIn
import static org.freud.groovy.Freud.forEach

class TextExamples extends Specification {

    static File root = ClassLoader.getSystemResource('TextExamples').file as File


    def 'line length does not exceed 80'() {
    expect:
        analyse(analysed) { it.line.length() < 80 }
    where:
        analysed << forEach(textLineWithin(forEach(textOf(filesIn([root], { !it.contains('Long')})))))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'line length does not exceed 80 - failing test'() {
    expect:
        analyse(analysed) { it.line.length() < 80 }
    where:
        analysed << forEach(textLineWithin(forEach(textOf(filesIn([root], { it.contains('Long')})))))
    }
}
