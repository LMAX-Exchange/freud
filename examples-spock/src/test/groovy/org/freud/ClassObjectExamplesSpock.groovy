package org.freud

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.classobject.ClassObjectDsl.classOf
import static org.freud.analysed.classobject.ClassObjectDsl.hasDeclaredMethod
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.classNamesIn
import static org.freud.groovy.Freud.forEach

class ClassObjectExamplesSpock extends Specification {


    static File classExamples = new File('examples-resources/src/main/java')

    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                            (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(classNamesIn(classExamples)),
                        { it.name.equals('examples.classobject.ClassWithEqualsButNoHashCode') })
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'equals always goes together with hash code - failing test'() {
    expect:
        analyse(analysed) { hasDeclaredMethod(it, 'equals', Object) &&  hasDeclaredMethod(it, 'hashCode') ||
                (!hasDeclaredMethod(it, 'equals', Object) &&  !hasDeclaredMethod(it, 'hashCode'))
        }
    where:
        analysed << forEach(classOf(classNamesIn(classExamples)),
                { !it.name.equals('examples.classobject.ClassWithEqualsButNoHashCode') })
    }

    def 'test class with JMOCK Mockery object must contain the right RunWith annotation'() {
//        return Freud.iterateOver(Class.class).
//                forEach(classSimpleName().matches(".+Test")).
//                assertThat(no(hasDeclaredFieldOfType(Mockery.class)).
//                        or(classAnnotation(RunWith.class, JMock.class))).in(iterator);
    }

    def 'all implementors of Comparator must not contain fields'() {
//        return Freud.iterateOver(Class.class).
//                assertThat(no(subTypeOf(Comparator.class)).or(no(withFields()))).in(iterator);
    }


}
