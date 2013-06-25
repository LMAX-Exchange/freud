/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.freud.analysed.classbytecode.method;


import org.freud.analysed.classbytecode.method.instruction.Label;

public final class LocalVariable {
    private final String name;
    private final String desc;
    private final String signature;
    private final Label scopeStart;
    private final Label scopeEnd;

    public LocalVariable(final String name, final String desc, final String signature, final Label scopeStart, final Label scopeEnd) {
        this.name = name;
        this.desc = desc;
        this.signature = signature;
        this.scopeStart = scopeStart;
        this.scopeEnd = scopeEnd;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public Label getScopeEnd() {
        return scopeEnd;
    }

    public Label getScopeStart() {
        return scopeStart;
    }

    public String getSignature() {
        return signature;
    }
}
