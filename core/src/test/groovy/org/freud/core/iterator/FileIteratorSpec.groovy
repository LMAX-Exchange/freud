package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class FileIteratorSpec extends Specification {

    @Subject
    FileIterator fileIterator
    File root = ClassLoader.getSystemResource('FileIteratorSpec/src').file as File


    def 'iterates over files'() {
    given:
        fileIterator = new FileIterator([root], false, null)
    when:
        List files = fileIterator.collect { it }
    then:
        files == [
                new File(root, 'test'),
        ]
    }

    def 'iterates over files recursively'() {
    given:
        fileIterator = new FileIterator([root], true, null)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/a.other'),
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'test'),
        ]
    }

    def 'iterates over files using filter'() {
    given:
        fileIterator = new FileIterator([root], true, { dir, name -> name.endsWith('test') } as FilenameFilter)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'test'),
        ]
    }

    def 'iterates over files in several roots'() {
    given:
        fileIterator = new FileIterator([new File(root, 'a'), new File(root, 'b')], true, { dir, name -> name.endsWith('test') } as FilenameFilter)
    when:
        List files = fileIterator.collect { it }
    then:
        files.sort() == [
                new File(root, 'a/atest'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
        ]
    }
}
