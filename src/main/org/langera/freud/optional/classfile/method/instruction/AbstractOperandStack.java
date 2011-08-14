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

public abstract class AbstractOperandStack implements OperandStack
{
    public static final OperandStack EMPTY_STACK = new AbstractOperandStack(null, null)
    {
        @Override
        protected String getTypeForCurrentOperandStackItem()
        {
            throw new IllegalStateException("cannot pop an empty stack");
        }

        @Override
        public OperandStack next()
        {
            throw new IllegalStateException("cannot pop an empty stack");
        }

        @Override
        public OperandStack dup(final OperandStack next, final Opcode opcode)
        {
            throw new UnsupportedOperationException("Cannot duplicate empty stack");
        }

        @Override
        public int depth()
        {
            return 0;
        }

        @Override
        public int getComputationalTypeCategory()
        {
            return 0;
        }

        @Override
        protected String toStringInternal()
        {
            return "";
        }
    };

    private final OperandStack next;
    private final Opcode opcode;


    public AbstractOperandStack(final OperandStack next, final Opcode opcode)
    {
        this.next = next;
        this.opcode = opcode;
    }

    protected static int calculateComputationalTypeCategory(String type)
    {
        return ("J".equals(type) || "D".equals(type)) ? 2 : 1;
    }

    protected abstract String getTypeForCurrentOperandStackItem();

    @Override
    public String getOperandType()
    {
        return getTypeForCurrentOperandStackItem();
    }

    @Override
    public OperandStack next()
    {
        return next;
    }

    @Override
    public Opcode getGeneratingOpcode()
    {
        return opcode;
    }

    @Override
    public int depth()
    {
        return 1 + next.depth();
    }

    @Override
    public final String toString()
    {
        return toStringInternal() + "|" + (next == null ? "" : next);
    }

    protected abstract String toStringInternal();
}
