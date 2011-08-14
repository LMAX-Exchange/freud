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

package org.langera.freud.optional.classfile.method;

import org.langera.freud.optional.classfile.method.instruction.Label;

public final class LocalVariable
{
    private final String name;
    private final String desc;
    private final String signature;
    private final Label scopeStart;
    private final Label scopeEnd;

    public LocalVariable(final String name, final String desc, final String signature, final Label scopeStart, final Label scopeEnd)
    {
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.scopeStart = scopeStart;
        this.scopeEnd = scopeEnd;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getName()
    {
        return name;
    }

    public Label getScopeEnd()
    {
        return scopeEnd;
    }

    public Label getScopeStart()
    {
        return scopeStart;
    }

    public String getSignature()
    {
        return signature;
    }
}
