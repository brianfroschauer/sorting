import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 17/08/17
 */
class BubbleSorter extends AbstractSorter {

    BubbleSorter() {
        super(SorterType.BUBBLE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size());
    }

    /**
     * Order by exchanging contiguous elements, at the end of each pass each element will be in its final position.
     * Optimized implementation that stops iterating when there was no change in the last iteration.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        boolean flag = true;
        while (flag) {
            for (int i = l; i < r-1; i++) {
                flag = false;
                for (int j = r-1; j > i; j--) {
                    if (greater(comparator, list, j-1, j)) {
                        swap(list, j-1, j);
                        flag = true;
                    }
                }
            }
        }
    }
}
