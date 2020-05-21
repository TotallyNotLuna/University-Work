import java.util.Scanner; 

/**
 * A program that converts Fahrenheit to Celsius and vice versa. This 
 * version of the program is poorly constructed with all of the code
 * in the main method. 
 *
 * Remember: 
 * 		tempCelsius = (tempFahr - 32) / 1.8 
 * 		tempFahr = tempCelsius * 1.8 + 32
 *
 * @author 	Deven Ronquillo
 * @revised	12 sept. 2016
 */ 
public class GoodTempConverter {
	static Scanner input = new Scanner(System.in); 

	public static void main(String[] args) {
		int userChoice = 0;  
		
		// Loop until user opts to quit program. 
		while (userChoice != 3) {
			// Print the user's options. 
			printMenu();
			
			// Get user's choice. 
			userChoice = input.nextInt(); 
			
			executeChoice(userChoice);
			
		} // end main program loop
	} // end method main

	private static void printMenu(){//prints user menu

		System.out.println("Please select one of the following options: "); 
		System.out.println("	1. Convert Fahrenheit to Celsius; "); 			
		System.out.println("	2. Convert Celsius to Fahrenheit; or "); 
		System.out.println("	3. Quit "); 
			
	}//end printMenu()

	private static void userChoiceOne(){
		System.out.printf("Please enter the temperature in Fahrenheit: "); 
		double tempFahr = input.nextDouble(); 
		double tempCelsius = (tempFahr - 32) / 1.8; 
		System.out.printf("%.2f degrees Fahrenheit is equal to %.2f degrees Celsius.%n",
		tempFahr, tempCelsius); 

	}//end userChoiceOne()

	private static void userChoiceTwo(){
		
		System.out.printf("Please enter the temperature in Celsius: "); 
		double tempCelsius = input.nextDouble(); 
		double tempFahr = tempCelsius * 1.8 + 32; 
		System.out.printf("%.2f degrees Celsius is equal to %.2f degrees Fahrenheit.%n", 
		tempCelsius, tempFahr); 

	}//end userChoiceTwo()
	
	private static void executeChoice(int userChoice){

		// User wants to convert Fahrenheit to Celsius. 
		if (userChoice == 1) {
			userChoiceOne();
		} 
			
		// User wants to convert Celsius to Fahrenheit. 
		else if (userChoice == 2) {
			userChoiceTwo();
		} 
			
		// User wants to quit the program. 
		else if (userChoice == 3) {
			System.out.println("Thanks! Have a nice day!");  
		} 
					// User entered invalid input. 
		else {
			System.out.println("Invalid input. Please enter only 1, 2, or 3."); 
		} 
	}//end executeChoice()

} // end class BadTempConverter
