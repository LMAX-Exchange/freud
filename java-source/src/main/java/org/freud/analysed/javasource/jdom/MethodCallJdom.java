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
import org.freud.analysed.javasource.MethodCall;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.freud.core.parser.JdomTreeAdaptor;
import org.freud.core.parser.JdomTreePositionComparator;
import org.jdom.Attribute;
import org.jdom.Element;

import java.util.Collections;
import java.util.List;


final class MethodCallJdom implements MethodCall {
    private static final String[] EMPTY_ARRAY = new String[0];

    private Element methodCallElement;
    private String methodName;
    private String[] instanceReferences;

    public MethodCallJdom(Element methodCallElement) {
        this.methodCallElement = methodCallElement;
    }

    @SuppressWarnings("unchecked")
    public String getMethodName() {
        if (methodName == null) {
            final Attribute idAttr = methodCallElement.getAttribute(JdomTreeAdaptor.ID_ATTR);
            if (idAttr != null) {
                methodName = idAttr.getValue();
                instanceReferences = EMPTY_ARRAY;
            }
            else {
                JXPathContext context = JXPathContext.newContext(methodCallElement);
                final List<Element> identElementList = context.selectNodes("//" + JavaSourceTokenType.IDENT.getName());

                Collections.sort(identElementList, JdomTreePositionComparator.getInstance());

                final int methodNameIndex = identElementList.size() - 1;
                methodName = identElementList.get(methodNameIndex).getText();
                instanceReferences = new String[methodNameIndex];
                for (int i = 0, size = instanceReferences.length; i < size; i++) {
                    instanceReferences[i] = identElementList.get(i).getText();
                }
            }
        }
        return methodName;
    }

    public String[] getInstanceReferences() {
        return instanceReferences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MethodCall ");
        for (String reference : instanceReferences) {
            sb.append(reference).append('.');
        }
        sb.append(methodName);

        return sb.toString();
    }
}
