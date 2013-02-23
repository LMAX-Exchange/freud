package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class ClassNamesSpec extends Specification {

    @Subject
    ClassNames classNamesIterator
    File root = ClassLoader.getSystemResource('ClassNamesSpec/src').file as File


    def 'iterates over class names'() {
    given:
        classNamesIterator = new ClassNames([root], false, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames == [ 'Test' ]
    }

    def 'iterates over class names recursively'() {
    given:
        classNamesIterator = new ClassNames([root], true, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'Test',
                'a.TestA',
                'a.TestAA',
                'b.TestB',
                'b.c.TestC',
        ]
    }

    def 'iterates over class names using filter'() {
    given:
        classNamesIterator = new ClassNames([root], true, { dir, name -> name.endsWith('.myjava') } as FilenameFilter)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'Test',
                'a.TestA',
                'b.TestB',
                'b.c.TestC',
        ]
    }

    def 'iterates over class names in several roots'() {
    given:
        classNamesIterator = new ClassNames([new File(root, 'a'), new File(root, 'b')], true, null)
    when:
        List classNames = classNamesIterator.collect { it }
    then:
        classNames.sort() == [
                'TestA',
                'TestAA',
                'TestB',
                'c.TestC',
        ]
    }
}
