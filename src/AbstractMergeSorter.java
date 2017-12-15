import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 14/09/17
 */
abstract class AbstractMergeSorter extends AbstractSorter {

    AbstractMergeSorter(@NotNull SorterType type) {
        super(type);
    }

    /**
     * Merger without using sentinels by copying the second array into the
     * auxiliary array aux in reverse order back to back with the first.
     * This implementation copies the second array in inverted form avoiding the end-of-array tests.
     *
     * @param comparator to be used for sorting
     * @param list to be merged
     * @param l left index of the list
     * @param m middle index of the list
     * @param r right index of the list
     */
    <T> void merge(Comparator<T> comparator, List<T> list, int l, int m, int r) {
        final List<T> aux = new ArrayList<>();
        for (int i = 0; i <= m; i++) copy(list, aux, i, i, true);
        for (int j = r; j > m; j--)  copy(list, aux, j, j, true);
        for (int k = l, i = l, j = r; k <= r; k++)
            if (greater(comparator, aux, i, j))
                 copy(aux, list, j--, k, false);
            else copy(aux, list, i++, k, false);
    }
}
