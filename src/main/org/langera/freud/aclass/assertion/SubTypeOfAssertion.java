package org.langera.freud.aclass.assertion;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class SubTypeOfAssertion extends TypeSafeMatcher<Class>
{
    private final Class<?> superType;

    public SubTypeOfAssertion(final Class<?> superType)
    {
        this.superType = superType;
    }

    public final boolean matchesSafely(final Class toBeAnalysed)
    {
        return superType.isAssignableFrom(toBeAnalysed);
    }

    @Override
    public String toString()
    {
        return "subTypeOf(" + superType.getName() + ")";
    }

    public void describeTo(Description description)
    {
        description.appendText(toString());
    }
}
