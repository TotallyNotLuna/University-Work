/**
 * Created by Apera on 4/11/2017.
 */
import java.util.List;
import java.util.Comparator;
public interface Sorter {
    /**
     * Sorts the given data list, replacing the values in sorted values
     * according to their
     * natural ordering. That is, {@literal i > j ==> data.get(i) >= data.get(j)}.
     *
     **/
    <E extends Comparable<E>> int sort(List<E> data);

     /**
      *Performs the same as sort() above except using a custom Comparator
     * to determine the following order.
     * @param data The data to sort.
     * @param comparator The ordering to use.
     * @param <E> the type of elements stored in the list.
     *
     **/

     <E> int sort(List<E> data, Comparator<E> comparator); }
