package org.freud.analysed.javasource

import spock.lang.Specification
import spock.lang.Subject

class CodeBlockToMethodCallsCreatorSpec extends Specification {

    @Subject
    CodeBlockToMethodCallsCreator creator = new CodeBlockToMethodCallsCreator()

    def 'returns method calls inside code block'() {
    given:
        CodeBlock codeBlock = Mock()
        MethodCall call1 = Mock()
        MethodCall call2 = Mock()
        MethodCall call3 = Mock()
        codeBlock.methodCallByMethodNameMap >> [method1: [call1, call2], method2: [call3]]
    expect:
        creator.create(codeBlock) as List == [call1, call2, call3]
    }
}
