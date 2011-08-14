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

public final class Label
{
    private final int createdInstructionIndex;
    private final int key;
    private final boolean defaultKey;
    private final String handledType;
    private int declaredInstructionIndex;
    private int lineNumber;

    public static Label create(final int createdInstructionIndex)
    {
        return new Label(createdInstructionIndex, null, -1, false);
    }

    public static Label createHandler(final int createdInstructionIndex, final String handledType)
    {
        return new Label(createdInstructionIndex, handledType, -1, false);
    }

    public static Label createLookupKey(final int createdInstructionIndex, final int key)
    {
        return new Label(createdInstructionIndex, null, key, false);
    }

    public static Label createDefaultLookupKey(final int createdInstructionIndex)
    {
        return new Label(createdInstructionIndex, null, -1, true);
    }

    private Label(final int createdInstructionIndex, final String handledType, final int key, final boolean defaultKey)
    {
        this.createdInstructionIndex = createdInstructionIndex;
        this.handledType = handledType;
        this.key = key;
        this.defaultKey = defaultKey;
        declaredInstructionIndex = -1;
        lineNumber = -1;
    }

    public void declare(final int instructionIndex)
    {
        declaredInstructionIndex = instructionIndex;
    }

    public void setLineNumber(final int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public int getKey()
    {
        return key;
    }

    public int getCreatedInstructionIndex()
    {
        return createdInstructionIndex;
    }

    public int getDeclaredInstructionIndex()
    {
        return declaredInstructionIndex;
    }

    public String getHandledType()
    {
        return handledType;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    @Override
    public String toString()
    {
        return "Label{" +
                "createdInstructionIndex=" + createdInstructionIndex +
                ", key=" + key +
                ", defaultKey=" + defaultKey +
                ", handledType='" + handledType + '\'' +
                ", declaredInstructionIndex=" + declaredInstructionIndex +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
