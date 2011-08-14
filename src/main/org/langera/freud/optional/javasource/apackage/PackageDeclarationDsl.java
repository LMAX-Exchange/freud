/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.freud.optional.javasource.apackage;

import org.langera.freud.core.matcher.IntOperatorMatcherAdapter;
import org.langera.freud.core.matcher.IntOperatorMatcherBuilder;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;


public final class PackageDeclarationDsl
{
    private PackageDeclarationDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<PackageDeclaration> packageDeclaration()
    {
        return new RegexMatcherBuilder<PackageDeclaration>(new RegexMatcherAdapter<PackageDeclaration>()
        {
            @Override
            public String getStringToMatch(final PackageDeclaration toBeAnalysed)
            {
                return toBeAnalysed.getPackagePathAsString();
            }

            @Override
            public String matcherDisplayName()
            {
                return "PackageDeclaration";
            }
        });
    }

    public static IntOperatorMatcherBuilder<PackageDeclaration> packageDepth()
    {
        return new IntOperatorMatcherBuilder<PackageDeclaration>(new IntOperatorMatcherAdapter<PackageDeclaration>()
        {
            @Override
            public int valueOf(final PackageDeclaration matched)
            {
                return matched.getPackagePath().length;
            }

            @Override
            public String matcherDisplayName()
            {
                return "PackageDepth";
            }
        });
    }
}
