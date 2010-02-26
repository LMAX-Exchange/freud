package org.langera.freud.aclass.assertion;

import org.langera.freud.AnalysisAssertion;

import java.lang.reflect.Method;

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

public final class HasSetterForTypeAssertion implements AnalysisAssertion<Class>
{
    private Class type;

    public HasSetterForTypeAssertion(Class type)
    {
        this.type = type;
    }

    public boolean analyse(Class toBeAnalysed)
    {
        Method[] methods = toBeAnalysed.getMethods();
        for (Method method : methods)
        {
            if (method.getName().startsWith("set"))
            {
                Class[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 1 && type.isAssignableFrom(paramTypes[0]))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "hasSetterOfTYpe(" + type.getName() + ")";
    }
}
