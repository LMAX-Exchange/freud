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



package org.freud.analysed.javasource

import static java.lang.ClassLoader.getSystemResource
import static org.langera.spock.SpockExtension.matches

class JavaSourceParsingFixture {

    static boolean exampleJavaSourceResourceParsedBy(Closure<JavaSource> parser) {
        matches(EXPECTED_EXAMPLE_JAVA_SOURCE).call(parser.call(EXAMPLE_JAVA_SOURCE))
    }

    private static final URL EXAMPLE_JAVA_SOURCE = getSystemResource('org/freud/example/ExampleJavaSourceClass.java')

    private static final Map<String, Object> EXPECTED_EXAMPLE_JAVA_SOURCE = [
            resourceName: { it.startsWith('file:') && it.endsWith('ExampleJavaSourceClass.java') },
            simpleClassName: 'ExampleJavaSourceClass',
            fullClassName: 'org.freud.example.ExampleJavaSourceClass',
            packageDeclaration: packageDeclaration('org.freud.example'),
            classDeclaration: matches([
                                              name: 'ExampleJavaSourceClass',
                                              superClassName: null,
                                              declarationType: ClassDeclaration.DeclarationType.CLASS,
                                              declaredImplementedInterfaceNames: [],
                                              methodDeclarationListByNameMap: {
                                                  matches([
                                                                  methodDeclarations('noArgsVoidMethod', 'void', 1),
                                                                  methodDeclarations('oneStringArgVoidMethod', 'void', 1),
                                                                  methodDeclarations('onePrimitiveIntArgVoidMethod', 'void', 1),
                                                                  methodDeclarations('multipleArgsMethod', 'void', 1),
                                                                  methodDeclarations('methodReturningList', 'List', 1),
                                                                  methodDeclarations('methodReturningBigDecimal', 'BigDecimal', 1),
                                                                  methodDeclarations('methodReturningPrimitiveInt', 'int', 1),
                                                                  methodDeclarations('privateMethodWithAnnotation', 'List', 1),
                                                                  methodDeclarations('privateMethodWithGenerics', 'List', 1),
                                                                  methodDeclarations('overloadedMethod', 'void', 2),
                                                          ]).call(it.values() as List)
                                              }
                                      ]),
            importDeclarations: matches([importDeclaration('java.math.BigDecimal'),
                                                importDeclaration('java.util.*'),
                                                staticImportDeclaration('java.math.BigDecimal.ZERO'),
                                        ]),
    ]

    static Closure<Boolean> methodDeclarations(String name, String returnType, int overloadedCount) {
        return {
            Closure<Boolean> matchesName = { MethodDeclaration methodDeclaration ->
                methodDeclaration.name == name &&
                        methodDeclaration.returnType == returnType
            }
            List<Closure<Boolean>> conditions = []
            overloadedCount.times {
                conditions << matchesName
            }
            matches(conditions).call(it)
        }
    }

    static Closure<Boolean> packageDeclaration(final String packageAsString) {
        return { PackageDeclaration declaration ->
            declaration.packagePathAsString == packageAsString &&
                    declaration.packagePath as List == packageAsString.split(/\./)
        }
    }

    static Closure<Boolean> staticImportDeclaration(final String importAsString) {
        return { ImportDeclaration declaration ->
            declaration.importDeclarationPathAsString == importAsString &&
                    declaration.importDeclarationPath as List == importAsString.split(/\./) &&
                    declaration.staticDecalaration
        }
    }

    static Closure<Boolean> importDeclaration(final String importAsString) {
        return { ImportDeclaration declaration ->
            declaration.importDeclarationPathAsString == importAsString &&
                    declaration.importDeclarationPath as List == importAsString.split(/\./) &&
                    !declaration.staticDecalaration
        }
    }
}