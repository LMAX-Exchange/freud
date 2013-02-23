package ClassObjectExamples;

public final class ClassWithEqualsButNoHashCode {

    private int i;

    public ClassWithEqualsButNoHashCode(final int i) {
        this.i = i;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ClassWithEqualsButNoHashCode that = (ClassWithEqualsButNoHashCode) o;

        if (i != that.i) return false;

        return true;
    }
}
