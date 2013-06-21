package examples.classobject;

import java.util.Comparator;

public final class StatelessComparator<T> implements Comparator<T> {

    @Override
    public int compare(final T o1, final T o2) {
        return 0;
    }
}
