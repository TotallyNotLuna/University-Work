/*

CombateCalculator6 iteration 7

@author Deven Ronquillo
@version sept 5 2016

*/

//IMPORTS

import java.util.Scanner;

public class CombatCalculator7{

	public static void main(String[] args){

		//INPUT VARS

		int menuChoice;
		boolean contLoop = true;

		//OBJECTS

		Scanner keyBoardIn = new Scanner(System.in);//Keyboard input
		//MONSTER VARS

		String monsterName = "goblin"; //Declare variable for monster's name and initialize it to goblin
		int monsterHealth = 100; //Declare variable for monster's health and initialize it 100
		int monsterAttackPower = 10; //Declare variable for monster's attack power and initialize it to 15

		//HERO VARS

		int heroHealth = 100; //Declare variable for Hero's health and initialize it to 100
		int heroAttackPower = 12; //Declare variable for Hero's attack power and initialize it to 12
		int heroMagicPower = 0; //Declare variable for Hero's magic power and initialize it to 0

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

	}//main end

}//class CombatCalculator7 end
