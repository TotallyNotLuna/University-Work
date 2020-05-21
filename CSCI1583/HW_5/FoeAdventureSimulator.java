/*

Adventure Mode for Foe

@author Deven Ronquillo
@timestamp oct 27 2016


*/



import java.util.Scanner;

public class FoeAdventureSimulator{
	
	private static Dungeon ExcavationSiteDungeon = new Dungeon();
	private static Room currentRoom = ExcavationSiteDungeon.getRoom0();
	
	private static boolean quit = false;
	
	private static Scanner input = new Scanner(System.in);
	private static String userChoice;
	
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
			
			System.out.println("\nYou enter the " + currentRoom.getDesc() + ".\n");
			
			System.out.println("Where would you like to go next?\n");
			
			navigationPrintOptions();
			navigationHandler();		
			
		}//end while
		
		
	}//end method navigationMain
	
	public static void navigationPrintOptions(){
		
		System.out.println(currentRoom.getExits());
		
		System.out.print("Enter Q to quit");
		
	}//end method navigationPrintOptions
	
	public static void navigationHandler(){
		
		boolean choiceChosen = false;
		
		while(!choiceChosen){
		
			System.out.print("\n\nChoice: ");
			
			userChoice = input.next();
		
			if(userChoice.equals("W")){
			
				if(currentRoom.getNorth() != null){
				
					currentRoom = currentRoom.getNorth();
					choiceChosen = true;
				
				}//end if
				else{
			
					System.out.println("This is an invalid choice, please try again.");
			
				}//end else					
			}//end if
			else if(userChoice.equals("D")){
			
				if(currentRoom.getEast() != null){
				
					currentRoom = currentRoom.getEast();
					choiceChosen = true;
				
				}//end if
				else{
			
					System.out.println("This is an invalid choice, please try again.");
			
				}//end else	
			}//end else if
			else if(userChoice.equals("S")){
			
				if(currentRoom.getSouth() != null){
				
					currentRoom = currentRoom.getSouth();
					choiceChosen = true;
				
				}//end if
				else{
			
					System.out.println("This is an invalid choice, please try again.");
			
				}//end else	
			}//end else if
			else if(userChoice.equals("A")){
			
				if(currentRoom.getWest() != null){
				
					currentRoom = currentRoom.getWest();
					choiceChosen = true;
				
				}//end if	
				else{
			
					System.out.println("This is an invalid choice, please try again.");
			
				}//end else	
			}//end else if
			else if(userChoice.equals("Q")){
				
				quit = true;
				choiceChosen = true;
				
			}
			else{
			
				System.out.println("This is an invalid choice, please try again.");
			
			}//end else	
				
		}//end while
		
	}//end method navigationHandler
	
}//class end