package org.freud.core.listener

import spock.lang.Specification
import spock.lang.Subject

class AssertionErrorAnalysisListenerSpec extends Specification {

    @Subject
    AssertionErrorAnalysisListener listener = new AssertionErrorAnalysisListener()

    def 'throws assertion error on failed'() {
    when:
        listener.failed('analysed', 'details')
    then:
        thrown AssertionError
    }

    def 'throws assertion error on unexpected'() {
    when:
        listener.unexpected('analysed', new Exception())
    then:
        thrown AssertionError
    }

    def 'does not throw error on passed or filtered'() {
    when:
        listener.passed('analysed')
        listener.filtered('analysed', 'details')
    then:
        notThrown AssertionError

    }
}
