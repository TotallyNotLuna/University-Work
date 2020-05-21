import java.util.Comparator;
import java.util.List;

/**
 * uses the binary searching algorithm to find a specified value.
 * continuously divides the data in have and selectively compares the key, requires that the list be sorted.
 *
 * @author Deven Ronquillo
 * @version 4/11/2017.
 */
public class BinarySearcher implements Searcher {

    /**
     * @inheritDoc
     */

    public <E extends Comparable<E>> int[] search(List<? extends E> data, E key){

        int first = 0;
        int last = data.size() - 1;
        int index = (first + last + 1)/2;
        int count = 0;

        while(first <= last){

            //System.out.println("low: "+ first + " High: "+  last + " index: " + index);
           // System.out.println(data.get(index).compareTo(key));

            if(data.get(index).compareTo(key) > 0){//if data is lower
                //System.out.println("lessthan");
                last = index - 1;//move lower
                index = (first + last + 1)/2;//find new middle
                count++;
            }
            else if(data.get(index).compareTo(key) < 0){//if data is higher
                //System.out.println("greaterthan");

                first = index + 1;//move higher
                index = (first + last + 1)/2;//find new middle
                count++;
            }
            else if(data.get(index).compareTo(key) == 0){
                //System.out.println("equal");

                return new int[]{index, count };
            }
            else{

                return new int[]{-1, count};
            }
        }

        return  new int[] {-1, 0};
    }


    /**
     *@inheritDoc
     *
     * comments from above code may be applied here
     */
    public <E> int[] search( List<? extends E> data, E key, Comparator<E> comparator){

        int first = 0;
        int last = data.size() - 1;
        int index = (first + last + 1)/2;
        int count = 0;

        while(first <= last){

            //System.out.println("low: "+ first + " High: "+  last + " index: " + index);
            // System.out.println(data.get(index).compareTo(key));

            if(comparator.compare(data.get(index), key) > 0){
                //System.out.println("lessthan");
                last = index - 1;
                index = (first + last + 1)/2;
                count++;
            }
            else if(comparator.compare(data.get(index), key) < 0){
                //System.out.println("greaterthan");

                first = index + 1;
                index = (first + last + 1)/2;
                count++;
            }
            else if(comparator.compare(data.get(index), key) == 0){
                //System.out.println("equal");

                return new int[]{index, count };
            }
            else{

                return new int[]{-1, count};
            }
        }

        return  new int[] {-1, 0};
    }
}
