package org.freud.analysed.javasource.jdom;


import org.freud.analysed.javasource.Annotation;
import org.freud.analysed.javasource.VarDeclaration;

import javax.xml.bind.Element;
import java.util.List;

final class FieldDeclarationJdom implements VarDeclaration {
    private final String type;
    private final boolean finalVariable;
    private final String name;

    public FieldDeclarationJdom(Element varDeclElement) {
        type = "";
        name = "";
        finalVariable = false;
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

        // TODO
        return null;
    }
}
