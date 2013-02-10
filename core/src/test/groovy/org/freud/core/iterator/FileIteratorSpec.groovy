package org.freud.core.iterator

import spock.lang.Specification
import spock.lang.Subject

class FileIteratorSpec extends Specification {

    @Subject
    FileIterator fileIterator
    File root = ClassLoader.getSystemResource('FileIteratorSpec/src').file as File


    def 'iterates over files'() {
    given:
        fileIterator = new FileIterator(false, root)
    when:
        List files = fileIterator.collect { it }
    then:
        files == [
                new File(root, 'test'),
        ]
    }

    def 'iterates over files recursively'() {
    given:
        fileIterator = new FileIterator(true, root)
    when:
        List files = fileIterator.collect { it }
    then:
        files == [
                new File(root, 'test'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'a/atest'),
                new File(root, 'a/a.other'),
        ]
    }

    def 'iterates over files using filter'() {
    given:
        fileIterator = new FileIterator(true, { dir, name -> name.endsWith('test') } as FilenameFilter, root)
    when:
        List files = fileIterator.collect { it }
    then:
        files == [
                new File(root, 'test'),
                new File(root, 'b/btest'),
                new File(root, 'b/c/ctest'),
                new File(root, 'a/atest'),
        ]
    }
}
