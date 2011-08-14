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

public final class CastOperandStack extends AbstractOperandStack
{
    private final String fromType;
    private final String toType;
    private final int category;

    public CastOperandStack(final String fromType, final String toType, final OperandStack next, final Opcode opcode)
    {
        super(next, opcode);
        this.fromType = fromType;
        this.toType = toType;
        this.category = calculateComputationalTypeCategory(toType);
    }

    @Override
    protected String getTypeForCurrentOperandStackItem()
    {
        return fromType;
    }

    @Override
    protected String toStringInternal()
    {
        return "cast to " + toType;
    }

    public String getFromType()
    {
        return fromType;
    }

    @Override
    public OperandStack dup(final OperandStack next, final Opcode opcode)
    {
        return new CastOperandStack(fromType, toType, next, opcode);
    }

    @Override
    public int getComputationalTypeCategory()
    {
        return category;
    }
}
