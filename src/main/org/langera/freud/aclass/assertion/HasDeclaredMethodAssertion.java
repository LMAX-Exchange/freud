package org.langera.freud.aclass.assertion;

import org.langera.freud.AnalysisAssertion;

import java.util.Arrays;

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

public final class HasDeclaredMethodAssertion implements AnalysisAssertion<Class>
{
    private String methodName;
    private Class[] parameterTypes;

    public HasDeclaredMethodAssertion(String methodName, Class... parameterTypes)
    {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
    }

    public boolean analyse(Class toBeAnalysed)
    {
        try
        {
            toBeAnalysed.getDeclaredMethod(methodName, parameterTypes);
        }
        catch (NoSuchMethodException e)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hasDeclaredMethod(" + methodName + "(" + Arrays.toString(parameterTypes) + ")" + ")";
    }
}
