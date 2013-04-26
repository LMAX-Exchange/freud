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
}
