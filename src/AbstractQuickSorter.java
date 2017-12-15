import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 13/09/17
 */
abstract class AbstractQuickSorter extends AbstractSorter {

    AbstractQuickSorter(@NotNull SorterType type) {
        super(type);
    }

    /**
     * The partitioning process divides a file into two subfiles that can be sorted independently.
     * In this implementation, the partition item will be the rightmost item.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     * @return index of the partition item
     */
    <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        int i = l-1;
        int j = r; // partitioning item
        while (true) {
            while (greater(comparator, list, r, ++i)) if (i == r) break; // find item greater than pivot to swap
            while (greater(comparator, list, --j, r)) if (j == l) break; // find item less than pivot to swap
            if (i >= j) break; // check if pointers cross
            swap(list, i, j);
        }
        swap(list, i, r); // swap with partitioning item
        return i;
    }
}
