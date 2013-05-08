package org.freud.analysed.javasource.jdom;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.freud.analysed.javasource.Annotation;
import org.freud.analysed.javasource.ClassDeclaration;
import org.freud.analysed.javasource.ImportDeclaration;
import org.freud.analysed.javasource.JavaSource;
import org.freud.analysed.javasource.PackageDeclaration;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.freud.core.parser.JdomTreePositionComparator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Collections.emptyList;
import static org.freud.analysed.javasource.jdom.JavaSourceJdomParser.JAVA_SOURCE_ROOT_ELEMENT_NAME;
import static org.freud.core.parser.JdomTreeAdaptor.documentToString;

final class JavaSourceJdom implements JavaSource {

    public static final JavaSourceTokenType[] POSSIBLE_CLASS_DECLARATION_TYPES = {
            JavaSourceTokenType.CLASS,
            JavaSourceTokenType.INTERFACE,
            JavaSourceTokenType.ENUM,
            JavaSourceTokenType.AT,
    };

    private final String resourceName;
    private final Document root;
    private ClassDeclaration classDeclaration;
    private PackageDeclaration packageDeclaration;
    private List<ImportDeclaration> importDeclarations;

    public JavaSourceJdom(final Document root, final String resourceName) {
        this.root = root;
        this.resourceName = resourceName;
    }

    public Document getDocument() {
        return root;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    public PackageDeclaration getPackageDeclaration() {
        return (packageDeclaration == null) ? parsePackageDeclaration() : packageDeclaration;
    }

    @Override
    public List<ImportDeclaration> getImportDeclarations() {
        return (importDeclarations == null) ? parseImportDeclaration() : importDeclarations;
    }

    @Override
    public ClassDeclaration getClassDeclaration() {
        return (classDeclaration == null) ? parseClassDeclaration() : classDeclaration;
    }

/////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public String getFullClassName() {

        final String packagePath = getPackageDeclaration().getPackagePathAsString();
        final String className = getClassDeclaration().getName();
        return (packagePath.length() > 0) ? packagePath + "." + className : className;
    }

    @Override
    public String getSimpleClassName() {
        return getClassDeclaration().getName();
    }

    @Override
    public String toString() {
        return documentToString(root);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String[] parsePackagePath(final Element element) {
        SortedSet<Element> packagePathElementSortedSet = new TreeSet<Element>(JdomTreePositionComparator.getInstance());

        for (Iterator iterator = element.getDescendants(new ElementFilter(JavaSourceTokenType.IDENT.getName()));
             iterator.hasNext(); ) {
            packagePathElementSortedSet.add((Element) iterator.next());

        }

        boolean endsWithDotStar = (element.getChild(JavaSourceTokenType.DOTSTAR.getName()) != null);
        final String[] packagePath = new String[(endsWithDotStar) ? packagePathElementSortedSet.size() + 1 :
                packagePathElementSortedSet.size()];
        int i = 0;
        for (Element pathElement : packagePathElementSortedSet) {
            packagePath[i++] = pathElement.getTextTrim();
        }
        if (endsWithDotStar) {
            packagePath[i] = "*";
        }
        return packagePath;
    }

    public static String buildPackagePath(final String[] packagePath) {
        StringBuilder packagePathStrBuilder = new StringBuilder();
        for (int i = 0, size = packagePath.length; i < size; i++) {
            if (i > 0) {
                packagePathStrBuilder.append('.');
            }
            packagePathStrBuilder.append(packagePath[i]);
        }
        return packagePathStrBuilder.toString();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    private ClassDeclaration parseClassDeclaration() {

        JXPathContext context = JXPathContext.newContext(root);
        for (JavaSourceTokenType tokenType : POSSIBLE_CLASS_DECLARATION_TYPES) {
            try {
                final String tokenName = tokenType.name();
                final Element element = (Element) context.selectSingleNode("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" + tokenName);
                if (element != null) {
                    classDeclaration = new ClassDeclarationJdom(element, getDeclarationType(tokenType), null);
                }
            }
            catch (JXPathException e) {
                // ignore and try another path
            }
        }
        if (classDeclaration == null) {
            throw new IllegalStateException("Internal: could not find class declaration in: " + this);
        }

        return classDeclaration;
    }

    private ClassDeclaration.DeclarationType getDeclarationType(JavaSourceTokenType tokenType) {
        switch (tokenType) {
            case CLASS:
                return ClassDeclaration.DeclarationType.CLASS;
            case INTERFACE:
                return ClassDeclaration.DeclarationType.INTERFACE;
            case ENUM:
                return ClassDeclaration.DeclarationType.ENUM;
            case AT:
                return ClassDeclaration.DeclarationType.ANNOTATION;
            default:
                throw new IllegalStateException("internal. unsupported Class decl. token type [" + tokenType + "]");
        }
    }

    private PackageDeclaration parsePackageDeclaration() {
        try {
            JXPathContext context = JXPathContext.newContext(root);
            packageDeclaration = new PackageDeclarationJdom((Element)
                    context.selectSingleNode("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" +
                            JavaSourceTokenType.PACKAGE.name()));
        }
        catch (JXPathException e) {
            packageDeclaration = new PackageDeclarationJdom();
        }

        return packageDeclaration;
    }

    private List<ImportDeclaration> parseImportDeclaration() {
        try {
            final JXPathContext context = JXPathContext.newContext(root);
            final List importNodes = context.selectNodes("/" + JAVA_SOURCE_ROOT_ELEMENT_NAME + "/" +
                    JavaSourceTokenType.IMPORT.name());
            importDeclarations = new ArrayList<ImportDeclaration>(importNodes.size());
            for (Object importNode : importNodes) {
                importDeclarations.add(new ImportDeclarationJdom((Element) importNode));
            }
        }
        catch (JXPathException e) {
            importDeclarations = emptyList();
        }
        return importDeclarations;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<Annotation> parseAnnotations(final Element element) {
        final List<Annotation> annotations;
        JXPathContext context = JXPathContext.newContext(element);
        List annotationList = context.selectNodes("/" + JavaSourceTokenType.MODIFIER_LIST.getName() +
                "/" + JavaSourceTokenType.AT.getName());
        annotations = new ArrayList<Annotation>(annotationList.size());
        for (Object annotationElement : annotationList) {
            annotations.add(new AnnotationJdom((Element) annotationElement));
        }
        return annotations;
    }

    public static String parseName(final Element element) {
        return element.getChildText(JavaSourceTokenType.IDENT.getName());
    }

    public static String parseType(final Element element) {
        final Element typeElement = element.getChild(JavaSourceTokenType.TYPE.getName());
        if (typeElement == null) { //void
            return JavaSourceTokenType.VOID.name().toLowerCase();
        }
        Element qualifiedType = typeElement.getChild(JavaSourceTokenType.QUALIFIED_TYPE_IDENT.getName());
        if (qualifiedType != null) {
            return qualifiedType.getChildText(JavaSourceTokenType.IDENT.getName());
        }
        else { //primitive type
            List<Element> children = typeElement.getChildren();
            return children.get(0).getText();
        }
    }
}
