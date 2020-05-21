import java.util.*;

/**
 * uses the merge sort algorithm to take an unsorted data set and sor it in an efficient manner.
 *
 * @author Deven Ronquillo
 * @version 4/14/2017
 */
public class MergeSorter implements Sorter {

    /**
     * @inheritDoc
     * @return returns one to denote the end of operation.
     */
    public <E extends Comparable<E>> int sort(List<E> data) {

        List<E> sortedList = mergesort(data);

        for(int i = 0; i < sortedList.size(); i++){

            data.set(i, sortedList.get(i));
        }

        return 1;
    }

    /**
     * @inheritDoc
     * @return returns one to denote the end of operation
     */
    public <E> int sort(List<E> data, Comparator<E> comparator) {

        List<E> sortedList = mergesort(data, comparator);

        for(int i = 0; i < sortedList.size(); i++){

            data.set(i, sortedList.get(i));
        }

        return 1;
    }

    /**
     * Starts the process of sorting by breaking bown the data into smaller sets then sorts
     * and merges the data.
     *
     * @param data List of any type containing data needing to be sorted.
     * @param <E>   Generic type for the data.
     * @return a list containing the sorted data.
     */
    private <E extends Comparable<E>> List<E> mergesort(List<E> data) {

        if (data.size() <= 1) {//base case

            return data;
        }
        else {

            List<E> left = new ArrayList<E>();
            List<E> right = new ArrayList<E>();

            int middle = data.size() / 2;//finds middle

            for (int i = 0; i < middle; i++) {//populates left

                left.add(data.get(i));
            }

            for (int i = middle; i < data.size(); i++) {//populates right

                right.add(data.get(i));
            }

            return merge(mergesort(left), mergesort(right));//loops until data <=1, merges, returns
        }
    }

    /**
     * sorts and merges two set of data.
     *
     * @param left the left set of a once whole data set.
     * @param right the right set of a once whole data set.
     * @param <E> the generic type of the original data set.
     * @return a sorted set of data.
     */
    private <E extends Comparable<E>> List<E> merge(List<E> left, List<E> right) {

        List<E> sortedList = new ArrayList<E>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex + 1 <= left.size() || rightIndex + 1 <= right.size()) {

            if (leftIndex + 1 <= left.size() && rightIndex + 1 <= right.size()) {//compares data if both sides are populated

                if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0.0) {

                    sortedList.add(left.get(leftIndex));

                    leftIndex++;

                }
                else {

                    sortedList.add(right.get(rightIndex));

                    rightIndex++;
                }
            }
            else if (leftIndex + 1 <= left.size()) {//moves left if right is empty

                sortedList.add(left.get(leftIndex));

                leftIndex++;

            }
            else if (rightIndex + 1 <= right.size()) {//moves right if left is empty

                sortedList.add(right.get(rightIndex));

                rightIndex++;
            }
        }

        return sortedList;
    }

    /**
     * Starts the process of sorting by breaking bown the data into smaller sets then sorts
     * and merges the data with the help of a comparator.
     *
     * @param data List of any type containing data needing to be sorted.
     * @param <E>   Generic type for the data.
     * @return a list containing the sorted data.
     */
    private <E> List<E> mergesort(List<E> data, Comparator<E> comparator) {

        if (data.size() <= 1) {//base case

            return data;
        }
        else {

            List<E> left = new ArrayList<E>();
            List<E> right = new ArrayList<E>();

            int middle = data.size() / 2;//finds middle

            for (int i = 0; i < middle; i++) {//populates left

                left.add(data.get(i));
            }

            for (int i = middle; i < data.size(); i++) {//populates right

                right.add(data.get(i));
            }

            return merge(mergesort(left, comparator), mergesort(right, comparator), comparator);//loops until data <=1, merges, returns
        }
    }

    /**
     * sorts and merges two set of data via comparator.
     *
     * @param left the left set of a once whole data set.
     * @param right the right set of a once whole data set.
     * @param <E> the generic type of the original data set.
     * @return a sorted set of data.
     */
    private <E> List<E> merge(List<E> left, List<E> right, Comparator<E> comparator) {

        List<E> sortedList = new ArrayList<E>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex + 1 <= left.size() || rightIndex + 1 <= right.size()) {

            if (leftIndex + 1 <= left.size() && rightIndex + 1 <= right.size()) {//compares data if both sides are populated

                if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0.0) {

                    sortedList.add(left.get(leftIndex));

                    leftIndex++;

                }
                else {

                    sortedList.add(right.get(rightIndex));

                    rightIndex++;
                }
            }
            else if (leftIndex + 1 <= left.size()) {//moves left if right is empty

                sortedList.add(left.get(leftIndex));

                leftIndex++;

            }
            else if (rightIndex + 1 <= right.size()) {//moves right if left is empty

                sortedList.add(right.get(rightIndex));

                rightIndex++;
            }
        }

        return sortedList;
    }
}
