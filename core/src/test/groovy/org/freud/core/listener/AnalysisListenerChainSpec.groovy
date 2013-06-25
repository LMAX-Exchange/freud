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



package org.freud.core.listener

import spock.lang.Specification
import spock.lang.Subject

class AnalysisListenerChainSpec extends Specification {

    AnalysisListener listener1 = Mock()
    AnalysisListener listener2 = Mock()
    AnalysisListener listener3 = Mock()
    @Subject
    AnalysisListenerChain listenerChain = new AnalysisListenerChain(listener1, listener2, listener3)

    def 'chain delegates done call to all listeners'() {
    when:
        listenerChain.done()
    then:
        1 * listener1.done()
        1 * listener2.done()
        1 * listener3.done()
    }

    def 'chain delegates unexpected call to all listeners'() {
    given:
        Object analysed = 'analysed'
        Exception ex = new Exception()
    when:
        listenerChain.unexpected(analysed, ex)
    then:
        1 * listener1.unexpected(analysed, ex)
        1 * listener2.unexpected(analysed, ex)
        1 * listener3.unexpected(analysed, ex)
    }

    def 'chain delegates passed call to all listeners'() {
    given:
        Object analysed = 'analysed'
    when:
        listenerChain.passed(analysed)
    then:
        1 * listener1.passed(analysed)
        1 * listener2.passed(analysed)
        1 * listener3.passed(analysed)
    }

    def 'chain delegates filtered call to all listeners'() {
    given:
        Object analysed = 'analysed'
        String details = 'details'
    when:
        listenerChain.filtered(analysed, details)
    then:
        1 * listener1.filtered(analysed, details)
        1 * listener2.filtered(analysed, details)
        1 * listener3.filtered(analysed, details)
    }

    def 'chain delegates failed call to all listeners'() {
    given:
        Object analysed = 'analysed'
        String details = 'details'
    when:
        listenerChain.failed(analysed, details)
    then:
        1 * listener1.failed(analysed, details)
        1 * listener2.failed(analysed, details)
        1 * listener3.failed(analysed, details)
    }
}
