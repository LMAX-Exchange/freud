package examples.classobject;

import java.util.Comparator;

public final class StatefulComparator<T> implements Comparator<T> {

    private int i; // That's a smell - could a comparator result be decided by internal state??? really?

    @Override
    public int compare(final T o1, final T o2) {
        return i;
    }
}
