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

package org.freud.analysed.javasource.jdom;

import org.freud.analysed.javasource.Annotation;
import org.freud.analysed.javasource.ParamDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.jdom.Element;

import java.util.List;


final class ParamDeclarationJdom implements ParamDeclaration {
    private final Element paramDecl;
    private final String type;
    private final boolean finalVariable;
    private final boolean varArgs;
    private final String name;
    private List<Annotation> annotations;

    public ParamDeclarationJdom(final Element paramDecl) {
        this.paramDecl = paramDecl;
        this.type = JavaSourceJdom.parseType(paramDecl);
        this.name = JavaSourceJdom.parseName(paramDecl);
        this.varArgs = JavaSourceTokenType.FORMAL_PARAM_VARARG_DECL.getName().equals(paramDecl.getName());
        this.finalVariable = paramDecl.getChild(JavaSourceTokenType.LOCAL_MODIFIER_LIST.getName()).
                getChild(JavaSourceTokenType.FINAL.getName()) != null;
    }

    @Override
    public boolean isVarArgsParam() {
        return varArgs;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isFinalVariable() {
        return finalVariable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Annotation> getDeclaredAnnotations() {
        if (annotations == null) {
            annotations = JavaSourceJdom.parseAnnotations(paramDecl);
        }
        return annotations;

    }
}
