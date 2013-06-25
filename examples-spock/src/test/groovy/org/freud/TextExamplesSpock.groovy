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
