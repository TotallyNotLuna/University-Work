import java.util.Comparator;
import java.util.List;

/**
 * Uses the Selection sort algorithm to sort a given data set.
 * compares all the things, pretty lame.
 *
 * @author Deven Ronquillo
 * @version 4/12/2017.
 */
public class SelectionSorter implements Sorter{

    /**
     * @inheritDoc
     *
     * @param data The comparable list to be sorted.
     * @param <E>  The generic type param.
     * @return Returns an int of one to note that the data is sorted.
     */

    public <E extends Comparable<E>> int sort(List<E> data){

        for(int index = 0; index < data.size() - 1; index++){//runs through the data

            int smallest = index;

            for(int nextIndex = index + 1; nextIndex < data.size(); nextIndex++){//runs through the data for each ellement

                if(data.get(nextIndex).compareTo(data.get(smallest)) < 0){//compares data

                    smallest = nextIndex;
                }
            }

            E temp = data.get(index);//swaps data
            data.set(index, data.get(smallest));
            data.set(smallest, temp);
        }

        return 1;
    }

    /**
     * @inheritDoc
     */

    public <E> int sort(List<E> data, Comparator<E> comparator){


        for(int index = 0; index < data.size() - 1; index++){//runs through the data

            int smallest = index;

            for(int nextIndex = index + 1; nextIndex < data.size(); nextIndex++){// runs through the data for each ellement

                if(comparator.compare(data.get(nextIndex), data.get(smallest)) < 0){//compares data

                    smallest = nextIndex;
                }
            }

            E temp = data.get(index);//swaps data
            data.set(index, data.get(smallest));
            data.set(smallest, temp);
        }

        return 1;
    }
}
