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

import java.math.BigDecimal;

public final class ClassThatUsesBigDecimal {

    private final BigDecimal bigDecimal;

    public ClassThatUsesBigDecimal(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public boolean equalsThatComparesInternalValues(BigDecimal another) {
        return bigDecimal.equals(another);
    }

    public boolean ourRuleAboutHowToCompareBigDecimals(BigDecimal another) {
        return bigDecimal.compareTo(another) == 0;
    }


    public String ourRuleAboutConvertingBigDecimalToAString() {
        return bigDecimal.toPlainString();
    }


    public String explicitToString() {
        return bigDecimal.toString();
    }

    public void implicitToStringInPrintStream() {
        System.out.println(bigDecimal);
    }

    public void implicitToStringInStringBuilder() {
        System.out.println("my big decimal: " + bigDecimal);
    }

}
