import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that models a list of grocery items.
 *
 * Hint: Use ArrayList to do all the heavy lifting for storing your items.
 *
 * @author deven ronquillo
 * @version 18/2/17
 *
 * Note: since the homework documentation didn't state the passing of junit as a requirement I wrote the formatting
 * block into the add method since I felt the class shouldn't store and save garbage. This in my opinion is a better choice
 * rather than fixing the list at output.
 */
public class GroceryList implements Serializable {

    private ArrayList<String> items;

    /**
     * Constructs a new GroceryList object that is initially empty.
     */
    public GroceryList(){

        items = new ArrayList<String>();
    }

    /**
     * Returns the number of items currently in the grocery list.
     *
     * @return The number of items in the list.
     */
    public int getItemCount(){

        return items.size();
    }

    /**
     * If the given item String is not in the list yet (ignoring capitalization
     * and leading/trailing whitespace), appends the item to the end
     * of the list. Otherwise, does nothing. A GroceryList should be able
     * to hold as many unique item names as the user desires. If the item
     * contains no actual text other than whitespace, this should not be added.
     *
     * @param item The item to add.
     */
    public void addItem(final String item){

        if(item.equals("")){

            return;
        }
        else if(item.matches("([A-Z]{1}[a-z]*\\s?)+") && !stringMatchesArray(item, items)){

            items.add(item);
        }
        else{

            StringBuilder finalizedInput = new StringBuilder("");

            String[] deconstructedItem = item.split("\\s+");

            for(String strParts : deconstructedItem){

                if(!strParts.equals("")) {

                    char[] charParts = strParts.trim().toCharArray();

                    charParts[0] = Character.toUpperCase(charParts[0]);

                    strParts = new String(charParts);

                    finalizedInput.append(strParts + " ");
                }
            }

            if(!stringMatchesArray(finalizedInput.toString().trim(), items)) {

                items.add(finalizedInput.toString().trim());
            }
            else{

                return;
            }
        }
    }

    /**
     * Removes the item at the specified index. If the index specified is
     * >= this.getItemCount(), an IllegalArgumentException should be thrown.
     * After removal of an item, the index of all items past the specified index
     * should be decremented. E.g.:
     *
     * Before:
     * 0 => Eggs
     * 1 => Milk
     * 2 => Spinach
     *
     * list.removeItemAtIndex(1);
     *
     * After:
     * 0 => Eggs
     * 1 => Spinach
     *
     * @param index The index (zero-based) to remove.
     */
    public void removeItemAtIndex(final int index) throws IndexOutOfBoundsException{

        try{

            items.remove(index-1);
        }
        catch(IllegalArgumentException e){

            System.out.println("Illegal argument, wrond data type.");
        }
    }

    /**
     * Returns the item String at the specified index. If the index specified
     * is >= this.getItemCount(), an IllegalArgumentException should be thrown. The
     * String returned should be given in "canonical" form", that is, with no leading/trailing
     * whitespace and the first letter of each individual word capitalized, regardless of
     * how the item was added initially. E.g.:
     *
     * "eggs" => "Eggs"
     * "toilet paper" => "Toilet Paper"
     * "MILK" => "Milk"
     * "  coffee " => "Coffee"
     *
     * @param index The index (zero-based) to fetch.
     * @return The grocery item text at the given index, in the canonical form specified above.
     */
    public String getItemAtIndex(final int index){

        try{

            return items.get(index);
        }
        catch (IllegalArgumentException e){

            return "Index out of bounds.";
        }
    }

    /**
     * @{inheritDoc}
     *
     * Returns a String representation of this list. Should use the StringBuilder class to build
     * up the result. A representation of a GroceryList is a human-readable series of lines with
     * a line number (1-based), followed by a period and space (". "), followed by the item. There
     * should be no trailing newline. If the list is empty, the words "(Empty List)" should be returned.
     *
     * E.g. for GroceryList {0 => "Eggs", 1 => "Bacon", 2 => "Bread"}, it should return:
     *
     * "1. Eggs
     * 2. Bacon
     * 3. Bread"
     */
    @Override
    public String toString(){

        if(items.isEmpty()){

            return "(Empty List)";
        }
        else{

            StringBuilder itemOutputBuilder = new StringBuilder("");

            for(int i = 0; i < items.size(); i++){

                itemOutputBuilder.append((i+1) + ". " + items.get(i) + ".\n");
            }

            return itemOutputBuilder.toString();
        }
    }

    /** method used to compare a string to an arraylist in search of a match.
     *
     * @param str string to be compared.
     * @param strAry array to compare to.
     * @return returns true if a match is found, else returns false.
     */

    private boolean stringMatchesArray(String str, ArrayList<String> strAry){

        for(String strInAry : strAry){

            if(str.equals(strInAry)){

                return true;
            }
        }

        return false;
    }

}
