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



package org.freud.analysed.classobject

import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.classobject.AnnotationsOfAnnotatedElementCreator.Type.DECLARED

class AnnotationsOfAnnotatedElementCreatorSpec extends Specification {

    @Subject
    AnnotationsOfAnnotatedElementCreator creator = new AnnotationsOfAnnotatedElementCreator()

    def 'generates annotations for class'() {
    expect:
        creator.create(Integer) as Set == (Integer.annotations + Integer.declaredAnnotations) as Set
    }

    def 'generates declared annotations for class'() {
    given:
        creator = new AnnotationsOfAnnotatedElementCreator(DECLARED)
    expect:
        creator.create(Integer) as Set == Integer.declaredAnnotations as Set
    }

    def 'generates annotations for inner class'() {
    given:
        creator = new AnnotationsOfAnnotatedElementCreator()
    expect:
        creator.create(InnerClass) as List == [InnerClass.getAnnotation(Deprecated)]
    }

    def 'generates annotations for method'() {
    given:
        creator = new AnnotationsOfAnnotatedElementCreator()
    expect:
        creator.create(InnerClass.getDeclaredMethod('methodWithAnnotation')) as List == [InnerClass.getAnnotation(Deprecated)]
    }

    def 'does not generate annotation which does not have Runtime retention for method'() {
    given:
        creator = new AnnotationsOfAnnotatedElementCreator()
    expect:
        creator.create(InnerClass.getMethod('run')) as List == []
    }

    def 'generates annotations when non occur'() {
    given:
        creator = new AnnotationsOfAnnotatedElementCreator()
    expect:
        creator.create(InnerClass.getDeclaredMethod('methodWithNoAnnotation')) as List == []
    }



    @Deprecated
    private static class InnerClass implements Runnable {

        @Deprecated
        private void methodWithAnnotation() {}

        private void methodWithNoAnnotation() {}

        @Override
        void run() {}
    }
}