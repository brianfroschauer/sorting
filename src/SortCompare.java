import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * User: brianfroschauer
 * Date: 15/12/2017
 */
public class SortCompare {

    public void compare(SorterType s1, SorterType s2, int N, int T) {

        System.out.println("SortCompare: " + s1 + " & " + s2);
        final long t1 = timeRandomInput(s1, N, T);
        final long t2 = timeRandomInput(s2, N, T);

        final long t3 = timeAscendingInput(s1, N, T);
        final long t4 = timeAscendingInput(s1, N, T);

        final long t5 = timeDescendingInput(s1, N, T);
        final long t6 = timeDescendingInput(s1, N, T);

        final float delta1 = Math.abs(t2 - t1);
        final float delta2 = Math.abs(t4 - t3);
        final float delta3 = Math.abs(t6 - t5);

        System.out.println("\nFor " + N + " Random Integers: ");
        if   (t1 < t2) System.out.println("\t" + s1.toString() + " is " + delta1 + " milliseconds faster than " + s2.toString());
        else           System.out.println("\t" + s2.toString() + " is " + delta1 + " milliseconds faster than " + s1.toString());

        System.out.println("\nFor " + N + " Ascending Integers: ");
        if   (t1 < t2) System.out.println("\t" + s1.toString() + " is " + delta2 + " milliseconds faster than " + s2.toString());
        else           System.out.println("\t" + s2.toString() + " is " + delta2 + " milliseconds faster than " + s1.toString());

        System.out.println("\nFor " + N + " Descending Integers: ");
        if   (t1 < t2) System.out.println("\t" + s1.toString() + " is " + delta3 + " milliseconds faster than " + s2.toString());
        else           System.out.println("\t" + s2.toString() + " is " + delta3 + " milliseconds faster than " + s1.toString());
    }

    private <T> long time(SorterType sorter, Comparator<T> comparator, List<T> list) {
        final long start = System.nanoTime();
        if (sorter.equals(SorterType.INSERTION))             new InsertionSorter().sort(comparator, list);
        if (sorter.equals(SorterType.SELECTION))             new SelectionSorter().sort(comparator, list);
        if (sorter.equals(SorterType.BUBBLE))                new BubbleSorter().sort(comparator, list);
        if (sorter.equals(SorterType.MERGE))                 new MergeTopDownSorter().sort(comparator, list);
        if (sorter.equals(SorterType.MERGE_BOTTOM_UP))       new MergeBottomUpSorter().sort(comparator, list);
        if (sorter.equals(SorterType.QUICK))                 new QuickSorter().sort(comparator, list);
        if (sorter.equals(SorterType.QUICK_CUT))             new QuickCutSorter().sort(comparator, list);
        if (sorter.equals(SorterType.QUICK_NON_RECURSIVE))   new QuickNonRecursiveSorter().sort(comparator, list);
        if (sorter.equals(SorterType.QUICK_MED_OF_THREE))    new QuickMedianOfThreeSorter().sort(comparator, list);
        if (sorter.equals(SorterType.QUICK_THREE_PARTITION)) new QuickThreeWayPartitionSorter().sort(comparator, list);
        if (sorter.equals(SorterType.SHELL))                 new ShellSorter().sort(comparator, list);
        if (sorter.equals(SorterType.H))                     new HSorter().sort(comparator, list);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private long timeRandomInput(SorterType sorter, int N, int T) {
        long total = 0;
        final List<Integer> list = new ArrayList<>(N);
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                list.add(new Random().nextInt(N));
            total += time(sorter, Comparator.comparing(t1 -> t1), list);
        }
        return total;
    }

    private long timeAscendingInput(SorterType sorter, int N, int T) {
        long total = 0;
        final List<Integer> list = new ArrayList<>(N);
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                list.add(i);
            total += time(sorter, Comparator.comparing(t1 -> t1), list);
        }
        return total;
    }

    private long timeDescendingInput(SorterType sorter, int N, int T) {
        long total = 0;
        final List<Integer> list = new ArrayList<>(N);
        for (int t = 0; t < T; t++) {
            for (int i = N; i > 0; i--)
                list.add(i);
            total += time(sorter, Comparator.comparing(t1 -> t1), list);
        }
        return total;
    }
}
