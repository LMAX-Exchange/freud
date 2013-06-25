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

import org.jmock.Mockery
import org.jmock.integration.junit4.JMock
import org.junit.runner.RunWith
import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.classobject.ClassObjectDsl.classOf
import static org.freud.analysed.classobject.ClassObjectDsl.hasDeclaredFieldOfType
import static org.freud.analysed.classobject.ClassObjectDsl.hasDeclaredMethod
import static org.freud.groovy.Freud.analyse

class ClassObjectExamplesSpock extends Specification {


    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) {
            hasDeclaredMethod(it, 'equals', Object) && hasDeclaredMethod(it, 'hashCode') ||
                    (!hasDeclaredMethod(it, 'equals', Object) && !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << classOf(['examples.classobject.ClassWithEqualsAndHashCode',
                                    'examples.classobject.EmptyClass'])
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'equals always goes together with hash code - failing test'() {
    expect:
        analyse(analysed) {
            hasDeclaredMethod(it, 'equals', Object) && hasDeclaredMethod(it, 'hashCode') ||
                    (!hasDeclaredMethod(it, 'equals', Object) && !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << classOf(['examples.classobject.ClassWithEqualsButNoHashCode'])
    }

    def 'test class with JMock Mockery object must contain the right RunWith annotation'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !hasDeclaredFieldOfType(cls, Mockery) || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << classOf(
                ['examples.classobject.EmptyClass',
                        'examples.classobject.ClassThatHasOtherRunWith',
                        'examples.classobject.ClassThatHasRunWithJMock',
                ])
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'test class with JMock Mockery object must contain the right RunWith annotation - failing test1'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !hasDeclaredFieldOfType(cls, Mockery) || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << classOf(['examples.classobject.ClassWithMockeryFieldButNoRunWith'])
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'test class with JMock Mockery object must contain the right RunWith annotation - failing test2'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !hasDeclaredFieldOfType(cls, Mockery) || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << classOf(['examples.classobject.ClassWithMockeryFieldButNoRunWithJMock'])
    }

    def 'all implementors of Comparator must not contain fields'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << classOf(['examples.classobject.EmptyClass', 'examples.classobject.StatelessComparator'])
    }


    @FailsWith(ConditionNotSatisfiedError)
    def 'all implementors of Comparator must not contain fields - failing test'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << classOf(['examples.classobject.StatefulComparator'])
    }
}
