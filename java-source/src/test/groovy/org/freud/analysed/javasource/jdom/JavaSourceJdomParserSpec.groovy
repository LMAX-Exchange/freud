package org.freud.analysed.javasource.jdom

import spock.lang.Specification

import static org.freud.analysed.javasource.JavaSourceParsingFixture.exampleJavaSourceFileParsedBy

class JavaSourceJdomParserSpec extends Specification {


    def 'parses a file into a Java source'() {
    expect:
        exampleJavaSourceFileParsedBy
                { File file -> JavaSourceJdomParser.parseJavaSource(new FileReader(file), file.name) }
    }
}
