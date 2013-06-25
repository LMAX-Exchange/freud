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

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.freud.analysed.javasource.Annotation;
import org.freud.analysed.javasource.ClassDeclaration;
import org.freud.analysed.javasource.CodeBlock;
import org.freud.analysed.javasource.MethodDeclaration;
import org.freud.analysed.javasource.ParamDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;


final class MethodDeclarationJdom implements MethodDeclaration {
    private final Element methodDeclElement;
    private final String name;
    private CodeBlock methodCodeBlock;
    private List<Annotation> annotations;
    private ClassDeclaration classDeclaration;
    private final String returnType;
    private List<ParamDeclaration> paramDeclarations;

    public MethodDeclarationJdom(Element methodDeclElement, ClassDeclaration classDeclaration) {
        this.methodDeclElement = methodDeclElement;
        this.name = JavaSourceJdom.parseName(methodDeclElement);
        this.returnType = JavaSourceJdom.parseType(methodDeclElement);
        this.classDeclaration = classDeclaration;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }

    public CodeBlock getImplementation() {
        if (methodCodeBlock == null) {
            try {
                JXPathContext context = JXPathContext.newContext(methodDeclElement);
                Element codeBlockElement = (Element) context.selectSingleNode("/" + JavaSourceTokenType.BLOCK_SCOPE.getName());
                methodCodeBlock = CodeBlockJdom.createMethodImplementation(codeBlockElement, this, classDeclaration);
            }
            catch (JXPathException e) {
                return null;
            }
        }
        return methodCodeBlock;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ParamDeclaration> getParametersDeclarations() {
        if (paramDeclarations == null) {
            final List<Element> paramListChildren =
                    methodDeclElement.getChild(JavaSourceTokenType.FORMAL_PARAM_LIST.getName()).getChildren();
            paramDeclarations = new ArrayList<ParamDeclaration>(paramListChildren.size());
            for (Element paramDecl : paramListChildren) {
                paramDeclarations.add(new ParamDeclarationJdom(paramDecl));
            }
        }
        return paramDeclarations;
    }

    public List<Annotation> getDeclaredAnnotations() {
        if (annotations == null) {
            annotations = JavaSourceJdom.parseAnnotations(methodDeclElement);
        }
        return annotations;

    }

    public long getModifierMask() {
        // TODO
        return 0;
    }

    public String toString() {
        return classDeclaration + "." + name;
    }
}
