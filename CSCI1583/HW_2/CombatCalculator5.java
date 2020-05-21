/*

CombateCalculator5 iteration 5

@author Deven Ronquillo
@version sept 5 2016

*/

//IMPORTS

import java.util.Scanner;

public class CombatCalculator5{

	public static void main(String[] args){

		//INPUT VARS

		int menuChoice;
		boolean validChoice = false;

		//OBJECTS

		Scanner keyBoardIn = new Scanner(System.in);//Keyboard input
		//MONSTER VARS

		String monsterName = "goblin"; //Declare variable for monster's name and initialize it to goblin
		int monsterHealth = 100; //Declare variable for monster's health and initialize it 100
		int monsterAttackPower = 15; //Declare variable for monster's attack power and initialize it to 15

		//HERO VARS

		int heroHealth = 100; //Declare variable for Hero's health and initialize it to 100
		int heroAttackPower = 12; //Declare variable for Hero's attack power and initialize it to 12
		int heroMagicPower = 0; //Declare variable for Hero's magic power and initialize it to 0

		//REPORT STATS

		System.out.println("you're fighting a " + monsterName + "!");//Print the Monster’s name
		System.out.println("The " + monsterName + "'s HP: " + monsterHealth);//Print the Monster’s health
		
		System.out.println("Your HP: " + heroHealth);//Print the Player’s health
		System.out.println("your MP: " + heroMagicPower);//Print the Player’s magic points

		//MENU PROMPT

		while(!validChoice){

			System.out.println("\nWhat would you like to do?");

			System.out.println("\nEnter 1 for Sword Attack");//Print option 1: Sword Attack
			System.out.println("Enter 2 to Cast Spell");//Print option 2: Cast Spell
			System.out.println("Enter 3 to Charge Mana");//Print option 3: Charge Mana
			System.out.println("Enter 4 to Run Away");//Print option 4: Run Away
		
			System.out.print("\nChoice: ");//Prompt player for action 

			menuChoice = keyBoardIn.nextInt();//Stores option

			if(menuChoice == 1){//Option 1

				monsterHealth = monsterHealth - heroAttackPower;//Calculate damage & update monster health using subtraction

				System.out.println("\n You strike the " + monsterName + " with your sword for " + heroAttackPower + " damage!");

				validChoice = true;

			}//if menuChoice == 1 end

			else if(menuChoice == 2){//Option 2

				monsterHealth = monsterHealth / 2;//Calculate damage & update monster health using division

				System.out.println("\nYou cast the weaken spell on the " + monsterName + "!");

				validChoice = true;

			}//else if menuchoice == 2 end

			else if(menuChoice == 3){//Option 3

				heroMagicPower = heroMagicPower + 1;//Increment magic points and update players magic using addition

				System.out.println("\nYou focus and charge your magic!");

				validChoice = true;

			}//else if menuChoice == 3 end

			else if(menuChoice == 4){//option 4

				System.out.println("\nYou run away!");

				validChoice = true;

			}//else if menuChoice == 4 end

			else{//Option invalid

				System.out.println("\nInvalid command, please try another.");

				validChoice = false;

			}// else if not 1,2,3,4 end

		}//while != validChoice end

	}//main end

}//class CombatCalculator5 end
