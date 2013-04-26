package org.freud.analysed.javasource.jdom;

import org.apache.commons.jxpath.JXPathContext;
import org.freud.analysed.javasource.ClassDeclaration;
import org.freud.analysed.javasource.MethodDeclaration;
import org.freud.analysed.javasource.VarDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.jdom.Element;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.freud.analysed.javasource.jdom.JavaSourceJdom.POSSIBLE_CLASS_DECLARATION_TYPES;
import static org.freud.analysed.javasource.parser.JavaSourceTokenType.CLASS_TOP_LEVEL_SCOPE;
import static org.freud.analysed.javasource.parser.JavaSourceTokenType.EXTENDS_CLAUSE;
import static org.freud.analysed.javasource.parser.JavaSourceTokenType.FUNCTION_METHOD_DECL;
import static org.freud.analysed.javasource.parser.JavaSourceTokenType.IDENT;
import static org.freud.analysed.javasource.parser.JavaSourceTokenType.VOID_METHOD_DECL;
import static org.freud.core.parser.JdomTreeAdaptor.ID_ATTR;


final class ClassDeclarationJdom implements ClassDeclaration {
    private static final String NOT_RETRIEVED = "";
    private final Element classDeclElement;
    private final ClassDeclaration outerClassDeclaration;
    private String name;
    private Map<String, List<MethodDeclaration>> methodDeclarationListByNameMap;
    private Map<String, ClassDeclaration> innerClassDeclarationByNameMap;
    private DeclarationType declarationType;
    private String superClassName = NOT_RETRIEVED;

    public ClassDeclarationJdom(final Element classDeclElement,
                                final DeclarationType declarationType,
                                final ClassDeclaration outerClassDeclaration) {
        this.classDeclElement = classDeclElement;
        this.declarationType = declarationType;
        this.outerClassDeclaration = outerClassDeclaration;
    }

    public String[] getDeclaredClassAnnotations() {
        // TODO
        return new String[0];
    }

    public DeclarationType getDeclarationType() {
        return declarationType;
    }

    public String[] getDeclaredImplementedInterfaceNames() {
        // TODO
        return new String[0];
    }

    public VarDeclaration[] getFieldDeclarations() {
        // TODO
        return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, ClassDeclaration> getInnerClassDeclarationByNameMap() {
        if (innerClassDeclarationByNameMap == null) {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            innerClassDeclarationByNameMap = new HashMap<String, ClassDeclaration>();
            for (JavaSourceTokenType tokenType : POSSIBLE_CLASS_DECLARATION_TYPES) {
                final String tokenName = tokenType.getName();
                List<Element> innerClassElementList =
                        context.selectNodes("/" + CLASS_TOP_LEVEL_SCOPE.getName() + "/" + tokenName);
                for (Element innerClassElement : innerClassElementList) {
                    ClassDeclaration innerClass = new ClassDeclarationJdom(innerClassElement, DeclarationType.valueOf(tokenName), this);
                    innerClassDeclarationByNameMap.put(innerClass.getName(), innerClass);
                }
            }
        }
        return innerClassDeclarationByNameMap;
    }

    @Override
    public ClassDeclaration getOuterClassDeclaration() {
        return outerClassDeclaration;
    }

    // TODO   Block getStaticBlock();


    public Map<String, List<MethodDeclaration>> getMethodDeclarationListByNameMap() {
        if (methodDeclarationListByNameMap == null) {
            JXPathContext context = JXPathContext.newContext(classDeclElement);
            methodDeclarationListByNameMap = new HashMap<String, List<MethodDeclaration>>();
            getMethodDeclarationListByNameMap(context, FUNCTION_METHOD_DECL);
            getMethodDeclarationListByNameMap(context, VOID_METHOD_DECL);
        }
        return methodDeclarationListByNameMap;
    }

    private void getMethodDeclarationListByNameMap(final JXPathContext context, final JavaSourceTokenType methodElementName) {
        List<Element> methodDeclElementList =
                context.selectNodes("//" + methodElementName.getName());
        for (Element methodElement : methodDeclElementList) {
            MethodDeclaration methodDeclaration = new MethodDeclarationJdom(methodElement, this);
            final String name = methodDeclaration.getName();
            List<MethodDeclaration> methodDeclarationList = methodDeclarationListByNameMap.get(name);
            if (methodDeclarationList == null) {
                methodDeclarationList = new LinkedList<MethodDeclaration>();
                methodDeclarationListByNameMap.put(name, methodDeclarationList);
            }
            methodDeclarationList.add(methodDeclaration);

        }
    }

    public long getModifierMask() {
        // TODO
        return 0;
    }

    public String getName() {
        if (name == null) {
            name = classDeclElement.getAttribute(ID_ATTR).getValue();
        }
        return name;
    }

    public String getSuperClassName() {
        if (superClassName == NOT_RETRIEVED) {
            JXPathContext context = JXPathContext.newContext(classDeclElement);

            final Element superClassElement = (Element)
                    context.selectSingleNode("/" + EXTENDS_CLAUSE.getName() + "//" + IDENT.getName());
            superClassName = (null == superClassElement) ? null : superClassElement.getValue();
        }
        return superClassName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
