/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



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
        analysed << packageDeclarationsWithin(javaSourceOf(resourcesOf(['JavaSourceExamples/second/third/ExampleClass.javasrc'])))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'package depth must be between 3 and 7 - failing test'() {
    expect:
        analyse(analysed) { it.packagePath.length >= 3 && it.packagePath.length <= 7 }
    where:
        analysed << packageDeclarationsWithin(javaSourceOf(resourcesOf([
                                                                               'JavaSourceExamples/second/third/fourth/fifth/sixth/seventh/eighth/ExampleClassWithPackageDepthOfEight.javasrc'])))
    }

    def 'no System.out.print(ln) in code'() {
    expect:
        analyse(analysed) { !(it.instanceReferences == ['System', 'out'] && it.methodName.startsWith('print')) }
    where:
        analysed << methodCallsWithin(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/second/third/ExampleClass.javasrc']))))))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'no System.out.print(ln) in code - failing test'() {
    expect:
        analyse(analysed) { !(it.instanceReferences == ['System', 'out'] && it.methodName.startsWith('print')) }
    where:
        analysed << methodCallsWithin(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/ClassWithSystemOutPrint.javasrc']))))))
    }

    def 'code block limited to max 30 lines'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 30 }
    where:
        analysed << codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/second/third/ExampleClass.javasrc',
                                                 'JavaSourceExamples/ClassWith30LineMethod.javasrc'])))))
    }

    @FailsWith(ConditionNotSatisfiedError)
    def 'code block limited to max 30 lines - failing test'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 30 }
    where:
        analysed << codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/ClassWithLongMethod.javasrc'])))))
    }

    def 'code block limited to max 17 lines unless suppress annotation exists'() {
    expect:
        analyse(analysed) { it.numberOfLines <= 17 }
    where:
        analysed << codeBlocksWithin(forEach(                                                                methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/second/third/ExampleClass.javasrc',
                                                 'JavaSourceExamples/ClassWithIgnoredLongMethod.javasrc'])))), {
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
        analysed << codeBlocksWithin(forEach(                                                  methodDeclarationsWithin(classDeclarationsWithin(
                javaSourceOf(resourcesOf(['JavaSourceExamples/ClassWithLongMethod.javasrc'])))), {
            !it.declaredAnnotations.find { Annotation annotation ->
                annotation.name == 'SuppressWarning' &&
                        annotation.defaultParameter == '"Ignore this Freud, I admit, this is rubbish code"'
            }
        }))
    }
}
