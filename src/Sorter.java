import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

interface Sorter {

    <T> void sort(@NotNull final Comparator<T> comparator, @NotNull final List<T> list);

    @NotNull SorterType getType();
}
