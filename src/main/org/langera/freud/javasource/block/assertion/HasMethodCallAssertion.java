package org.langera.freud.javasource.block.assertion;

import org.langera.freud.AnalysisAssertion;
import org.langera.freud.javasource.block.CodeBlock;
import org.langera.freud.javasource.methodcall.MethodCall;

import java.util.Arrays;
import java.util.List;

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

public final class HasMethodCallAssertion implements AnalysisAssertion<CodeBlock>
{
    private String methodName;
    private String[] instanceReferences;

    public HasMethodCallAssertion(String methodCall)
    {
        String[] values = methodCall.split("\\.");
        instanceReferences = new String[values.length - 1];
        System.arraycopy(values, 0, instanceReferences, 0, instanceReferences.length);
        methodName = values[values.length - 1];

    }


    public boolean analyse(CodeBlock toBeAnalysed)
    {
        List<MethodCall> methodCallList = toBeAnalysed.getMethodCallListByMethodName(methodName);
        if (methodCallList != null)
        {
            for (MethodCall methodCall : methodCallList)
            {
                if (Arrays.equals(instanceReferences, methodCall.getInstanceReferences()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString()
    {
        return super.toString();
    }
}
