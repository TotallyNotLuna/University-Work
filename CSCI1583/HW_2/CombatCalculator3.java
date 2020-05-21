/*

CombateCalculator3 iteration 3

@author Deven Ronquillo
@version sept 4 2016

*/

//IMPORTS

import java.util.Scanner;

public class CombatCalculator3{

	public static void main(String[] args){

		//INPUT VARS

		int menuChoice;


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

		System.out.println("\nWhat would you like to do?");

		System.out.println("\nEnter 1 for Sword Attack");//Print option 1: Sword Attack
		System.out.println("Enter 2 to Cast Spell");//Print option 2: Cast Spell
		System.out.println("Enter 3 to Charge Mana");//Print option 3: Charge Mana
		System.out.println("Enter 4 to Run Away");//Print option 4: Run Away
		
		System.out.print("\nChoice: ");//Prompt player for action 

		menuChoice = keyBoardIn.nextInt();//Stores option
	}//main end

}//class CombatCalculator2 end
