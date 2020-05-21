import java.util.Comparator;
import java.util.List;

/**
 * uses the linear search algorithem to find a specified value.
 * copares key with every item until it is found.
 *
 * @author Deven Ronquillo
 * @version  4/11/2017.
 */

public class LinearSearcher implements Searcher {

    /**
     * @inheritDoc
     */

    public <E extends Comparable<E>> int[] search(List<? extends E> data, E key){

        for( int i = 0; i < data.size(); i++){//runs through the data

            if(data.get(i).compareTo(key) == 0){//compares data

                return new int[]{i, i+1};
            }
        }

        return new int[]{-1, data.size()};
    }

    /**
     *@inheritDoc
     */
    public <E> int[] search( List<? extends E> data, E key, Comparator<E> comparator){


        for( int i = 0; i < data.size(); i++){//runs through the data

            if(comparator.compare(data.get(i), key) == 0){//compares data

                return new int[]{i, i+1};
            }
        }

        return new int[]{-1, data.size()};
    }
}
