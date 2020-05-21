/*

Adventure Mode for Foe

@author Deven Ronquillo
@timestamp oct 12 2016


*/



import java.util.Scanner;

public class FoeAdventureSimulator{
	
	private static boolean quit = false;
	
	private static int currentRoom = 0;
	private static final int chambers = 12;
	private static final int directions = 4;
	
	
	private static Scanner input = new Scanner(System.in);
	private static String userChoice;
	
	
	private static String[] directionStrings = new String[] {"Enter W to go North", "Enter D to go East", "Enter S to go South", "Enter A to go West"};
	
	private static String[] chamberStrings = new String[] {"Excavation Site Entrance", "Entrance hall, its covered in dust", "Tool Storage but find nothing of value",
	"Living Quarters, the place is in disarray ", "Bunk Room, mattresses lay strewn about", "Lavatory Hall and hear the pater of running water", "Mess Hall, old preserved food still lay on the tables", "Through Hall, its empty apart from a few containers",
	"Shower Room and find the source of the pater, an old broken pipe", "Office Hall, dusty notes and book lay about the room","Overseers Office and find it in disarray with dried blood plastered to the back wall",
	"Excavation Chamber Entrance and see light pouring in from its distant end","Excavation Chamber and are greeted by half uncovered relics from days past"};
	
	private static int[][] chamberLayout = new int[][] {		
		{1,-1,-1,-1},
		{3,2,0,-1},
		{-1,-1,-1,1},
		{5,6,1,4},
		{-1,3,-1,-1},
		{7,-1,3,8},
		{-1,-1,-1,3},
		{-1,-1,5,9},
		{-1,5,-1,-1},
		{10,7,-1,11},
		{-1,-1,9,-1},
		{12,9,-1,-1},
		{-1,-1,11,-1}
	};
	
	
	
	public static void main(String[] args){
		
		while(!quit){
		
			System.out.print("You aproach an old excavation site, would you like to enter Y/N? ");
		
			userChoice = input.next();
		
			if(userChoice.equals("Y")){
			
				navigationMain();
			
			}//end if
			else if(userChoice.equals("N")){
				
				
				
				quit = true;
			
			}//end else if
			else{
				
				System.out.println("\nThis is not a valid choice\n");
				
			}//end else
		}//end while
		
		
		System.out.println("\nThanks for playing!");
		
	}//main end
	
	public static void navigationMain(){
		
		while(!quit){
			
			System.out.println("\nYou enter the " + chamberStrings[currentRoom] + ".\n");
			
			System.out.println("Where would you like to go next?\n");
			
			navigationPrintOptions();
			navigationHandler();
			
			
			
			
			
			
			
			
		}
		
		
	}//end method
	
	public static void navigationPrintOptions(){
		
		for(int index = 0; index < directions; index++){
			
			if(chamberLayout[currentRoom][index] >= 0){
				
				System.out.println(directionStrings[index]);
				
			}
			
			
		}
		
		System.out.print("Enter Q to quit");
		
	}
	
	public static void navigationHandler(){
		
		boolean choiceChosen = false;
		
		while(!choiceChosen){
		
			System.out.print("\n\nChoice: ");
			
			userChoice = input.next();
		
			if(userChoice.equals("W")){
			
				if(chamberLayout[currentRoom][0] >= 0){
				
					currentRoom = chamberLayout[currentRoom][0];
					choiceChosen = true;
				
				}//end if	
			}//end if
			else if(userChoice.equals("D")){
			
				if(chamberLayout[currentRoom][1] >= 0){
				
					currentRoom = chamberLayout[currentRoom][1];
					choiceChosen = true;
				
				}//end if	
			}//end else if
			else if(userChoice.equals("S")){
			
				if(chamberLayout[currentRoom][2] >= 0){
				
					currentRoom = chamberLayout[currentRoom][2];
					choiceChosen = true;
				
				}//end if	
			}//end else if
			else if(userChoice.equals("A")){
			
				if(chamberLayout[currentRoom][3] >= 0){
				
					currentRoom = chamberLayout[currentRoom][3];
					choiceChosen = true;
				
				}//end if	
			}//end else if
			else if(userChoice.equals("Q")){
				
				quit = true;
				choiceChosen = true;
				
			}
			else{
			
				System.out.println("This is an invalid choice, please try again.");
			
			}//end esle	
		}//end while
	}//end method navigationHandler
	
}//class end