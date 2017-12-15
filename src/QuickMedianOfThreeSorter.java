import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 13/09/17
 */
class QuickMedianOfThreeSorter extends AbstractQuickSorter {

    private final static int M = 10; // cut-off value
    private final InsertionSorter insertionSorter;

    QuickMedianOfThreeSorter() {
        super(SorterType.QUICK_MED_OF_THREE);
        insertionSorter = new InsertionSorter();
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * Take three samples from the unknown file and use them to estimate the value of the pivot.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        if (r-l <= M) {
            insertionSorter.sort(comparator, list, l, r+1);
            return;
        }
        swap(list, (l+r) >>> 1, r-1); // only used for sorted lists
        if (greater(comparator, list, l, r-1)) swap(list, l, r-1);
        if (greater(comparator, list, l, r))      swap(list, l, r);
        if (greater(comparator, list, r-1, r)) swap(list, r-1, r);
        final int i = partition(comparator, list, l+1, r-1);
        sort(comparator, list, l, i-1);
        sort(comparator, list, i+1, r);
    }
}
