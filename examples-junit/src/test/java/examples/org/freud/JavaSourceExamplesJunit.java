package examples.org.freud;

import org.freud.analysed.javasource.CodeBlock;
import org.freud.analysed.javasource.PackageDeclaration;
import org.freud.java.Freud;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.net.URL;

import static examples.org.freud.matchers.CodeBlockMatchers.codeBlockLines;
import static examples.org.freud.matchers.CodeBlockMatchers.hasMethodCall;
import static examples.org.freud.matchers.CodeBlockMatchers.method;
import static examples.org.freud.matchers.MethodDeclarationMatchers.hasDeclaredAnnotation;
import static examples.org.freud.matchers.PackageDeclarationMatchers.packageDepth;
import static java.util.Arrays.asList;
import static org.freud.analysed.javasource.JavaSourceDsl.classDeclarationsWithin;
import static org.freud.analysed.javasource.JavaSourceDsl.codeBlocksWithin;
import static org.freud.analysed.javasource.JavaSourceDsl.javaSourceOf;
import static org.freud.analysed.javasource.JavaSourceDsl.methodDeclarationsWithin;
import static org.freud.analysed.javasource.JavaSourceDsl.packageDeclarationsWithin;
import static org.freud.java.matcher.FreudDsl.no;


public final class JavaSourceExamplesJunit {

    static URL root = ClassLoader.getSystemResource("JavaSourceExamples/");
    private AnalysisListenerStub listener = new AnalysisListenerStub();

    @Test
    public void packageDepthMustBeBetween3And7() throws Exception {
        Freud.iterateOver(PackageDeclaration.class).
                assertThat(packageDepth().lessThanOrEqualTo(7).and(
                        packageDepth().greaterThanOrEqualTo(3))).in(packageDeclarationsWithin(javaSourceOf(asList(
                new URL(root, "ClassWith30LineMethod.javasrc"),
                new URL(root, "ClassWithIgnoredLongMethod.javasrc"))))).analyse(listener);
    }

    @Test
    public void noSystemOutPrintInCode() throws Exception {
        Freud.iterateOver(CodeBlock.class).
                assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println")))).
                in(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(javaSourceOf(asList(
                        new URL(root, "ClassWith30LineMethod.javasrc"),
                        new URL(root, "ClassWithIgnoredLongMethod.javasrc"))))))).analyse(listener);
    }

    @Test
    public void codeBlockSizeIsLimitedTo30Lines() throws Exception {
        Freud.iterateOver(CodeBlock.class).
                assertThat(codeBlockLines().lessThanOrEqualTo(30)).in(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(javaSourceOf(asList(
                new URL(root, "ClassWithLongMethod.javasrc"))))))).analyse(listener);
    }

    @Test
    public void codeBlockSizeIsLimitedToThirtyLinesIfFreudNotSuppressed() throws Exception {
        Freud.iterateOver(CodeBlock.class).
                forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("Ignore this Freud"))))).
                assertThat(codeBlockLines().lessThanOrEqualTo(1)).in(codeBlocksWithin(methodDeclarationsWithin(classDeclarationsWithin(javaSourceOf(asList(
                new URL(root, "ClassWith30LineMethod.javasrc"),
                new URL(root, "ClassWithIgnoredLongMethod.javasrc"))))))).analyse(listener);
    }
}
