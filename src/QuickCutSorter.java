import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 13/09/17
 */
class QuickCutSorter extends AbstractQuickSorter {

    private final static int M = 10; // cut-off value
    private final InsertionSorter insertionSorter;

    QuickCutSorter() {
        super(SorterType.QUICK_CUT);
        insertionSorter = new InsertionSorter();
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * It uses insertion sort for subfiles smaller than the cutoff value.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        if (r-l <= M) {
            insertionSorter.sort(comparator, list, l, r + 1);
            return;
        }
        final int i = partition(comparator, list, l, r);
        sort(comparator, list, l, i-1);
        sort(comparator, list, i+1, r);
    }
}
