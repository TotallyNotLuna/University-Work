/*

FoeBattleSimulator Iteration 1

@author Deven Ronquillo
@version sept 21 2016

info

Some styling relies on the default 80 character dos box

the loop within the main method is currently set to not run

*/

//IMPORTS

import java.util.Scanner;

public class FoeBattleSimulator2{
	
	//GLOBALS VARS
	private static int heroHealth = 0;//Declare variable for Hero's health
	private static int heroAttackPower = 0;//Declare variable for Hero's attack power
	private static int heroMagicPower = 0;//Declare variable for Hero's magic power
	
	private static String[] monsterName = {"Radroach", "Feral Ghoul", "Manticore", "Hydra"}; //Declare string[] variable for monster's name and initialize it
	private static int monsterNameIndex;//stores generated index number for monster
	private static int monsterHealth; //Declare variable for monster's health
	private static int monsterAttackPower; //Declare variable for monster's attack power
	private static int monsterExperianceValue; //declare var for possible experiance gain from the monster
	
	//GLOBAL OBJECTS

	private static Scanner keyBoardIn = new Scanner(System.in);//Keyboard input

	public static void main(String[] args){
		
		//heroCreationKit();//begins hero creation kit
		
		monsterCreationKit();//randomly generates a monster
		
/*
		//INPUT VARS

		int menuChoice;
		boolean contLoop = false;	

			//MENU PROMPT

		while(contLoop){

			//REPORT STATS & MENU
	
			System.out.println("you're fighting a " + monsterName + "!");//Print the Monster’s name
			System.out.println("The " + monsterName + "'s HP: " + monsterHealth);//Print the Monster’s health
		
			System.out.println("Your HP: " + heroHealth);//Print the Player’s health
			System.out.println("your MP: " + heroMagicPower);//Print the Player’s magic points



			System.out.println("\nWhat would you like to do?");

			System.out.println("\nEnter 1 for Sword Attack");//Print option 1: Sword Attack
			System.out.println("Enter 2 to Cast Spell");//Print option 2: Cast Spell
			System.out.println("Enter 3 to Charge Mana");//Print option 3: Charge Mana
			System.out.println("Enter 4 to Run Away");//Print option 4: Run Away
		
			System.out.print("\nChoice: ");//Prompt player for action 

			menuChoice = keyBoardIn.nextInt();//Stores option

			if(menuChoice == 1){//Option 1

				monsterHealth = monsterHealth - heroAttackPower;//Calculate damage & update monster health using subtraction

				System.out.println("\n You strike the " + monsterName + " with your sword for " + heroAttackPower + " damage!\n");


			}//if menuChoice == 1 end

			else if(menuChoice == 2){//Option 2

				if(heroMagicPower >= 3){

					monsterHealth = monsterHealth / 2;//Calculate damage & update monster health using division

					heroMagicPower = heroMagicPower - 3;//Reduce player's mana points by the spell cost using subtraction

					System.out.println("\nYou cast the weaken spell on the " + monsterName + "!\n");

				}//if heroMagicPower >= 3 end

				else{

					System.out.println("\nYou don't have enough mana\n");

				}//else not enough mana end

			}//else if menuchoice == 2 end

			else if(menuChoice == 3){//Option 3

				heroMagicPower = heroMagicPower + 1;//Increment magic points and update players magic using addition

				System.out.println("\nYou focus and charge your magic!\n");


			}//else if menuChoice == 3 end

			else if(menuChoice == 4){//option 4

				System.out.println("\nYou run away!");

				contLoop = false;//Stop combat loop by setting control variable to false

			}//else if menuChoice == 4 end

			else{//Option invalid

				System.out.println("\nYou fumble around missing your opportunity to strike (Invalid Command).\n");


			}// else if not 1,2,3,4 end

			if(monsterHealth > 0){

				System.out.println("\nThe " + monsterName + " attacks you for " + monsterAttackPower + " damage!\n");

				heroHealth = heroHealth - monsterAttackPower;//calculates hero health after monster attack

			}//if monterhealth > 0 end

			if(monsterHealth <= 0 || heroHealth <=0){

				if(monsterHealth <= 0){

					System.out.println("You defeated the " + monsterName + "!");

				}//if victory end

				else{

					System.out.println("You were defeated, better luck next time hero.");

				}//else defeat end

				contLoop = false;//Stop combat loop by setting control variable to false

			}//if health <= 0 end

		}//while != validChoice end
		
*/

	}//main end
	
	public static void centerText(String textToCenter, String sideType){
		
		String sideSpace = "";
		
		for(int spaces = 0; spaces <= (78 - textToCenter.length()) / 2; spaces++){
			
			sideSpace = sideSpace + sideType;
			
		}//end for loop
			
		System.out.println(sideSpace + textToCenter + sideSpace);
			
	}//method centerText end
	
	public static void heroCreationKit(){
		
		int points = 20;
		int pointSelection;
		
		centerText("Welcome to the Hero Creation Kit", "-");//centrers welcome text
		
		while(points > 0){//loop for attribute selection
		
			System.out.println("\n---current Stats---");
			System.out.println("\nMax Health: " + heroHealth);
			System.out.println("Attack Power: " + heroAttackPower);
			System.out.println("Magic Power: " + heroMagicPower);
		
			System.out.println("\nyou have " + points + " to spend:");
		
			System.out.println("\nEnter 1 for +10 Health");
			System.out.println("Enter 2 for +1 Attack Power");
			System.out.println("Enter 3 for +3 Magic Power");
		
			System.out.print("\nEnter choice: ");
			
			pointSelection = keyBoardIn.nextInt();
			
			if(pointSelection == 1){//choice when user selects 1
				
				heroHealth = heroHealth + 10;
				points--;
				
			}//end if 1
			
			else if(pointSelection == 2){//choice when user selects 2
				
				heroAttackPower = heroAttackPower + 1;
				points--;
				
			}//end if 2
			
			else if(pointSelection == 3){//choice when user selects 3
				
				heroMagicPower = heroMagicPower + 3;
				points--;
				
			}//end if 3
			
			else{//when user choice is invalid
				
				System.out.println("\nInvalid attribute, please try again.");
				
			}//end if not 1,2,3
			
		}//end while for point selection
		
		System.out.println("\nYou're now finished dedicating your attributes, prepare for battle!");
		
	}//method heroCreationKit end

	public static void monsterCreationKit(){
		
		monsterNameIndex = randomRange(0 , 3);
		
		switch(monsterNameIndex){
			
			case 0://radroach
	
				monsterHealth = 75 + randomRange(0, 24);
				monsterAttackPower = 8 + randomRange(0, 4);
				monsterExperianceValue = 1;
				
				break;
				
			case 1://feral ghoul
			
				monsterHealth = 100 + randomRange(0, 24);
				monsterAttackPower = 12 + randomRange(0, 4);
				monsterExperianceValue = 3;
				
				break;
				
			case 2:
			
				monsterHealth = 150 + randomRange(0, 49);
				monsterAttackPower = 15 + randomRange(0, 4);
				monsterExperianceValue = 5;
				
				break;
				
			case 3:
			
				monsterHealth = 200 + randomRange(0, 50);
				monsterAttackPower = 20 + randomRange(0, 5);
				monsterExperianceValue = 15;
				
				break;
			
			
		}//end switch statement
		
		System.out.println("You have encountered a " + monsterName[monsterNameIndex] + "!");
		
	}
	
	public static int randomRange(int min, int max){
		
		return (int)((Math.random() * ((max - min) + 1)) + min);
		
	}
}//class FoeBattleSimulator end
