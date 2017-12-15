import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 17/08/17
 */
class SelectionSorter extends AbstractSorter {

    SelectionSorter() {
        super(SorterType.SELECTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size());
    }

    /**
     * Sequentially searches for the smallest element and exchanges it with the last unordered position.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        for (int i = l; i < r; i++) {
            int min = i; // index of minimal entry
            for (int j = i+1; j < r; j++)
                if (greater(comparator, list, min, j)) min = j;
            swap(list, i, min);
        }
    }
}