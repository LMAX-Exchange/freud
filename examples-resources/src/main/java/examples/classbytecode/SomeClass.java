package examples.classbytecode;

import java.io.IOException;

public final class SomeClass {

    public void thisThrowsException() throws Exception {
        throw new Exception();
    }


    public void thisThrowsRuntimeException() {
        throw new RuntimeException();
    }

    public void thisDeclaresExceptionButDoesNotThrowAny() throws IOException {
        System.out.println("do nothing");
    }

    public void thisDoesNothing() {
        System.out.println("do nothing");
    }

    public void thisHasBranchLogic(boolean flag) {
        if (flag) {
            System.out.println("true");
        }  else {
            System.out.println("false");
        }
    }

    public void thisOneAlsoHasBranchLogic(boolean flag) {
        System.out.println((flag) ? "true" : "false");
    }

    public void thisOneHasALoopWhichIsAlsoABranchLogic() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
