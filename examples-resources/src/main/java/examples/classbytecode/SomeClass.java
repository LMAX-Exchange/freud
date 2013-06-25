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
        }
        else {
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
