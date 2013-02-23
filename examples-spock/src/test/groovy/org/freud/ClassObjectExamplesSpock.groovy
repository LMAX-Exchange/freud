package org.freud

import org.freud.analysed.classobject.ClassObjectDsl

import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.filesIn
import static org.freud.groovy.Freud.forEach

class ClassObjectExamplesSpock {

    static File classExamplesPackage = ClassLoader.getSystemResource('ClassObjectExamples').file as File

    def 'equals always goes together with hash code'() {
    expect:
        analyse(analysed) { (it.getDeclaredMethod('equals', Object) &&  it.getDeclaredMethod('hashCode')) ||
                            (!it.getDeclaredMethod('equals', Object) &&  !it.getDeclaredMethod('hashCode'))
        }
    where:
        analysed << forEach(ClassObjectDsl.classOf(filesIn([classExamplesPackage]), classExamplesPackage.parentFile))
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
