import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 14/09/17
 */
class MergeBottomUpSorter extends AbstractMergeSorter {

    MergeBottomUpSorter() {
        super(SorterType.MERGE_BOTTOM_UP);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * Bottom-up mergesort consists of a sequence of passes over the whole file doing m-by-m merges, doubling m on each pass.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        for (int m = 1; m <= r-l; m = m+m)
            for (int i = l; i <= r-m; i += m+m)
                merge(comparator, list, i,i+m-1, Math.min(i+m+m-1, r));
    }
}
