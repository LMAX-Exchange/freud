package org.freud

import org.freud.analysed.javasource.Annotation
import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import static org.freud.analysed.javasource.JavaSourceDsl.classDeclarationsWithin
import static org.freud.analysed.javasource.JavaSourceDsl.codeBlocksWithin
import static org.freud.analysed.javasource.JavaSourceDsl.javaSourceOf
import static org.freud.analysed.javasource.JavaSourceDsl.methodCallsWithin
import static org.freud.analysed.javasource.JavaSourceDsl.methodDeclarationsWithin
import static org.freud.analysed.javasource.JavaSourceDsl.packageDeclarationsWithin
import static org.freud.groovy.Freud.analyse
import static org.freud.groovy.Freud.forEach
import static org.freud.groovy.Freud.resourcesOf

class JavaSourceExamplesSpock extends Specification {

    def 'package depth must be between 3 and 7'() {
    expect:
        analyse(analysed) { it.packagePath.length >= 3 && it.packagePath.length <= 7 }
    where:
        analysed << packageDeclarationsWithin(javaSourceOf(resourcesOf(['javasource/second/third/ExampleClass.javasrc'])))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'package depth must be between 3 and 7 - failing test'() {
    expect:
        analyse(analysed) { it.packagePath.length >= 3 && it.packagePath.length <= 7 }
    where:
        analysed << packageDeclarationsWithin(javaSourceOf(resourcesOf([
                'javasource/second/third/fourth/fifth/sixth/seventh/eighth/ExampleClassWithPackageDepthOfEight.javasrc'])))
    }

    def 'no System.out.print(ln) in code'() {
    expect:
        analyse(analysed) { !(it.instanceReferences == ['System', 'out'] && it.methodName.startsWith('print')) }
    where:
        analysed << methodCallsWithin(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/second/third/ExampleClass.javasrc']))))))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'no System.out.print(ln) in code - failing test'() {
    expect:
        analyse(analysed) { !(it.instanceReferences == ['System', 'out'] && it.methodName.startsWith('print')) }
    where:
        analysed << methodCallsWithin(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/ClassWithSystemOutPrint.javasrc']))))))
    }

    def 'code block limited to max 30 lines'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 30 }
    where:
        analysed << codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/second/third/ExampleClass.javasrc',
                                          'javasource/ClassWith30LineMethod.javasrc'])))))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'code block limited to max 30 lines - failing test'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 30 }
    where:
        analysed << codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/ClassWithLongMethod.javasrc'])))))
    }

    def 'code block limited to max 17 lines unless suppress annotation exists'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 17 }
    where:
        analysed << codeBlocksWithin(forEach(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/second/third/ExampleClass.javasrc',
                        'javasource/ClassWithIgnoredLongMethod.javasrc'])))), {
            !it.declaredAnnotations.find { Annotation annotation ->
                annotation.name == 'SuppressWarning' &&
                        annotation.defaultParameter == '"Ignore this Freud, I admit, this is rubbish code"'
            }
        }))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'code block limited to max 17 lines unless suppress annotation exists - failing test'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 17 }
    where:
        analysed << codeBlocksWithin(forEach(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['javasource/ClassWithLongMethod.javasrc'])))), {
            !it.declaredAnnotations.find { Annotation annotation ->
                annotation.name == 'SuppressWarning' &&
                        annotation.defaultParameter == '"Ignore this Freud, I admit, this is rubbish code"'
            }
        }))
    }
}
