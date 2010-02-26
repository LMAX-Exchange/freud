package org.langera.freud.javasource.apackage;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.dsl.CountableDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.javasource.apackage.assertion.PackageDepthCalculation;
import org.langera.freud.javasource.apackage.assertion.PackagePathMatchAssertionAdapter;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class PackageDeclarationAnalysisBuilder
        extends AbstractAnalysisBuilder<PackageDeclarationAnalysisBuilder, PackageDeclaration>
        implements PackageDeclarationDsl
{
    public ReadableDsl<PackageDeclarationAnalysisBuilder> packageDeclaration()
    {
        setRegexAssertionAdapterClass(PackagePathMatchAssertionAdapter.class);
        return (ReadableDsl<PackageDeclarationAnalysisBuilder>) trueAssertion();
    }

    public CountableDsl<PackageDeclarationAnalysisBuilder> packageDepth()
    {
        setCalculation(PackageDepthCalculation.getInstance());
        return this;        
    }

    public Class<PackageDeclaration> getType()
    {
        return PackageDeclaration.class;
    }
}
