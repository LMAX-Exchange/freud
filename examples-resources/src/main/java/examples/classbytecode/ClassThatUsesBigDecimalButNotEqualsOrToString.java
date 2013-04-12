package examples.classbytecode;

import java.math.BigDecimal;

public final class ClassThatUsesBigDecimalButNotEqualsOrToString {

    private final BigDecimal bigDecimal;

    public ClassThatUsesBigDecimalButNotEqualsOrToString(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public boolean equalsThatCompareJustNumericValue(BigDecimal another) {
        return bigDecimal.compareTo(another) == 0;
    }

    public String toStringOfNumericValue() {
        return bigDecimal.toPlainString();
    }

}
