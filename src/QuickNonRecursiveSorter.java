import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * User: brianfroschauer
 * Date: 13/09/17
 */
class QuickNonRecursiveSorter extends AbstractQuickSorter {

    QuickNonRecursiveSorter() {
        super(SorterType.QUICK_NON_RECURSIVE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    /**
     * Uses an explicit stack, replacing recursive calls with stack pushes.
     *
     * @param comparator to be used for sorting
     * @param list to be sorted
     * @param l left index of the list
     * @param r right index of the list
     */
    <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        final Stack<Integer> stack = new Stack<>();
        stack.push(l);
        stack.push(r);
        while (!stack.empty()) {
            r = stack.pop();
            l = stack.pop();
            if (r <= l) continue;
            final int i = partition(comparator, list, l, r);
            // checking the sizes of the two sub-files and putting the larger of the two on the stack first.
            if (i-l > r-i) {
                stack.push(l);
                stack.push(i-1);
                stack.push(i+1);
                stack.push(r);
            } else {
                stack.push(i+1);
                stack.push(r);
                stack.push(l);
                stack.push(i-1);
            }
        }
    }
}
