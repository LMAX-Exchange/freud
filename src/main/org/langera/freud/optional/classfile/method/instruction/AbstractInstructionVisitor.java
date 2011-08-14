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

package org.langera.freud.optional.classfile.method.instruction;

public abstract class AbstractInstructionVisitor implements InstructionVisitor
{
    @Override
    public void noArgInstruction(final Instruction instruction)
    {
    }

    @Override
    public void methodInvocation(final Instruction instruction, final String owner, final String methodName, final String... args)
    {
    }

    public static String typeEncoding(Class clazz)
    {
        final StringBuilder sb = new StringBuilder();
        if (clazz.isArray())
        {
            sb.append(clazz.getName().replace('.', '/'));
        }
        else
        {
            if (clazz.isPrimitive())
            {
                if (boolean.class.equals(clazz))
                {
                    sb.append("Z");
                }
                else if (byte.class.equals(clazz))
                {
                    sb.append("B");
                }
                else if (char.class.equals(clazz))
                {
                    sb.append("C");
                }
                else if (double.class.equals(clazz))
                {
                    sb.append("D");
                }
                else if (float.class.equals(clazz))
                {
                    sb.append("F");
                }
                else if (int.class.equals(clazz))
                {
                    sb.append("I");
                }
                else if (long.class.equals(clazz))
                {
                    sb.append("J");
                }
                else if (short.class.equals(clazz))
                {
                    sb.append("S");
                }
            }
            else
            {
                sb.append("L").append(clazz.getName().replace('.', '/')).append(";");
            }
        }
        return sb.toString();
    }

}
