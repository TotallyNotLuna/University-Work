/*

FoeBattleSimulator Iteration 3

@author Deven Ronquillo
@version sept 21 2016

info

Some styling elements relly on the dos box being atleat the default 80 characters wide
This program uses ANSI escape codes which may or may not be compatible with your terminal ex: Windows terminal


*/

//IMPORTS

import java.util.Scanner;

public class FoeBattleSimulator3{
	
	//GLOBALS VARS
	private static final String colorReset = "\u001B[0m";
	private static final String colorCyan = "\u001B[36m";
	private static final String colorGreen = "\u001B[32m";
	private static final String colorRed = "\u001B[31m";
	private static final String colorYellow = "\u001B[33m";
	
	private static int heroLevel = 1;//declares level
	private static int xpPool = 0;// declares current xp 
	private static int points = 20;//declares points for atributes
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
		
		mainMenu();//produces the main menu

	}//main end
	
	public static void centerText(String textToCenter, String sideType){
		
		String sideSpace = "";
		
		for(int spaces = 0; spaces <= (78 - textToCenter.length()) / 2; spaces++){
			
			sideSpace = sideSpace + sideType;
			
		}//end for loop
			
		System.out.println(colorCyan + sideSpace + textToCenter + sideSpace + colorReset);
			
	}//method centerText end
	
	public static void heroCreationKit(){
		
		int pointSelection;
		
		centerText("Welcome to the Hero Creation Kit", "-");//centrers welcome text
		
		while(points > 0){//loop for attribute selection
		
			System.out.println("\n---current Stats---");
			System.out.println("\nMax Health: " + heroHealth);
			System.out.println("Attack Power: " + heroAttackPower);
			System.out.println("Magic Power: " + heroMagicPower);
		
			System.out.println(colorGreen + "\nyou have " + points + " points to spend:" + colorReset);
		
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
				
				System.out.println(colorRed + "\nInvalid attribute, please try again." + colorReset);
				
			}//end if not 1,2,3
			
		}//end while for point selection
		
		System.out.println(colorYellow + "\nYou're now finished dedicating your attributes, prepare for battle!\n" + colorReset);
		
	}//method heroCreationKit end

	public static void monsterCreationKit(){
		
		monsterNameIndex = randomRange(0 , 3);
		
		centerText("Running the Monster Creation Kit", "-");
		System.out.println("\nGenerating monster...");
		
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
				
			case 2://manticore
			
				monsterHealth = 150 + randomRange(0, 49);
				monsterAttackPower = 15 + randomRange(0, 4);
				monsterExperianceValue = 5;
				
				break;
				
			case 3://hydra
			
				monsterHealth = 200 + randomRange(0, 50);
				monsterAttackPower = 20 + randomRange(0, 5);
				monsterExperianceValue = 15;
				
				break;
			
			
		}//end switch statement
		
		System.out.println("\n...Done!\n");
		
	}//end method monsterCreationKit
	
	public static int randomRange(int min, int max){
		
		return (int)((Math.random() * ((max - min) + 1)) + min);
		
	}
	
	public static void combatHandler(){
		
		int menuChoice;
		boolean run = true;	
		
		centerText("Welcome to the Combat Handler", "-");
		
		System.out.println(colorYellow + "\nyou're fighting a " + monsterName[monsterNameIndex] + "!" + colorReset);//Print the Monster’s name

		while(run){

			//REPORT STATS & MENU
	
			
			
			System.out.println("\n---" + monsterName[monsterNameIndex] + "'s stats---");
			System.out.println("\nHP: " + monsterHealth);//Print the Monster’s health
			System.out.println("AP: " + monsterAttackPower);//Print the Monster’s health
			System.out.println("XP Value: " + monsterExperianceValue);//Print the Monster’s health
			
			System.out.println("\n---Hero's stats---\n");//Print the Monster’s health
			System.out.println("HP: " + heroHealth);//Print the Player’s health
			System.out.println("AP: " + heroAttackPower);//Print the Monster’s health
			System.out.println("your MP: " + heroMagicPower);//Print the Player’s magic points



			System.out.println(colorGreen + "\nWhat would you like to do?" + colorReset);

			System.out.println("\nEnter 1 for Sword Attack");//Print option 1: Sword Attack
			System.out.println("Enter 2 to Cast Spell");//Print option 2: Cast Spell
			System.out.println("Enter 3 to Charge Mana");//Print option 3: Charge Mana
			System.out.println("Enter 4 to Run Away");//Print option 4: Run Away
		
			System.out.print("\nChoice: ");//Prompt player for action 

			menuChoice = keyBoardIn.nextInt();//Stores option

			if(menuChoice == 1){//Option 1

				combatHandlerSwordAttack();

			}//if menuChoice == 1 end

			else if(menuChoice == 2){//Option 2

				combatHandlerMagicAttack();

			}//else if menuchoice == 2 end

			else if(menuChoice == 3){//Option 3

				combatHandlerChargeMagic();


			}//else if menuChoice == 3 end

			else if(menuChoice == 4){//option 4

				run = combatHandlerRunAway();

			}//else if menuChoice == 4 end

			else{//Option invalid

				System.out.println(colorRed + "\nYou fumble around missing your opportunity to strike (Invalid Command).\n" + colorReset);


			}// else if not 1,2,3,4 end

			if(monsterHealth > 0 && run){

				combatHandlerMonsterAttack();

			}//if monterhealth > 0 end

			if(monsterHealth <= 0 || heroHealth <=0){

				if(monsterHealth <= 0){

					System.out.println(colorYellow + "You defeated the " + monsterName[monsterNameIndex] + " and earned " + monsterExperianceValue + " XP!" + colorReset);
					
					xpPool = xpPool + monsterExperianceValue;
					
					if(xpPool >= 10){
						
						for(int newLevel = 0; xpPool >= 10; newLevel++){
							
							xpPool = xpPool - 10;
							heroLevel = heroLevel + 1;
							points = points + 1;
							
						}//end for loop
					}//end if

				}//if victory end

				else{

					System.out.println(colorRed + "You were defeated, better luck next time hero." + colorReset);

				}//else defeat end

				run = false;//Stop combat loop by setting control variable to false

			}//if health <= 0 end

		}//while != validChoice end
		
	}
	
	public static void combatHandlerSwordAttack(){
		
		int generatedHeroDamage = randomRange(1, heroAttackPower);
		
		monsterHealth = monsterHealth - generatedHeroDamage;//Calculate damage & update monster health using subtraction

		System.out.println(colorYellow + "\n You strike the " + monsterName[monsterNameIndex] + " with your sword for " + generatedHeroDamage + " damage!\n" + colorReset);
		
	}//end method combatHandlerSwordAttack
	
	public static void combatHandlerMagicAttack(){
		
		if(heroMagicPower >= 3){

					monsterHealth = monsterHealth / 2;//Calculate damage & update monster health using division

					heroMagicPower = heroMagicPower - 3;//Reduce player's mana points by the spell cost using subtraction

					System.out.println(colorYellow + "\nYou cast the weaken spell on the " + monsterName[monsterNameIndex] + "!\n" + colorReset);

				}//if heroMagicPower >= 3 end

				else{

					System.out.println(colorRed + "\nYou try to cast the spell but suffer from magical burnout (Not Enough Mana)\n" + colorReset);

				}//else not enough mana end
		
	}//end method combatHandlerMagicAttack
	
	public static void combatHandlerChargeMagic(){
		
		heroMagicPower = heroMagicPower + 1;//Increment magic points and update players magic using addition

		System.out.println(colorYellow + "\nYou focus and charge your magic!\n" + colorReset);
		
	}//end method combatHandlerChargeMagic
	
	public static boolean combatHandlerRunAway(){
		
		System.out.println(colorYellow + "\nYou run away!" + colorReset);
		
		return false;
		
	}//end method combatHandlerRunAway
	
	public static void combatHandlerMonsterAttack(){
		
		int generatedMonsterDamage = randomRange(1, monsterAttackPower);

		heroHealth = heroHealth - generatedMonsterDamage;//calculates hero health after monster attack
				
		System.out.println(colorRed + "\nThe " + monsterName[monsterNameIndex] + " attacks you for " + generatedMonsterDamage + " damage!\n" + colorReset);
		
	}
	
	public static void mainMenu(){
		
				boolean run = true;
		
		while(run){
			
			centerText("Welcome to the Fallout Equestria Battle Simulator", "-");
			
			System.out.println("\n---Current stats---");
			System.out.println("\nLevel: " + heroLevel);
			System.out.println("XP: " + xpPool + "/10");
			System.out.println("available points: " + points);
		
			System.out.println(colorGreen + "\nWhat would you like to do? " + colorReset);
		
			System.out.println("\n1 to enter the Hero Creation Kit");
			System.out.println("2 to enter a battle");
			System.out.println("3 to quit");
			
			System.out.print("\nEnter choice: ");
		
			int mainMenuSelection = keyBoardIn.nextInt();
		
			if(mainMenuSelection == 1 && points >= 1){
			
				heroCreationKit();//begins hero creation kit
			
			}//end if
			
			else if(mainMenuSelection == 1 && points <= 0){
				
				System.out.println(colorRed + "\nYou don't have enough points to do this." + colorReset);
				
			}//end else if
		
			else if(mainMenuSelection == 2 && points == 0){
		
				monsterCreationKit();//randomly generates a monster
		
				combatHandler();//begins fight sequence
				
			}//end else if
			
			else if(mainMenuSelection == 2 && points >= 1){
				
				System.out.println(colorRed + "\nYou still have points to spend." + colorReset)
				;
			}//end else if
			
			else if(mainMenuSelection == 3){
				
				System.out.println(colorYellow + "\nThanks for playing!" + colorReset);
				
				run = false;
				
			}//end else if
			
			else{
				
				System.out.println(colorRed + "\nAre you sure you can read?" + colorReset);
				
			}//end else
			
		
		}//end while
		
	}//end method mainMenu
	
}//class FoeBattleSimulator end
