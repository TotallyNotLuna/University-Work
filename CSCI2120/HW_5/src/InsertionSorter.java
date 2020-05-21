import java.util.Comparator;
import java.util.List;

/**
 * uses the insertion sort algorithm to sort a given data set.
 *
 * @author Deven Ronquillo
 * @version 4/12/2017.
 */
public class InsertionSorter implements Sorter {

    /**
     * @inheritDoc
     *
     * @param data The data to be sorted.
     * @param <E> The generic type of data to be sorted.
     * @return Returns one to confirm operation has finished.
     */

    public <E extends Comparable<E>> int sort(List<E> data){

        for(int i = 1; i < data.size(); i++){//runs through the data

            E valToMove = data.get(i);//temp val
            int locToMoveTo = i;

            while(locToMoveTo > 0 && data.get(locToMoveTo - 1).compareTo(valToMove) > 0){//compares data

                data.set(locToMoveTo, data.get(locToMoveTo - 1));// moves first data that exceeds locToMoveTo - 1 in data
                locToMoveTo--;//dec
            }

            data.set(locToMoveTo, valToMove);//moves temp val back into data
        }

       return 1;
    }

    /**
     *@inheritDoc
     *
     * comments above may apply here.
     */

    public <E> int sort(List<E> data, Comparator<E> comparator){

        for(int i = 1; i < data.size(); i++){

            E valToMove = data.get(i);
            int locToMoveTo = i;

            while(locToMoveTo > 0 && comparator.compare(data.get(locToMoveTo - 1), valToMove) > 0){

                data.set(locToMoveTo, data.get(locToMoveTo - 1));
                locToMoveTo--;
            }

            data.set(locToMoveTo, valToMove);
        }

       return 1;
    }
}
