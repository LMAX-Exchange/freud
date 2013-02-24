package org.freud

import spock.lang.Specification

import static org.freud.analysed.classobject.ClassObjectDsl.classOf
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.classNamesIn
import static org.freud.groovy.Freud.forEach
import static org.freud.groovy.Freud.has

class ClassObjectExamplesSpock extends Specification {


    static File classExamples = new File('examples-resources/src/main/java')

    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) { has {it.getDeclaredMethod('equals', Object)} &&  has {it.getDeclaredMethod('hashCode')} ||
                            (!has {it.getDeclaredMethod('equals', Object)} &&  !has {it.getDeclaredMethod('hashCode')})
        }
    where:
        analysed << forEach(classOf(classNamesIn(classExamples)))
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
