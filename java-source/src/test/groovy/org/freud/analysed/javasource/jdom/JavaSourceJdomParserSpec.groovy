package org.freud.analysed.javasource.jdom

import spock.lang.Specification

import static org.freud.analysed.javasource.JavaSourceParsingFixture.exampleJavaSourceResourceParsedBy
import static org.freud.analysed.javasource.jdom.JavaSourceJdomParser.parseJavaSource

class JavaSourceJdomParserSpec extends Specification {


    def 'parses a file into a Java source'() {
    expect:
        exampleJavaSourceResourceParsedBy { URL url ->
            parseJavaSource(new InputStreamReader(url.openStream()), url.toExternalForm())
                }
    }
}
