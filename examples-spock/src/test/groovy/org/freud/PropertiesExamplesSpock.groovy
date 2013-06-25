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

import static org.freud.analysed.properties.PropertiesDsl.propertyOf
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class PropertiesExamplesSpock extends Specification {

    static URL root = ClassLoader.getSystemResource('PropertiesExamples/')

    def 'properties that contain max or min in their name must be a number'() {
    expect:
        analyse(analysed) { it.value.number }
    where:
        analysed << forEach(propertyOf([new URL(root, 'ok.properties').text]), { it.key.contains('min') || it.key.contains('max') })
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'properties that contain max or min in their name must be a number - failing test'() {
    expect:
        analyse(analysed) { it.value.number }
    where:
        analysed << forEach(propertyOf([new URL(root, 'badMinSize.properties').text]), { it.key.contains('min') || it.key.contains('max') })
    }

    def 'properties file should not contain passwords'() {
    expect:
        analyse(analysed) { !it.key.contains('password') }
    where:
        analysed << propertyOf([new URL(root, 'ok.properties').text])
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'properties file should not contain passwords - failing test'() {
    expect:
        analyse(analysed) { !it.key.contains('password') }
    where:
        analysed << propertyOf([new URL(root, 'containsPassword.properties').text])
    }
}
