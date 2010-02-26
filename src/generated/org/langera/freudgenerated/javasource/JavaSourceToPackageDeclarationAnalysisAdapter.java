// Freud generated code 2010-02-17 22:32:45
package org.langera.freudgenerated.javasource;

import org.langera.freud.*;
import org.langera.freud.dsl.*;
import org.langera.freud.javasource.JavaSource;
import org.langera.freud.javasource.apackage.PackageDeclaration;
import java.util.Collections;    

public final class JavaSourceToPackageDeclarationAnalysisAdapter implements NestedTypeAnalysisAdapter<JavaSource, PackageDeclaration>
{
    private static final JavaSourceToPackageDeclarationAnalysisAdapter SINGLETON = new JavaSourceToPackageDeclarationAnalysisAdapter();

    private JavaSourceToPackageDeclarationAnalysisAdapter()
    {
        // singleton
    }

    public static JavaSourceToPackageDeclarationAnalysisAdapter getInstance()
    {
        return SINGLETON;
    }

    public Iterable<PackageDeclaration> getNestedObjectsToAnalyse(JavaSource toBeAnalysed)
    {
        return Collections.singleton(toBeAnalysed.getPackageDeclaration());
    }
}