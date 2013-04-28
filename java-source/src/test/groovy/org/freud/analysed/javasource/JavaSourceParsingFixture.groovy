package org.freud.analysed.javasource

import static org.langera.spock.SpockExtension.matches

class JavaSourceParsingFixture {

    static boolean exampleJavaSourceFileParsedBy(Closure<JavaSource> parser) {
        matches(EXPECTED_EXAMPLE_JAVA_SOURCE).call(parser.call(EXAMPLE_JAVA_SOURCE))
    }

    private static final File EXAMPLE_JAVA_SOURCE = new File(ClassLoader.getSystemResource('org/freud/example/ExampleJavaSourceClass.java').file)

    private static final Map<String, Object> EXPECTED_EXAMPLE_JAVA_SOURCE = [
            fileName: 'ExampleJavaSourceClass.java',
            simpleClassName: 'ExampleJavaSourceClass',
            fullClassName: 'org.freud.example.ExampleJavaSourceClass',
            packageDeclaration: packageDeclaration('org.freud.example'),
            classDeclaration: matches([
                    name: 'ExampleJavaSourceClass',
                    superClassName: null,
                    declarationType: ClassDeclaration.DeclarationType.CLASS,
                    declaredImplementedInterfaceNames: [],
                    methodDeclarationListByNameMap: { matches([
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
                    ]).call(it.values() as List) }
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