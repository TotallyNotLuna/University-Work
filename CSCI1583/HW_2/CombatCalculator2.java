/*

CombateCalculator1 iteration 2

@author Deven Ronquillo
@version sept 2 2016

*/

public class CombatCalculator2{

	public static void main(String[] args){

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



	}//main end

}//class CombatCalculator2 end
