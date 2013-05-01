package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class SystemResourcesSpec extends Specification {

    @Subject
    SystemResources resources

    def 'iterates over files'() {
    given:
        resources = new SystemResources(['SystemResourcesSpec/foo.txt', 'SystemResourcesSpec/bar.txt'])
    when:
        List filesSuffix = resources.collect { it.canonicalPath.substring(it.canonicalPath.indexOf('SystemResourcesSpec')) }
    then:
        filesSuffix == [
                'SystemResourcesSpec/foo.txt',
                'SystemResourcesSpec/bar.txt',
        ]


    }
}
