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

package org.freud.example;

import java.math.BigDecimal;
import java.util.*;
import static java.math.BigDecimal.ZERO;

public final class ExampleJavaSourceClass {

    public void noArgsVoidMethod() {
        System.out.println("Hello");
    }

    public void oneStringArgVoidMethod(String string) {
        System.out.println(string);
    }

    public void onePrimitiveIntArgVoidMethod(int i) {
        System.out.println(i);
    }

    public void multipleArgsMethod(long l, int i, double d, float f, short s, char c, boolean bool, byte b, Object o) {
        System.out.println("" + l + i + d + f + s + c + bool + b + o);
    }

    public List methodReturningList() {
        return privateMethodWithAnnotation(Collections.emptySet());
    }

    public BigDecimal methodReturningBigDecimal() {
        return ZERO;
    }

    public int methodReturningPrimitiveInt() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    private List privateMethodWithAnnotation(Set set) {
        return new ArrayList(set);
    }

    private List<String> privateMethodWithGenerics(Set<String> set) {
        return new ArrayList<String>(set);
    }

    private void overloadedMethod(int i) {

    }

    private void overloadedMethod(String str) {

    }

}
