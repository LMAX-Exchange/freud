package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.classobject.ClassObjectDsl.classOf
import static org.freud.analysed.classobject.ClassObjectDsl.hasDeclaredMethod
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach
import static org.freud.groovy.Freud.sourcesIn

class ClassObjectExamplesSpock extends Specification {


    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                            (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(sourcesIn(['examples.classobject.ClassWithEqualsAndHashCode',
                                               'examples.classobject.EmptyClass'], String)))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'equals always goes together with hash code - failing test'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(sourcesIn(['examples.classobject.ClassWithEqualsButNoHashCode'], String)))
    }

    def 'test class with JMOCK Mockery object must contain the right RunWith annotation'() {
//        return Freud.iterateOver(Class.class).
//                forEach(classSimpleName().matches(".+Test")).
//                assertThat(no(hasDeclaredFieldOfType(Mockery.class)).
//                        or(classAnnotation(RunWith.class, JMock.class))).in(iterator);
    }

    def 'all implementors of Comparator must not contain fields'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << forEach(classOf(sourcesIn(['examples.classobject.EmptyClass', 'examples.classobject.StatelessComparator'], String)))
    }


    @FailsWith(ConditionNotSatisfiedError)
    def 'all implementors of Comparator must not contain fields - failing test'() {
    expect:
        analyse(analysed) { Class cls ->
            !Comparator.isAssignableFrom(cls) || cls.declaredFields.length == 0
        }
    where:
        analysed << forEach(classOf(sourcesIn(['examples.classobject.StatefulComparator'], String)))
    }
}
