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

package org.langera.freud.optional.javasource.classdecl;

import org.hamcrest.Description;
import org.langera.freud.core.matcher.FreudMatcher;
import org.langera.freud.core.matcher.RegexMatcherAdapter;
import org.langera.freud.core.matcher.RegexMatcherBuilder;


public final class ClassDeclarationDsl
{
    private ClassDeclarationDsl()
    {
        // static utility
    }

    public static RegexMatcherBuilder<ClassDeclaration> className()
    {
        return new RegexMatcherBuilder<ClassDeclaration>(new RegexMatcherAdapter<ClassDeclaration>()
        {
            @Override
            public String getStringToMatch(final ClassDeclaration toBeAnalysed)
            {
                return toBeAnalysed.getName();
            }

            @Override
            public String matcherDisplayName()
            {
                return "ClassDeclarationName";
            }
        });
    }

    public static FreudMatcher<ClassDeclaration> hasDeclaredMethodNamed(final String methodName)
    {
        return new FreudMatcher<ClassDeclaration>()
        {
            @Override
            protected boolean matchesSafely(final ClassDeclaration item)
            {
                return item.getMethodDeclarationListByNameMap().containsKey(methodName);
            }

            @Override
            public void describeTo(final Description description)
            {
                description.appendText("hasDeclaredMethodNamed(" + methodName + ")");
            }
        };
    }

}
