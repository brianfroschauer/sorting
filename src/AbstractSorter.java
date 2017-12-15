import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * User: brianfroschauer
 * Date: 17/08/17
 */
abstract class AbstractSorter implements Sorter {

    private final SorterType sorterType;

    AbstractSorter(@NotNull SorterType sorterType) {
        this.sorterType = sorterType;
    }

    <T> boolean equals(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j) {
        return comparator.compare(list.get(i), list.get(j)) == 0;
    }

    <T> boolean greater(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j) {
        return comparator.compare(list.get(i), list.get(j)) > 0;
    }

    <T> void swap(@NotNull List<T> list, int i, int j) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    <T> void copy(@NotNull List<T> source, @NotNull List<T> target, int from, int to, boolean copyToAux) {
        if (copyToAux) target.add(source.get(from));
        else target.set(to, source.get(from));
    }

    @NotNull
    public SorterType getType() {
        return sorterType;
    }
}
