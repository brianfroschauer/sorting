import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 04/09/17
 */
class QuickSorter extends AbstractQuickSorter {

    QuickSorter() {
        super(SorterType.QUICK);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * It works by partitioning the list into two parts, then sorting the parts independently.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        final int i = partition(comparator, list, l, r);
        sort(comparator, list, l, i-1);
        sort(comparator, list, i+1, r);
    }
}