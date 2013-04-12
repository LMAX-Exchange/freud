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

    public String explicitToString() {
        return bigDecimal.toPlainString();
    }

    public void implicitToString() {
        System.out.println(bigDecimal);
    }

}
