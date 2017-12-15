import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 14/09/17
 */
class QuickThreeWayPartitionSorter extends AbstractQuickSorter {

    QuickThreeWayPartitionSorter() {
        super(SorterType.QUICK_THREE_PARTITION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * Partition the file into three parts: smaller than, equal to, and larger than the partitioning element.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        if (r <= l) return;
        int i = l-1, j = r, p = l-1, q = r, k;
        while(true) {
            while (greater(comparator, list, r, ++i)); // look for greater or equal than partitioning element
            while (greater(comparator, list, --j, r)) if (j == l) break; // look for lower or equal than partitioning element
            if (i >= j) break;
            swap(list, i, j);
            if (equals(comparator, list, i, r)) swap(list, ++p, i);
            if (equals(comparator, list, r, j)) swap(list, --q, j);
        }
        swap(list, i, r);
        j = i-1;
        i = i+1;
        for (k = l; k <= p; k++, j--)   swap(list, k, j);
        for (k = r-1; k >= q; k--, i++) swap(list, k, i);
        sort(comparator, list, l, j);
        sort(comparator, list, i, r);
    }
}
