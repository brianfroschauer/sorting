import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 17/08/17
 */
class InsertionSorter extends AbstractSorter {

    InsertionSorter() {
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size());
    }

    /**
     * It takes each element and leaves it ordered with respect to the previous ones.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        for (int i = l+1; i < r; i++)
            for (int j = i; j > l && greater(comparator, list, j-1, j); j--)
                 swap(list, j-1, j);
    }
}
