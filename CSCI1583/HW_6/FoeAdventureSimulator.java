/*

Adventure Mode for Foe

@author Deven Ronquillo
@timestamp Nov 10 2016


*/



import java.util.Scanner;

public class FoeAdventureSimulator{
	
	private static Dungeon ExcavationSiteDungeon = new Dungeon();
	private static Room currentRoom = ExcavationSiteDungeon.getRoom0();

	
	private static boolean quit = false;
	private static boolean quitBattle;
	
	private static Scanner input = new Scanner(System.in);
	private static String userChoice;
	
	
	private static Player newPlayer = new Player();
	private static Monster newMonster;
	
	
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
			
			if(randomRange(0, 2) == 0){
				
				battleMain();
				
			}
			
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
	
	public static void battleMain(){
		
		newMonster = new Monster();
		
		System.out.println("You encounter a " +newMonster.getName() + "!" );
		
		quitBattle = false;
		
		while(!quitBattle){
			
			battlePrintOptions();
			battleHandler();
		}//end if
		
	}//end battleMain Method
	
	public static void battlePrintOptions(){
		
		System.out.println("---Your stats---\n");
		System.out.println("HP: " + newPlayer.getHealth());
		System.out.println("AP: " + newPlayer.getAttackPower());
		System.out.println("Mana: " + newPlayer.getMana());
		
		System.out.println("\n---Monster Stats---\n");
		System.out.println("HP: " + newMonster.getHealth());
		System.out.println("AP: " + newMonster.getAttackPower());
		System.out.println("XP: " + newMonster.getXP());
		
		System.out.println("\nWhat would you like to do?\n");
		
		System.out.println("Enter 1 for Sword Attack");
		System.out.println("Enter 2 to Cast Spell");
		System.out.println("Enter 3 to Charge Mana");
		
		System.out.print("Choice: ");
		
	}//end battlePrintOptions method
	
	public static void battleHandler(){
		
		int userChoice = input.nextInt();//Stores option

		if(userChoice == 1){//Option 1

			newPlayer.attack(newMonster);
			
			System.out.println("\n You strike the " + newMonster.getName() + " with your sword for " + newPlayer.getAttackPower() + " damage!\n");

		}//if menuChoice == 1 end

		else if(userChoice == 2){//Option 2

			if(newPlayer.getMana() >= 2){
				
				newPlayer.castSpell(newMonster);
				
				newPlayer.updateMana();
				
				System.out.println("\nYou cast the weaken spell on the " + newMonster.getName() + "!\n");
			
			}//end if
			else{
				
				System.out.println("\nYou try to cast the spell but suffer from magical burnout (Not Enough Mana)\n");
				
			}//end else
		}//else if menuchoice == 2 end

		else if(userChoice == 3){//Option 3

			newPlayer.chargeMana();
			
			System.out.println("\nYou focus and charge your magic!\n");

		}//else if menuChoice == 3 end
		else{//Option invalid

			System.out.println("\nYou fumble around missing your opportunity to strike (Invalid Command).\n");

		}// else if not 1,2,3,4 end

		if(newMonster.getHealth() > 0 ){

			newMonster.attack(newPlayer);

		}//if monterhealth > 0 end
		
		if(newMonster.getHealth() <= 0 || newPlayer.getHealth() <=0){

			if(newMonster.getHealth() <= 0){

				System.out.println("You defeated the " + newMonster.getName() + " and earned " + newMonster.getXP() + " XP!\n");
					
				quitBattle = true;
			}//if victory end

			else{

				System.out.println("You were defeated, better luck next time hero.\n");
				
				System.out.println("Thanks for playing!");
				
				System.exit(1);
				
			}//else defeat end

		}//if health <= 0 end
			
	}//end battleHandler method
	
	public static int randomRange(int min, int max){
		
		return (int)((Math.random() * ((max - min) + 1)) + min);
		
	}//end randomRange method
	
}//end class