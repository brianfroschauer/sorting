import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 04/09/17
 */
class HSorter extends AbstractSorter {

    HSorter() {
        super(SorterType.H);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size(), 1);
    }

    /**
     * Move elements more than one position at a time to "h-sort".
     * An h-sorted file has the property that taking a subset every h elements, results in an ordered file.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r, int h) {
        for (int i = l+h; i < r; i++)
            for (int j = i; j >= h && greater(comparator, list, j-h, j); j -= h)
                swap(list, j-h, j);
    }
}
