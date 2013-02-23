package ClassObjectExamples;

public final class ClassWithEqualsAndHashCode {

    private int i;

    public ClassWithEqualsAndHashCode(final int i) {
        this.i = i;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ClassWithEqualsAndHashCode that = (ClassWithEqualsAndHashCode) o;

        if (i != that.i) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return i;
    }
}
