import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

/**A program tha utilizes GroceryList Class in order to create, edit, and store a personal GroceryList.
 *
 * @author deven ronquillo
 * @version 18/2/17
 *
 */
public class GroceryTracker {

    public static ObjectOutputStream outputList;
   public static ObjectInputStream inputList;

    public static GroceryList list;

    /**The main method which creates a ui for navigation as well as variables to utilize the GroceryList class.
     *
     * @param args path to the file to read or write to.
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int userMenuInput = 0;
        int userIntInput = -1;
        String userStringInput = "";

        Boolean operating = true;

        File newFileInput = new File(args[0]);
        Path filePath = Paths.get(args[0]);

        if(new File(filePath.toString()).exists()) {

            openInputStream(filePath.toString());
            deserilizeInputStream();
            closeInputStream();
        }
        else{

            openOutputStream(filePath.toString());
            list = new GroceryList();
            serializeOutputStream();
            closeInputStream();
        }

        while(true) {
            System.out.println("Welcome to the grocery tracker.");
            System.out.println("Current file: " + filePath);
            System.out.println("What would you like to do?");
            System.out.println("1) View list");
            System.out.println("2) Add an item");
            System.out.println("3) Remove an  item");
            System.out.println("4) Save");
            System.out.println("5) Quit");

            try {

                userMenuInput = userInput.nextInt();
            }
            catch (InputMismatchException e) {
                userInput.next();
                System.out.println("Input mismatch please try again");
            }

            if(userMenuInput == 1){

                System.out.println(list);
            }
            else if(userMenuInput == 2){

                System.out.print("Enter item to add: ");

                while(userStringInput.equals("")){

                    userStringInput = userInput.nextLine();
                    list.addItem(userStringInput);
                }
            }
            else if(userMenuInput == 3){

                System.out.print("Enter index of item to remove: ");

                while(userIntInput == -1) {

                    try {

                        userIntInput = userInput.nextInt();
                        list.removeItemAtIndex(userIntInput);
                    }
                    catch(InputMismatchException e){

                        userInput.next();
                        System.out.println("Input mismatch please try again.");
                        userIntInput = -1;
                    }
                    catch(IndexOutOfBoundsException e){
                        userInput.next();
                        System.out.println("Array index out of bounds please try again.");
                        userIntInput = -1;
                    }
                }
            }
            else if(userMenuInput == 4){

                System.out.println("Saving...");

                openOutputStream(filePath.toString());
                serializeOutputStream();
                closeOutputStream();
            }
            else if(userMenuInput == 5){

                System.out.println("Exiting...");
                System.exit(0);
            }

            System.out.println("\n\n");
            userStringInput = "";
            userIntInput = -1;
        }
    }

    /**prepares a file for data read in via input stram.
     *
     * @param path location of file.
     */
    public static void openInputStream(String path){

        try{

            inputList = new ObjectInputStream(new FileInputStream(path));
        }
        catch(NullPointerException e){

            System.out.println("Stream not properly closed.");
        }
        catch(IOException e){

            System.out.println("Error in loading file.");
        }
    }

    /**reads in data via input stream.
     *
     */
    public static void deserilizeInputStream(){

        try{

            list = (GroceryList) inputList.readObject();
        }
        catch (NullPointerException e){

            System.out.println("Invalid file path.");
        }
        catch (EOFException e){

            //alls good
        }
        catch (ClassNotFoundException e){

            System.out.println("GroceryList is n invalid class.");
        }
        catch (IOException e){

            System.out.println("Error in file read.");
        }
    }

    /**Closes a file after reading in it data via input stream.
     *
     */
    public static void closeInputStream(){

        try{

            inputList.close();
        }
        catch (IOException e){

            System.out.println("error in file closing.");
        }
    }


    /**opens a file for editing via output stream.
     *
     * @param path location of file.
     */
    public static void openOutputStream(String path){

        try{

            outputList = new ObjectOutputStream(new FileOutputStream(path));
        }
        catch(NullPointerException e){

            System.out.println("Stream not properly closed.");
        }
        catch(IOException e){

            System.out.println("Error in loading file.");
        }
    }

    /**serializes data to the file open via output stream.
     *
     */
    public static void serializeOutputStream(){

        try{

            outputList.writeObject(list);
        }
        catch (NullPointerException e){

            System.out.println("Invalid file path.");
        }
        catch (IOException e){

            System.out.println("Error in file read.");
        }
    }

    /**Closes a output stream after editing.
     *
     */
    public static void closeOutputStream(){

        try{

            outputList.close();
        }
        catch (IOException e){

            System.out.println("error in file closing.");
        }
    }
}