package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class SystemResourcesSpec extends Specification {

    @Subject
    SystemResources resources

    def 'iterates over resources'() {
    given:
        resources = new SystemResources(['SystemResourcesSpec/foo.txt', 'SystemResourcesSpec/bar.txt'])
    when:
        List filesSuffix = resources.collect { URL url ->
            url.path.substring(url.path.indexOf('SystemResourcesSpec'))
        }
    then:
        filesSuffix == [
                'SystemResourcesSpec/foo.txt',
                'SystemResourcesSpec/bar.txt',
        ]


    }
}
