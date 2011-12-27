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

package org.langera.freud.optional.javasource.importdecl;

import org.hamcrest.Description;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.StringMatcherBuilder;


public final class ImportDeclarationDsl
{
    private ImportDeclarationDsl()
    {
        // static utility
    }

    public static StringMatcherBuilder<ImportDeclaration> importDeclaration()
    {
        return new StringMatcherBuilder<ImportDeclaration>(new RegexMatcherAdapter<ImportDeclaration>()
        {
            @Override
            public String getStringToMatch(final ImportDeclaration toBeAnalysed)
            {
                return toBeAnalysed.getImportDeclarationPathAsString();
            }

            @Override
            public String matcherDisplayName()
            {
                return "ImportDeclaration";
            }
        });
    }


    public static StringMatcherBuilder<ImportDeclaration> importDeclarationLastElement()
    {
        return new StringMatcherBuilder<ImportDeclaration>(new RegexMatcherAdapter<ImportDeclaration>()
        {
            @Override
            public String getStringToMatch(final ImportDeclaration toBeAnalysed)
            {
                final String[] path = toBeAnalysed.getImportDeclarationPath();
                return (path.length == 0) ? null : path[path.length  - 1];
            }

            @Override
            public String matcherDisplayName()
            {
                return "ImportDeclaration";
            }
        });
    }

    public static FreudMatcher<ImportDeclaration> staticImport()
    {
        return new FreudMatcher<ImportDeclaration>()
        {
            @Override
            protected boolean matchesSafely(final ImportDeclaration importDeclaration)
            {
                return importDeclaration.isStaticDecalaration();
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("staticImport");
            }
        };
    }
}
