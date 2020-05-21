/**
 * Sandbox for working with Fractions and exceptions for
 * Lab 10.
 *
 * Author: Franklin D. Worrell
 * Revised: 19 July 2016 at 16:48
 * Assignment: Lab 10 - Exceptions
 * Class: Runner
 */ 
 
import java.util.Scanner; 

public class Runner {
	public static void main(String[] args) {
		// Create Scanner instance to get user input. 
		Scanner input = new Scanner(System.in);
		boolean run = true; 
		
		// Get values for first Fraction and create it. 
		System.out.printf("Please enter the dividend Fraction's numerator: "); 
		int firstNum = input.nextInt(); 
		System.out.printf("Please enter the dividend Fraction's denominator: "); 
		int firstDen = input.nextInt(); 
		Fraction dividend = new Fraction(firstNum, firstDen); 

		while(run){
			try{

			// Get values for second Fraction and create it. 
			System.out.printf("Please enter the divisor Fraction's numerator: "); 
			int secondNum = input.nextInt(); 
			System.out.printf("Please enter the divisor Fraction's denominator: "); 
			int secondDen = input.nextInt(); 
			Fraction divisor = new Fraction(secondNum, secondDen); 
		
			// Calculate quotient. Print results. 
			Fraction quotient = dividend.divide(divisor); 
			System.out.println("Their quotient is: " + quotient); 

			run = false;
			}
			catch(IllegalArgumentException e){

				System.out.print("Invalid argument, try again.\n");
				run = true;
			}		
		}
	} // end method main
} // end class Runner