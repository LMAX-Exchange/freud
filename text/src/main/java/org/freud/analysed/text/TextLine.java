package org.freud.analysed.text;

public final class TextLine {

    private final String line;
    private final int lineNumber;

    public TextLine(final String line, final int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "TextLine{" +
                "lineNumber=" + lineNumber +
                ", line='" + line + '\'' +
                '}';
    }
}
