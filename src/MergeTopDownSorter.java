import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 14/09/17
 */
class MergeTopDownSorter extends AbstractMergeSorter {

    MergeTopDownSorter() {
        super(SorterType.MERGE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * Divide the list in half, and recursively sort the two halves, and then merge them.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        final int m = (l + r) / 2;
        sort(comparator, list, l, m); // sort left half
        sort(comparator, list, m+1, r); // sort right half
        merge(comparator, list, l, m, r);
    }
}
