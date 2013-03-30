package org.freud

import org.jmock.Mockery
import org.jmock.integration.junit4.JMock
import org.junit.runner.RunWith
import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.classobject.ClassObjectDsl.classOf
import static org.freud.analysed.classobject.ClassObjectDsl.hasDeclaredMethod
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach

class ClassObjectExamplesSpock extends Specification {


    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                            (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(['examples.classobject.ClassWithEqualsAndHashCode',
                                               'examples.classobject.EmptyClass'], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'equals always goes together with hash code - failing test'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(['examples.classobject.ClassWithEqualsButNoHashCode'], String))
    }

    def 'test class with JMock Mockery object must contain the right RunWith annotation'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !cls.declaredFields.any { it.class == Mockery.class } || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << forEach(classOf(
                ['examples.classobject.EmptyClass',
                       'examples.classobject.ClassThatHasOtherRunWith',
                       'examples.classobject.ClassThatHasRunWithJMock',
                ], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'test class with JMock Mockery object must contain the right RunWith annotation - failing test1'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !cls.declaredFields.any { it.type == Mockery.class } || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << forEach(classOf(
                ['examples.classobject.ClassWithMockeryFieldButNoRunWith'], String))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'test class with JMock Mockery object must contain the right RunWith annotation - failing test2'() {
    expect:
        analyse(analysed) { Class cls ->
            RunWith runWith = (RunWith) cls.getAnnotation(RunWith)
            !cls.declaredFields.any { it.type == Mockery.class } || (runWith != null && runWith.value() == JMock.class)
        }
    where:
        analysed << forEach(classOf(
                ['examples.classobject.ClassWithMockeryFieldButNoRunWithJMock'], String))
    }

    def 'all implementors of Comparator must not contain fields'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << forEach(classOf(['examples.classobject.EmptyClass', 'examples.classobject.StatelessComparator'], String))
    }


    @FailsWith(ConditionNotSatisfiedError)
    def 'all implementors of Comparator must not contain fields - failing test'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << forEach(classOf(['examples.classobject.StatefulComparator'], String))
    }
}
