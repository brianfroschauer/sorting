import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 04/09/17
 */
class ShellSorter extends AbstractSorter {

    private final HSorter hSorter;

    ShellSorter() {
        super(SorterType.SHELL);
        hSorter = new HSorter();
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size());
    }

    /**
     * H-sort the file for a decreasing sequence of h values.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        int h = 1;
        // outer-loop that changes the increment h with the sequence 1, 4, 13, 40, 121, ...
        while (h < (r-l)/3) h = 3*h+1;
        while (h > 0) {
            hSorter.sort(comparator, list, l, r, h); // h-sort the array
            h /= 3;
        }
    }
}
