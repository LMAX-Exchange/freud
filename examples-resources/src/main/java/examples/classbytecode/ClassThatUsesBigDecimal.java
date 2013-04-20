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
